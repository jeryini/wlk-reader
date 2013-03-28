wlk-reader
==========

Structure and program for reading *.wlk files which store meteorological data for Davis Vantage Pro2 meteorological station.

Usage
-----

Create an instance from class WlkReader, where you can specify directory of the *.wlk files, start date and time from where onward to read, end date and time and whether you want unit conversion:

    WlkReader wlkReader = new WlkReader(new File("C:/davisvan"), new DateTime(2012, 2, 5, 14, 0), new DateTime(2012, 7, 23, 16, 30), true)

And then simply read data by calling method readData:

	List<DailyWeatherData> dailyWeatherDataList = wlkReader.readData();

The structure of the data is as following:
<ul>
<li>DailyWeatherData:
<ul>
<li>DailySummary</li>
<li>WeatherDataRecord</li>
</ul>
</li>
</ul>
    