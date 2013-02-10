package reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

import struct.DailySummary1;
import struct.DailySummary2;
import struct.DayIndex;
import struct.HeaderBlock;
import struct.WeatherDataRecord;

import org.joda.time.DateTime;

/**
 * @author	Jernej Jerin <jernej.jerin@gmail.com>
 * @version	0.1
 * @since	2012-01-27
 */
public class WlkReader {
	// Properties
	private File directory;
	private DateTime dateTime;
	
	/**
	 * @return the directory
	 */
	public File getDirectory() {
		return directory;
	}

	/**
	 * @param directory the directory to set
	 */
	public void setDirectory(File directory) {
		this.directory = directory;
	}

	/**
	 * @return the dateTime
	 */
	public DateTime getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * Constructor for setting root directory where the *.wlk files are contained.
	 * @param directory
	 */
	public WlkReader(File directory) {
		this.directory = directory;
	}
	
	/**
	 * Constructor for setting root directory where the *.wlk files are contained and date time
	 * from which point onward in time to return data.
	 * @param directory
	 * @param dateTime
	 */
	public WlkReader(File directory, DateTime dateTime) {
		this.directory = directory;
		this.dateTime = dateTime;
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			// First we get all the files with wlk extension in DavisVan folder.
			Collection<File> fileList = FileUtils.listFiles(new File("//JERNEJ-MINIPC/DavisVan"), FileFilterUtils.suffixFileFilter("wlk"), null);
			
			// Then we iterate over all the files.
			for (File file : fileList) {
				// The offset of daily summary record 1.
				int offset = 0;
				
				// First we get bytes from file by calling method getBytesFromFile.
				byte[] bytes = getBytesFromFile(new File(file.getPath()));
				
				// Then we create byte buffer and set the order to LITTLE ENDIAN.
				ByteBuffer buffer = ByteBuffer.wrap(bytes);
				buffer.order(ByteOrder.LITTLE_ENDIAN);
				
				/*
				 * Then we create and initialize object HeaderBlock, that represents
				 * header in a file that we are reading from. We also set
				 * current byte buffer for this struct.
				 */
				HeaderBlock headerBlock = new HeaderBlock();
				headerBlock.setByteBuffer(buffer, offset);
				
				/*
				 * Here we create instance of different data.
				 */
				DailySummary1 dailySummary1 = new DailySummary1();
				dailySummary1.setByteBuffer(buffer, offset);
				
				DailySummary2 dailySummary2 = new DailySummary2();
				
				WeatherDataRecord weatherDataRecord = new WeatherDataRecord();
				
				/*
				 * Now we iterate through array of dayIndex and if the number of records in
				 * day is larger than zero, then we calculate offset (this is the start of 
				 * daily summary 1 of the day).
				 */
				for (DayIndex dayIndex : headerBlock.dayIndex) {
					if (dayIndex.recordsInDay.get() > 0) {
						/*
						 * First we compute offset. We need to add 212, 
						 * because the first 212B is used by header. The multiplication
						 * with 88 is because each record takes 88B of space.
						 */
						offset = (int) (dayIndex.startPos.get() * 88 + 212);
						dailySummary1.setByteBufferPosition(offset);
						
						if (dailySummary1.dataType.get() == 2) {
							// Save data for daily summary 1.
							offset += 88;
							dailySummary2.setByteBuffer(buffer, offset);
							
							if (dailySummary2.dataType.get() == 3) {
								// Save data for daily summary 2.
								
								// Here we subtracted records in day by two, because we have
								// already accounted daily summary 1 and daily summary 2.
								for (int i = 0; i < dayIndex.recordsInDay.get() - 2; i++) {
									offset += 88;
									weatherDataRecord.setByteBuffer(buffer, offset);
									
									if (weatherDataRecord.dataType.get() == 1) {
										// Save data for weather data record.
										// int  forecast = weatherDataRecord.forecast.get();
									}
									else {
										throw new ArithmeticException("Error in offset. Wrong data type for weather data record!");
									}
								}
							}
							else {
								throw new ArithmeticException("Error in offset. Wrong data type for daily summary 2!"); 
							}
						}
						else {
							throw new ArithmeticException("Error in offset. Wrong data type for daily summary 1!");
						}
					}
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		} catch (UnsupportedOperationException e) {
			System.err.println(e.getMessage());
		} catch (ArithmeticException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Returns the contents of the file in a byte array.
	 * 
	 * @author Example Depot (http://www.exampledepot.com/egs/java.io/file2bytearray.html)
	 * @param file file from which the content is read
	 * @return contents of the file in a byte array
	 */
	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream inputStream = new FileInputStream(file);
		
		// Get the size of the file.
		long length = file.length();
		
		/*
		 * You cannot create an array using a long type.
		 * It needs to be an int type. Before converting to an int type, check
		 * to ensure that file is not larger than Integer.MAX_VALUE.
		 */
	    if (length > Integer.MAX_VALUE) {
	    	return null;
	    }
	    else {
	    	// Create the byte array to hold the data.
	    	byte[] bytes = new byte[(int)length];
	    	
	    	// Read in the bytes.
	    	int offset = 0;
	    	int numRead = 0;
	    	
	    	while (offset < bytes.length && (numRead = inputStream.read(bytes, offset, 
	    			bytes.length - offset)) >= 0) {
	    		offset += numRead;
	    	}
	    	
	    	// Ensure that all the bytes have been read in.
	    	if (offset < bytes.length) {
	    		throw new IOException("Could not completely read file " + file.getName());
	    	}
	    	
	    	// Close the input stream and return bytes.
	    	inputStream.close();
	    	return bytes;
	    }
	}
}
