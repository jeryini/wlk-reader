/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

import data.DailyWeatherData;

import reader.WlkReader;

/**
 * JUnit tests for testing reading *.wlk files and returning correct data
 * values.
 * 
 * @author Jernej Jerin <jernej.jerin@gmail.com>
 * @version %I%, %G%
 * @since 1.0
 */
public class WlkReaderTest {
	WlkReader wlkReaderTest, wlkReaderDateTimeTest, wlkReaderBooleanTest;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Create a reader where our two test files are located (current
		// directory). The first file represents winter month (february) while
		// the second file represents summer (august).
		URL resourceURL = getClass().getResource(".");
		wlkReaderTest = new WlkReader(new File(resourceURL.getPath()));
		wlkReaderDateTimeTest = new WlkReader(new File(resourceURL.getPath()),
				new DateTime(2012, 2, 5, 14, 0));
		wlkReaderBooleanTest = new WlkReader(new File(resourceURL.getPath()),
				false);
	}

	/**
	 * Test method for {@link reader.WlkReader#WlkReader(java.io.File)}.
	 */
	@Test
	public void testWlkReaderFile() {
		List<DailyWeatherData> dailyWeatherDataList = null;
		// First we get the data.
		try {
			dailyWeatherDataList = wlkReaderTest.readData();
		} catch (IllegalArgumentException | UnsupportedOperationException
				| ArithmeticException | IOException e) {
			// Failed because of exceptions.
			fail(e.getMessage());
		}

		// Check if list is empty.
		assertTrue("Returned list of weather data should not be empty!",
				!dailyWeatherDataList.isEmpty());

		// 29 (February) + August (31) should give 60 DailyWeatherData entities.
		assertEquals("Specified list is not of this size!", 60,
				dailyWeatherDataList.size());
	}

	/**
	 * Test method for
	 * {@link reader.WlkReader#WlkReader(java.io.File, org.joda.time.DateTime)}.
	 */
	@Test
	public void testWlkReaderFileDateTime() {
		List<DailyWeatherData> dailyWeatherDataList = null;
		// First we get the data.
		try {
			dailyWeatherDataList = wlkReaderDateTimeTest.readData();
		} catch (IllegalArgumentException | UnsupportedOperationException
				| ArithmeticException | IOException e) {
			// Failed because of exceptions.
			fail(e.getMessage());
		}
		// 25 (from 5th of February) + August (31) should give 56
		// DailyWeatherData entities.
		assertEquals("Specified list is not of this size!", 56,
				dailyWeatherDataList.size());

		// The time of the first weather data record should be specified time
		// plus 1 minute.
		assertEquals("Specified time is not equal!", new LocalTime(14, 1),
				dailyWeatherDataList.get(0).getWeatherDataRecords().get(0)
						.getTime());

		// Check for correct pressure reading at 5.2.2012 14:01.
		assertEquals("Specified pressure does not match!", 1030.5,
				dailyWeatherDataList.get(0).getWeatherDataRecords().get(0)
						.getPressure(), 0.1);

		// Check for correct air temperature reading at 5.2.2012 14:01.
		assertEquals("Specified outdoor temperature does not match!", -6.7,
				dailyWeatherDataList.get(0).getWeatherDataRecords().get(0)
						.getOutTemp(), 0.1);
	}

	/**
	 * Test method for {@link reader.WlkReader#WlkReader(java.io.File, boolean)}
	 * .
	 */
	@Test
	public void testWlkReaderFileBoolean() {
		List<DailyWeatherData> dailyWeatherDataList = null;
		// First we get the data.
		try {
			dailyWeatherDataList = wlkReaderBooleanTest.readData();
		} catch (IllegalArgumentException | UnsupportedOperationException
				| ArithmeticException | IOException e) {
			// Failed because of exceptions.
			fail(e.getMessage());
		}

		// Check for correct pressure reading at 1.2.2012 00:01.
		assertEquals("Specified pressure does not match!", 30.346,
				dailyWeatherDataList.get(0).getWeatherDataRecords().get(0)
						.getPressure(), 0.001);

		// Check for correct air temperature reading at 1.2.2012 00:01.
		assertEquals("Specified outdoor temperature does not match!", 23.8,
				dailyWeatherDataList.get(0).getWeatherDataRecords().get(0)
						.getOutTemp(), 0.1);
	}

	/**
	 * Test method for {@link reader.WlkReader#getBytesFromFile(java.io.File)}.
	 */
	@Test
	public void testGetBytesFromFile() {
		byte[] bytes = null;
		try {
			URL resourceURL = getClass().getResource("2012-02.wlk");
			bytes = WlkReader.getBytesFromFile(new File(resourceURL.getPath()));
		} catch (IOException e) {
			fail(e.getMessage());
		}

		// Check for length of the bytes.
		assertEquals("The length in bytes does not match!", 3680196,
				bytes.length, 1);
	}
}