/**
 * 
 */
package data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jernej
 *
 */
public class DailyWeatherData {
	// Properties for daily summary and array of weather data records for each day.
	DailySummary dailySummary;
	List<WeatherDataRecord> weatherDataRecords = new ArrayList<WeatherDataRecord>();
	
	public DailyWeatherData(DailySummary dailySummary, List<WeatherDataRecord> weatherDataRecords) {
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
	 * @param dailySummary the dailySummary to set
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
	 * @param weatherDataRecords the weatherDataRecords to set
	 */
	public void setWeatherDataRecords(List<WeatherDataRecord> weatherDataRecords) {
		this.weatherDataRecords = weatherDataRecords;
	}
	
}
