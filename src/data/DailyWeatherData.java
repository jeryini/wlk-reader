/**
 * 
 */
package data;

import java.util.ArrayList;
import java.util.List;

/**
 * DailyWeatherData class has two main values. The first is daily summary which
 * contains statistical data. The second is a list of weather data records,
 * which contains data for every point in time.
 * 
 * @author Jernej Jerin <jernej.jerin@gmail.com>
 * @version %I%, %G%
 * @since 1.0
 */
public class DailyWeatherData {
	// Properties for daily summary and array of weather data records for each
	// day.
	DailySummary dailySummary;
	List<WeatherDataRecord> weatherDataRecords = new ArrayList<WeatherDataRecord>();

	/**
	 * Constructs daily weather data from daily summary and weather data
	 * records.
	 * 
	 * @param dailySummary
	 * @param weatherDataRecords
	 */
	public DailyWeatherData(DailySummary dailySummary,
			List<WeatherDataRecord> weatherDataRecords) {
		this.dailySummary = dailySummary;
		this.weatherDataRecords = weatherDataRecords;
	}

	/**
	 * @return the dailySummary
	 */
	public DailySummary getDailySummary() {
		return dailySummary;
	}

	/**
	 * @param dailySummary
	 *            the dailySummary to set
	 */
	public void setDailySummary(DailySummary dailySummary) {
		this.dailySummary = dailySummary;
	}

	/**
	 * @return the weatherDataRecords
	 */
	public List<WeatherDataRecord> getWeatherDataRecords() {
		return weatherDataRecords;
	}

	/**
	 * @param weatherDataRecords
	 *            the weatherDataRecords to set
	 */
	public void setWeatherDataRecords(List<WeatherDataRecord> weatherDataRecords) {
		this.weatherDataRecords = weatherDataRecords;
	}
}
