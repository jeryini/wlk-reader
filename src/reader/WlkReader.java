package reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javolution.io.Struct.Signed8;
import javolution.io.Struct.Unsigned8;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

import struct.DailySummary1;
import struct.DailySummary2;
import struct.DayIndex;
import struct.HeaderBlock;
import struct.WeatherDataRecord;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import data.DailySummary;
import data.DailyWeatherData;
import data.DataConverter;

/**
 * Class for reading *.wlk files and converting data to user specified metric
 * units.
 * 
 * @author Jernej Jerin <jernej.jerin@gmail.com>
 * @version %I%, %G%
 * @since 1.0
 */
public class WlkReader {
	// Properties
	private File directory;
	private DateTime dateTimeStart;
	private DateTime dateTimeEnd;

	// Default is true which means that unit conversion from NonSI to SI
	// will take place (from imperial to metric).
	private boolean unit = true;

	/**
	 * @return the directory
	 */
	public File getDirectory() {
		return directory;
	}

	/**
	 * @param directory
	 *            the directory to set
	 */
	public void setDirectory(File directory) {
		this.directory = directory;
	}

	/**
	 * @return the dateTimeStart
	 */
	public DateTime getDateTimeStart() {
		return dateTimeStart;
	}

	/**
	 * @param dateTimeStart
	 *            the dateTimeStart to set
	 */
	public void setDateTimeStart(DateTime dateTimeStart) {
		this.dateTimeStart = dateTimeStart;
	}

	/**
	 * @return the dateTimeEnd
	 */
	public DateTime getDateTimeEnd() {
		return dateTimeEnd;
	}

	/**
	 * @param dateTimeEnd
	 *            the dateTimeEnd to set
	 */
	public void setDateTimeEnd(DateTime dateTimeEnd) {
		this.dateTimeEnd = dateTimeEnd;
	}

	/**
	 * @return the unit
	 */
	public boolean isUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(boolean unit) {
		this.unit = unit;
	}

	/**
	 * Constructor for setting root directory where the *.wlk files are
	 * contained. It will read all the available data with no date and time
	 * constrains.
	 * 
	 * @param directory
	 */
	public WlkReader(File directory) {
		this.directory = directory;
	}

	/**
	 * Constructor for setting root directory where the *.wlk files are
	 * contained and date time from which point onward in time to return data
	 * and to the last specified time.
	 * <p>
	 * If no start date time is specified then it will start from the beginning.
	 * </p>
	 * <p>
	 * If no end date time is specified then it will end with the last possible
	 * data.
	 * </p>
	 * 
	 * @param directory
	 * @param dateTimeStart
	 * @param dateTimeEnd
	 */
	public WlkReader(File directory, DateTime dateTimeStart,
			DateTime dateTimeEnd) {
		this.directory = directory;
		this.dateTimeStart = dateTimeStart;
		this.dateTimeEnd = dateTimeEnd;
	}

	/**
	 * Constructor for setting root directory where the *.wlk files are
	 * contained and whether there should be unit conversion.
	 * 
	 * @param directory
	 * @param unit
	 */
	public WlkReader(File directory, boolean unit) {
		this.directory = directory;
		this.unit = unit;
	}

	/**
	 * Constructor for setting root directory where the *.wlk files are
	 * contained, date time from which point onward in time to return data and
	 * whether there should be unit conversion.
	 * 
	 * @param directory
	 * @param dateTimeStart
	 * @param dateTimeEnd
	 * @param unit
	 */
	public WlkReader(File directory, DateTime dateTimeStart,
			DateTime dateTimeEnd, boolean unit) {
		this.directory = directory;
		this.dateTimeStart = dateTimeStart;
		this.dateTimeEnd = dateTimeEnd;
		this.unit = unit;
	}

	/**
	 * Read data, convert it to right type and according to specified
	 * metric/imperial units.
	 * 
	 * @return list of converted data
	 */
	public List<DailyWeatherData> readData() throws IOException,
			IllegalArgumentException, UnsupportedOperationException,
			ArithmeticException {
		// First we get all the files with *.wlk extension in specified
		// directory (usally DavisVan).
		Collection<File> fileList = null;
		// If we create WlkReader with a file instead a directory, we create fileList
		// collection only with one file to parse.
		if(this.directory.exists() && !this.directory.isDirectory() && this.directory.isFile()) {
			if(this.directory.getCanonicalPath().endsWith("wlk")) {
				fileList = new ArrayList<>(Arrays.asList(this.directory));
			}
		} else {
			fileList = FileUtils.listFiles(this.directory,
					FileFilterUtils.suffixFileFilter("wlk"), null);
		}

		// A collection where we will save data and later return it.
		List<DailyWeatherData> dailyWeatherDataList = new ArrayList<DailyWeatherData>();

		// Object for converting data to specific format accordingly to user
		// choice.
		DataConverter dataConverter = new DataConverter(this.unit);

		// Then we iterate over all the files.
		for (File file : fileList) {
			// File name (yyyy-MM) is converted to date time.
			DateTime fileDateTime = new DateTime(
					FilenameUtils.removeExtension(file.getName()));

			// We need to add one month so that file is read even if date time
			// for reading records is set to the last day in month.
			fileDateTime = fileDateTime.plusMonths(1);

			// Check whether date time of the specified date time is before date
			// time parsed from file name. If the date time is not set then we
			// read all the files. The end date time plus one month must be
			// after
			// file date time.

			if ((this.dateTimeStart == null && this.dateTimeEnd == null)
					|| (this.dateTimeStart == null && this.dateTimeEnd != null && this.dateTimeEnd
							.plusMonths(1).isAfter(fileDateTime))
					|| (this.dateTimeStart != null && this.dateTimeEnd == null && this.dateTimeStart
							.isBefore(fileDateTime))
					|| (this.dateTimeStart != null && this.dateTimeEnd != null
							&& this.dateTimeStart.isBefore(fileDateTime) && this.dateTimeEnd
							.plusMonths(1).isAfter(fileDateTime))) {
				// The offset of daily summary record 1.
				int offset = 0;

				// First we get bytes from file by calling method
				// getBytesFromFile.
				byte[] bytes = getBytesFromFile(new File(file.getPath()));

				// Then we create byte buffer and set the order to LITTLE
				// ENDIAN.
				ByteBuffer buffer = ByteBuffer.wrap(bytes);
				buffer.order(ByteOrder.LITTLE_ENDIAN);

				/*
				 * Then we create and initialize object HeaderBlock, that
				 * represents header in a file that we are reading from. We also
				 * set current byte buffer for this struct.
				 */
				HeaderBlock headerBlock = new HeaderBlock();
				headerBlock.setByteBuffer(buffer, offset);

				// Here we create instance of different data.
				DailySummary1 dailySummary1 = new DailySummary1();
				dailySummary1.setByteBuffer(buffer, offset);

				DailySummary2 dailySummary2 = new DailySummary2();
				WeatherDataRecord weatherDataRecord = new WeatherDataRecord();

				/*
				 * Now we iterate through array of dayIndex and if the number of
				 * records in day is larger than zero, then we calculate offset
				 * (this is the start of daily summary 1 of the day). We also
				 * set variable day which counts the number of cycles through
				 * for loop. It should be noted that index 0 is not being used
				 * in header block which means that indeks for day 1 is on index
				 * 1. That is why we set the number of loops on -1.
				 */
				int day = -1;
				for (DayIndex dayIndex : headerBlock.dayIndex) {
					// Increment number of days gone by in file.
					day++;

					// Check if record contains any data.
					if (dayIndex.recordsInDay.get() > 0) {

						// Create date time from file date and number of days.
						DateTime dateTimeDay = new DateTime(fileDateTime
								.minusMonths(1).getYear(), fileDateTime
								.minusMonths(1).getMonthOfYear(), day, 0, 0);

						// Check all the possibilities.
						if ((this.dateTimeStart == null && this.dateTimeEnd == null)
								|| (this.dateTimeStart == null
										&& this.dateTimeEnd != null && this.dateTimeEnd
										.toLocalDate().compareTo(
												dateTimeDay.toLocalDate()) >= 0)
								|| (this.dateTimeStart != null
										&& this.dateTimeEnd == null && this.dateTimeStart
										.toLocalDate().compareTo(
												dateTimeDay.toLocalDate()) <= 0)
								|| (this.dateTimeStart != null
										&& this.dateTimeEnd != null
										&& this.dateTimeStart.toLocalDate()
												.compareTo(
														dateTimeDay
																.toLocalDate()) <= 0 && this.dateTimeEnd
										.toLocalDate().compareTo(
												dateTimeDay.toLocalDate()) >= 0)) {
							/*
							 * First we compute offset. We need to add 212,
							 * because the first 212B is used by header. The
							 * multiplication with 88 is because each record
							 * takes 88B of space.
							 */
							offset = (int) (dayIndex.startPos.get() * 88 + 212);
							dailySummary1.setByteBufferPosition(offset);

							// Check for correct data type.
							if (dailySummary1.dataType.get() == 2) {
								// Daily summary for this day.
								DailySummary dailySummary = new DailySummary();

								// Set data from daily summary 1.
								setDataDailySummary1(dailySummary,
										dailySummary1, dataConverter,
										fileDateTime, day);

								// Continue with added offset for getting daily
								// summary 2.
								offset += 88;
								dailySummary2.setByteBuffer(buffer, offset);

								// Check for correct data type.
								if (dailySummary2.dataType.get() == 3) {
									// Save data for daily summary 2.
									setDailySummary2(dailySummary,
											dailySummary2, dataConverter);

									// Storing weather data records for each
									// measurement.
									List<data.WeatherDataRecord> weatherDataRecordList = new ArrayList<data.WeatherDataRecord>();

									// Here we subtracted records in day by two,
									// because we have
									// already accounted daily summary 1 and
									// daily
									// summary 2.
									for (int i = 0; i < dayIndex.recordsInDay
											.get() - 2; i++) {
										offset += 88;
										weatherDataRecord.setByteBuffer(buffer,
												offset);

										// Check for correct data type.
										if (weatherDataRecord.dataType.get() == 1) {
											// We get the hours from packed time
											// by
											// dividing it with 60 (represents
											// minutes)
											// and round it to the nearest
											// integer.
											short hours = (short) Math
													.floor(weatherDataRecord.packedTime
															.get() / 60);

											// We get the minutes from packed
											// time
											// by computing the reminder
											// when dividing packed time with 60
											short minutes = (short) (weatherDataRecord.packedTime
													.get() % 60);

											/*
											 * Now we can create date time on
											 * the basis of values from the name
											 * of the file (year, month) and
											 * packed time from weather data
											 * record (day, hours, minutes).
											 * Because the record with time
											 * 00:00 (hours = 24) belongs to
											 * previous day (interval of records
											 * is from 00:00 - 23:59) we need to
											 * set hours and minutes to 00:00
											 * (24:00 -> 00:00) and add a day.
											 */

											DateTime dateRecord;
											if (hours == 24) {
												dateRecord = new DateTime(
														fileDateTime
																.minusMonths(1)
																.getYear(),
														fileDateTime
																.minusMonths(1)
																.getMonthOfYear(),
														day, 0, 0);
												dateRecord = dateRecord
														.plusDays(1);
											} else {
												dateRecord = new DateTime(
														fileDateTime
																.minusMonths(1)
																.getYear(),
														fileDateTime
																.minusMonths(1)
																.getMonthOfYear(),
														day, hours, minutes);
											}

											// Again check for date time.
											if ((this.dateTimeStart == null && this.dateTimeEnd == null)
													|| (this.dateTimeStart == null
															&& this.dateTimeEnd != null && this.dateTimeEnd
																.isAfter(dateRecord))
													|| (this.dateTimeStart != null
															&& this.dateTimeEnd == null && this.dateTimeStart
																.isBefore(dateRecord))
													|| (this.dateTimeStart != null
															&& this.dateTimeEnd != null
															&& this.dateTimeStart
																	.isBefore(dateRecord) && this.dateTimeEnd
																.isAfter(dateRecord))) {
												// Create a new
												// WeatherDataRecord.
												data.WeatherDataRecord weatherRecord = new data.WeatherDataRecord();

												// Saving data.
												setWeatherRecord(weatherRecord,
														weatherDataRecord,
														dataConverter,
														dateRecord);

												// Setting the date and time of
												// the
												// user input record to the
												// date time of the last record.
												if (this.dateTimeStart != null) {
													this.dateTimeStart = dateRecord;
												}

												// Storing it into the list.
												weatherDataRecordList
														.add(weatherRecord);
											}
										} else {
											throw new ArithmeticException(
													"Error in offset. Wrong data type for weather data record!");
										}
									}

									// Here we will store daily summary as well
									// as
									// weather data record.
									DailyWeatherData dailyWeatherData = new DailyWeatherData(
											dailySummary, weatherDataRecordList);

									// Add it to the list of daily weather data
									// which is in the end returned.
									dailyWeatherDataList.add(dailyWeatherData);
								} else {
									throw new ArithmeticException(
											"Error in offset. Wrong data type for daily summary 2!");
								}
							} else {
								throw new ArithmeticException(
										"Error in offset. Wrong data type for daily summary 1!");
							}
						}
					}
				}
			}
		}
		return dailyWeatherDataList;
	}

	private void setWeatherRecord(data.WeatherDataRecord weatherRecord,
			WeatherDataRecord weatherDataRecord, DataConverter dataConverter,
			DateTime dateRecord) {
		weatherRecord.setArchiveInterval(weatherDataRecord.archiveInterval
				.get());
		weatherRecord.setIconFlags(weatherDataRecord.iconFlags.get());
		weatherRecord.setTime(dateRecord.toLocalTime());
		weatherRecord.setNumwindSamples((int) weatherDataRecord.numWindSamples
				.get());
		weatherRecord.setOutTemp(dataConverter
				.convertTemperature(weatherDataRecord.outsideTemp.get()));
		weatherRecord.setMaxOutTemp(dataConverter
				.convertTemperature(weatherDataRecord.hiOutsideTemp.get()));
		weatherRecord.setMinOutTemp(dataConverter
				.convertTemperature(weatherDataRecord.lowOutsideTemp.get()));
		weatherRecord.setInTemp(dataConverter
				.convertTemperature(weatherDataRecord.insideTemp.get()));
		weatherRecord.setPressure(dataConverter
				.convertPressure(weatherDataRecord.barometer.get()));
		weatherRecord.setOutHumidity(dataConverter
				.convertHumidity(weatherDataRecord.outsideHum.get()));
		weatherRecord.setInHumidity(dataConverter
				.convertHumidity(weatherDataRecord.insideHum.get()));
		weatherRecord.setPrecipitation(dataConverter
				.convertPrecipitation((short) weatherDataRecord.rain.get()));
		weatherRecord.setMaxPrecipitationRate(dataConverter
				.convertPrecipitation(weatherDataRecord.hiRainRate.get()));
		weatherRecord.setWindSpeed(dataConverter
				.convertWindSpeed(weatherDataRecord.windSpeed.get()));
		weatherRecord.setMaxWindSpeed(dataConverter
				.convertWindSpeed(weatherDataRecord.hiWindSpeed.get()));
		weatherRecord.setWindDirection(dataConverter
				.convertWindDirection((short) weatherDataRecord.windDirection
						.get()));
		weatherRecord.setMaxWindDirection(dataConverter
				.convertWindDirection((short) weatherDataRecord.hiWindDirection
						.get()));
		weatherRecord.setSolarRad(dataConverter
				.convertSolarEnergy(weatherDataRecord.solarRad.get()));
		weatherRecord.setMaxSolarRad(dataConverter
				.convertSolarEnergy(weatherDataRecord.hiSolarRad.get()));
		weatherRecord.setUV(dataConverter
				.convertUV((short) weatherDataRecord.UV.get()));
		weatherRecord.setMaxUV(dataConverter
				.convertUV((short) weatherDataRecord.hiUV.get()));
		weatherRecord.setLeafTemp(this.computeExtraSensors(
				weatherDataRecord.leafTemp, dataConverter));
		weatherRecord.setExtraRad((double) weatherDataRecord.extraRad.get());
		weatherRecord.setForecast((int) weatherDataRecord.forecast.get());
		weatherRecord.setET(dataConverter
				.convertPrecipitation((short) weatherDataRecord.ET.get()));
		weatherRecord.setSoilTemp(this.computeExtraSensors(
				weatherDataRecord.soilTemp, dataConverter));
		// Setting soil moisture!
		// weatherRecord.setSoilMoisture...
		// Setting leaf wetness!
		// weatherRecord.setLeafWetness...
		weatherRecord.setExtraTemp(this.computeExtraSensors(
				weatherDataRecord.extraTemp, dataConverter));
		// Setting extra humidity!
		// weatherRecord.setExtraHumidity...

	}

	private void setDailySummary2(DailySummary dailySummary,
			DailySummary2 dailySummary2, DataConverter dataConverter) {
		dailySummary.setNumWindPackets(dailySummary2.numWindPackets.get());
		dailySummary.setDailySolarEnergy(dataConverter
				.convertSolarEnergy(dailySummary2.dailySolarEnergy.get()));
		dailySummary.setMinSunLight((int) dailySummary2.minSunLight.get());
		dailySummary.setDailyETTotal(dataConverter
				.convertPrecipitation(dailySummary2.dailyETTotal.get()));
		dailySummary.setIntegratedHeatDD65(dataConverter
				.convertTemperature(dailySummary2.integratedHeatDD65.get()));
		dailySummary.setWindDirectionDistribution(this
				.computeWindDirectionDistribution(dailySummary2.dirBins));
		dailySummary.setIntegratedCoolDD65(dataConverter
				.convertTemperature(dailySummary2.integratedCoolDD65.get()));

		// Maximum values.
		dailySummary.setMaxSolar((double) dailySummary2.hiSolar.get());
		dailySummary.setTimeMaxSolar(this.computeTimeValue(0,
				dailySummary2.timeValues));
		dailySummary.setMaxHeatIndex(dataConverter
				.convertTemperature(dailySummary2.hiHeat.get()));
		dailySummary.setTimeMaxSolar(this.computeTimeValue(1,
				dailySummary2.timeValues));
		dailySummary.setMaxTHSWIndex(dataConverter
				.convertTemperature(dailySummary2.hiTHSW.get()));
		dailySummary.setTimeMaxTHSWIndex(this.computeTimeValue(3,
				dailySummary2.timeValues));
		dailySummary.setMaxTHWIndex(dataConverter
				.convertTemperature(dailySummary2.hiTHW.get()));
		dailySummary.setTimeMaxTHWIndex(this.computeTimeValue(5,
				dailySummary2.timeValues));

		// Minimum values.
		dailySummary.setMinHeatIndex(dataConverter
				.convertTemperature(dailySummary2.lowHeat.get()));
		dailySummary.setTimeMinHeatIndex(this.computeTimeValue(2,
				dailySummary2.timeValues));
		dailySummary.setMinTHSWIndex(dataConverter
				.convertTemperature(dailySummary2.lowTHSW.get()));
		dailySummary.setTimeMinTHSWIndex(this.computeTimeValue(4,
				dailySummary2.timeValues));
		dailySummary.setMinTHWIndex(dataConverter
				.convertTemperature(dailySummary2.lowTHW.get()));
		dailySummary.setTimeMinTHWIndex(this.computeTimeValue(6,
				dailySummary2.timeValues));

		// Avg values.
		dailySummary.setAvgHeatIndex(dataConverter
				.convertTemperature(dailySummary2.avgHeat.get()));

	}

	/**
	 * Set data for daily summary 1 values.
	 * 
	 * @param dailySummary
	 * @param dailySummary1
	 * @param dataConverter
	 * @param fileDateTime
	 * @param day
	 */
	private void setDataDailySummary1(DailySummary dailySummary,
			DailySummary1 dailySummary1, DataConverter dataConverter,
			DateTime fileDateTime, int day) {
		dailySummary.setDate(new LocalDate(fileDateTime.minusMonths(1)
				.getYear(), fileDateTime.minusMonths(1).getMonthOfYear(), day));
		if (dailySummary1.dataSpan.get() != Short.MIN_VALUE) {
			dailySummary.setDataSpan((int) dailySummary1.dataSpan.get());
		}
		dailySummary.setWindRun(dataConverter
				.convertWindRun(dailySummary1.dailyWindRunTotal.get()));
		dailySummary.setDailyRain(dataConverter
				.convertPrecipitation(dailySummary1.dailyRainTotal.get()));
		dailySummary.setDailyUVDose(dataConverter
				.convertUV(dailySummary1.dailyUVDose.get()));

		// Maximum values.
		dailySummary.setMaxOutTemp(dataConverter
				.convertTemperature(dailySummary1.hiOutTemp.get()));
		dailySummary.setTimeMaxOutTemp(this.computeTimeValue(0,
				dailySummary1.timeValues));
		dailySummary.setMaxInTemp(dataConverter
				.convertTemperature(dailySummary1.hiInTemp.get()));
		dailySummary.setTimeMaxInTemp(this.computeTimeValue(2,
				dailySummary1.timeValues));
		dailySummary.setMaxWindChill(dataConverter
				.convertTemperature(dailySummary1.hiChill.get()));
		dailySummary.setTimeMaxWindChill(this.computeTimeValue(4,
				dailySummary1.timeValues));
		dailySummary.setMaxDewPoint(dataConverter
				.convertTemperature(dailySummary1.hiDew.get()));
		dailySummary.setTimeMaxDewPoint(this.computeTimeValue(6,
				dailySummary1.timeValues));
		dailySummary.setMaxOutHum(dataConverter
				.convertHumidity(dailySummary1.hiOutHum.get()));
		dailySummary.setTimeMaxOutHum(this.computeTimeValue(8,
				dailySummary1.timeValues));
		dailySummary.setMaxInHum(dataConverter
				.convertHumidity(dailySummary1.hiInHum.get()));
		dailySummary.setTimeMaxInHum(this.computeTimeValue(10,
				dailySummary1.timeValues));
		dailySummary.setMaxPressure(dataConverter
				.convertPressure(dailySummary1.hiBar.get()));
		dailySummary.setTimeMaxPressure(this.computeTimeValue(12,
				dailySummary1.timeValues));
		dailySummary.setMaxWindSpeed(dataConverter
				.convertWindSpeed(dailySummary1.hiSpeed.get()));
		dailySummary.setMaxWindSpeedDir(dataConverter
				.convertWindDirection(dailySummary1.dirHiSpeed.get()));
		dailySummary.setTimeMaxWindSpeed(this.computeTimeValue(14,
				dailySummary1.timeValues));
		dailySummary.setMaxAvg10MinWindSpeed(dataConverter
				.convertWindSpeed(dailySummary1.hi10MinSpeed.get()));
		dailySummary.setMaxAvg10MinWindSpeedDir(dataConverter
				.convertWindDirection(dailySummary1.hi10MinDir.get()));
		dailySummary.setTimeMaxAvg10MinWindSpeed(this.computeTimeValue(15,
				dailySummary1.timeValues));
		dailySummary.setMaxRainRate(dataConverter
				.convertPrecipitation(dailySummary1.hiRainRate.get()));
		dailySummary.setTimeMaxRainRate(this.computeTimeValue(16,
				dailySummary1.timeValues));
		dailySummary
				.setMaxUV(dataConverter.convertUV(dailySummary1.hiUV.get()));
		dailySummary.setTimeMaxUV(this.computeTimeValue(17,
				dailySummary1.timeValues));

		// Minimum values.
		dailySummary.setMinOutTemp(dataConverter
				.convertTemperature(dailySummary1.lowOutTemp.get()));
		dailySummary.setTimeMinOutTemp(this.computeTimeValue(1,
				dailySummary1.timeValues));
		dailySummary.setMinInTemp(dataConverter
				.convertTemperature(dailySummary1.lowInTemp.get()));
		dailySummary.setTimeMinInTemp(this.computeTimeValue(3,
				dailySummary1.timeValues));
		dailySummary.setMinWindChill(dataConverter
				.convertTemperature(dailySummary1.lowChill.get()));
		dailySummary.setTimeMinWindChill(this.computeTimeValue(5,
				dailySummary1.timeValues));
		dailySummary.setMinDewPoint(dataConverter
				.convertTemperature(dailySummary1.lowDew.get()));
		dailySummary.setTimeMinDewPoint(this.computeTimeValue(7,
				dailySummary1.timeValues));
		dailySummary.setMinOutHum(dataConverter
				.convertHumidity(dailySummary1.lowOutHum.get()));
		dailySummary.setTimeMinOutHum(this.computeTimeValue(9,
				dailySummary1.timeValues));
		dailySummary.setMinInHum(dataConverter
				.convertHumidity(dailySummary1.lowInHum.get()));
		dailySummary.setTimeMinInHum(this.computeTimeValue(11,
				dailySummary1.timeValues));
		dailySummary.setMinPressure(dataConverter
				.convertPressure(dailySummary1.lowBar.get()));
		dailySummary.setTimeMinPressure(this.computeTimeValue(13,
				dailySummary1.timeValues));

		// Average values.
		dailySummary.setAvgOutTemp(dataConverter
				.convertTemperature(dailySummary1.avgOutTemp.get()));
		dailySummary.setAvgInTemp(dataConverter
				.convertTemperature(dailySummary1.avgInTemp.get()));
		dailySummary.setAvgWindChill(dataConverter
				.convertTemperature(dailySummary1.avgChill.get()));
		dailySummary.setAvgDewPoint(dataConverter
				.convertTemperature(dailySummary1.avgDew.get()));
		dailySummary.setAvgOutHum(dataConverter
				.convertHumidity(dailySummary1.avgOutHum.get()));
		dailySummary.setAvgPressure(dataConverter
				.convertPressure(dailySummary1.avgBar.get()));
		dailySummary.setAvgWindSpeed(dataConverter
				.convertWindSpeed(dailySummary1.avgSpeed.get()));

	}

	/**
	 * Returns the contents of the file in a byte array.
	 * 
	 * The author: Example Depot
	 * (http://www.exampledepot.com/egs/java.io/file2bytearray.html)
	 * 
	 * @param file
	 *            file from which the content is read
	 * @return contents of the file in a byte array
	 */
	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream inputStream = new FileInputStream(file);

		// Get the size of the file.
		long length = file.length();

		/*
		 * You cannot create an array using a long type. It needs to be an int
		 * type. Before converting to an int type, check to ensure that file is
		 * not larger than Integer.MAX_VALUE.
		 */
		if (length > Integer.MAX_VALUE) {
			// Close the input stream.
			inputStream.close();
			return null;
		} else {
			// Create the byte array to hold the data.
			byte[] bytes = new byte[(int) length];

			// Read in the bytes.
			int offset = 0;
			int numRead = 0;

			while (offset < bytes.length
					&& (numRead = inputStream.read(bytes, offset, bytes.length
							- offset)) >= 0) {
				offset += numRead;
			}

			// Ensure that all the bytes have been read in.
			if (offset < bytes.length) {
				// Close the input stream.
				inputStream.close();
				throw new IOException("Could not completely read file "
						+ file.getName());
			}

			// Close the input stream and return bytes.
			inputStream.close();
			return bytes;
		}
	}

	/**
	 * Computes time values from structure read from the files.
	 * 
	 * @param index
	 * @param timeValues
	 * @return computed time values
	 */
	private LocalTime computeTimeValue(int index, Unsigned8[] timeValues) {
		// Compute field index from index. Integer division (rounded down).
		int fieldIndex = (int) ((index / 2) * 3);

		// Packed time which we will get from time values.
		int packedTime;

		// If index is even.
		if (index % 2 == 0) {
			packedTime = timeValues[fieldIndex].get()
					+ ((timeValues[fieldIndex + 2].get() & 0x0F) << 8);
		} else {
			packedTime = timeValues[fieldIndex + 1].get()
					+ ((timeValues[fieldIndex + 2].get() & 0xF0) << 4);
		}

		// A value of 0x0FFF or 0x07FF indicates no data available (i. e.
		// invalid data).
		if (packedTime == 0x0FFF || packedTime == 0x07FF
				|| packedTime == 0x1000 || packedTime == 0x0800) {
			return null;
		} else {
			// We get the hours by dividning packed time with 60 and rounding it
			// down to the nearest integer value.
			short hourOfDay = (short) Math.floor(packedTime / 60);

			// Number of minutes is always between 1 and 1440. If extreme is
			// recorded
			// at 00:00 it means it was recorded at the end of day (23:59 -
			// 00:00). So
			// 1 means 00:00 - 00:01 and 1440 means 23:59 - 00:00.
			if (hourOfDay == 24) {
				hourOfDay = 0;
			}

			// We get the minutes by computing the reminder when dividing with
			// 60.
			short minuteOfHour = (short) (packedTime % 60);

			// Return computed time.
			return new LocalTime(hourOfDay, minuteOfHour);
		}
	}

	/**
	 * Computes wind direction in degrees according to direction values.
	 * 
	 * @param direction
	 * @return converted direction
	 */
	private Integer[] computeWindDirectionDistribution(Signed8[] direction) {
		// If index is even.
		Integer[] windDirectionDistribution = new Integer[16];

		for (int i = 0; i < 16; i++) {
			// Compute field index from index. Integer division (rounded down).
			int fieldIndex = (int) ((i / 2) * 3);

			// Minutes.
			int minutes;

			if (i % 2 == 0) {
				minutes = direction[fieldIndex].get()
						+ ((direction[fieldIndex + 2].get() & 0x0F) << 8);
			} else {
				minutes = direction[fieldIndex + 1].get()
						+ ((direction[fieldIndex + 2].get() & 0xF0) << 4);
			}

			// A value of 0x0FFF or 0x07FF indicates no data available (i. e.
			// invalid data).
			if (minutes == 0x0FFF || minutes == 0x07FF) {
				windDirectionDistribution[i] = null;
			} else {
				// Saving values.
				windDirectionDistribution[i] = minutes;
			}
		}

		return windDirectionDistribution;
	}

	/**
	 * Extra temperature sensors. At the moment this sensors are not used by
	 * Davis Vantage Pro2 (reserved for future use).
	 * 
	 * @param temperatureSensors
	 * @return converted values
	 */
	private Double[] computeExtraSensors(Signed8[] temperatureSensors,
			DataConverter dataConverter) {
		Double[] computedTemperature = new Double[temperatureSensors.length];
		for (int i = 0; i < temperatureSensors.length; i++) {
			computedTemperature[i] = dataConverter
					.convertTemperature((short) (temperatureSensors[i].get() - 90));
		}

		return computedTemperature;
	}
}
