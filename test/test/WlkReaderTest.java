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
	WlkReader wlkReaderTest, wlkReaderDateTimeStartTest,
			wlkReaderDateTimeEndTest, wlkReaderDateTimeStartEndTest, wlkReaderBooleanTest;

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
		wlkReaderDateTimeStartTest = new WlkReader(new File(
				resourceURL.getPath()), new DateTime(2012, 2, 5, 14, 0), null);
		wlkReaderDateTimeEndTest = new WlkReader(
				new File(resourceURL.getPath()), null, new DateTime(2012, 8,
						25, 18, 30));
		wlkReaderDateTimeStartEndTest = new WlkReader(
				new File(resourceURL.getPath()), new DateTime(2012, 8,
						5, 22, 5), new DateTime(2012, 8,
						17, 2, 20));
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
	public void testWlkReaderFileDateTimeStart() {
		List<DailyWeatherData> dailyWeatherDataList = null;
		// First we get the data.
		try {
			dailyWeatherDataList = wlkReaderDateTimeStartTest.readData();
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
	 * Test method for
	 * {@link reader.WlkReader#WlkReader(java.io.File, org.joda.time.DateTime)}.
	 */
	@Test
	public void testWlkReaderFileDateTimeEnd() {
		List<DailyWeatherData> dailyWeatherDataList = null;
		// First we get the data.
		try {
			dailyWeatherDataList = wlkReaderDateTimeEndTest.readData();
		} catch (IllegalArgumentException | UnsupportedOperationException
				| ArithmeticException | IOException e) {
			// Failed because of exceptions.
			fail(e.getMessage());
		}
		// 29 (February) + 25 (to including 25th of August) should give 54
		// DailyWeatherData entities.
		assertEquals("Specified list is not of this size!", 54,
				dailyWeatherDataList.size());

		// The time of the last weather data record should be specified time
		// minus 1 minute.
		assertEquals(
				"Specified time is not equal!",
				new LocalTime(18, 29),
				dailyWeatherDataList
						.get(dailyWeatherDataList.size() - 1)
						.getWeatherDataRecords()
						.get(dailyWeatherDataList
								.get(dailyWeatherDataList.size() - 1)
								.getWeatherDataRecords().size() - 1).getTime());

		// Check for correct pressure reading at 25.8.2012 18:29.
		assertEquals("Specified pressure does not match!", 1007.6,
				dailyWeatherDataList
				.get(dailyWeatherDataList.size() - 1)
				.getWeatherDataRecords()
				.get(dailyWeatherDataList
						.get(dailyWeatherDataList.size() - 1)
						.getWeatherDataRecords().size() - 1)
						.getPressure(), 0.1);

		// Check for correct air temperature reading at 25.8.2012 18:29.
		assertEquals("Specified outdoor temperature does not match!", 34.4,
				dailyWeatherDataList
				.get(dailyWeatherDataList.size() - 1)
				.getWeatherDataRecords()
				.get(dailyWeatherDataList
						.get(dailyWeatherDataList.size() - 1)
						.getWeatherDataRecords().size() - 1)
						.getOutTemp(), 0.1);
	}
	
	/**
	 * Test method for
	 * {@link reader.WlkReader#WlkReader(java.io.File, org.joda.time.DateTime)}.
	 */
	@Test
	public void testWlkReaderFileDateTimeStartEnd() {
		List<DailyWeatherData> dailyWeatherDataList = null;
		// First we get the data.
		try {
			dailyWeatherDataList = wlkReaderDateTimeStartEndTest.readData();
		} catch (IllegalArgumentException | UnsupportedOperationException
				| ArithmeticException | IOException e) {
			// Failed because of exceptions.
			fail(e.getMessage());
		}
		// From 2012-08-05 to 2012-08-17 should give 13,
		// DailyWeatherData entities.
		assertEquals("Specified list is not of this size!", 13,
				dailyWeatherDataList.size());

		// The time of the last weather data record should be specified time
		// minus 1 minute.
		assertEquals(
				"Specified time is not equal!",
				new LocalTime(2, 19),
				dailyWeatherDataList
						.get(dailyWeatherDataList.size() - 1)
						.getWeatherDataRecords()
						.get(dailyWeatherDataList
								.get(dailyWeatherDataList.size() - 1)
								.getWeatherDataRecords().size() - 1).getTime());

		// Check for correct pressure reading at 2012-08-17 02:19.
		assertEquals("Specified pressure does not match!", 1018.8,
				dailyWeatherDataList
				.get(dailyWeatherDataList.size() - 1)
				.getWeatherDataRecords()
				.get(dailyWeatherDataList
						.get(dailyWeatherDataList.size() - 1)
						.getWeatherDataRecords().size() - 1)
						.getPressure(), 0.1);

		// Check for correct air temperature reading at 2012-08-17 02:19.
		assertEquals("Specified outdoor temperature does not match!", 17.6,
				dailyWeatherDataList
				.get(dailyWeatherDataList.size() - 1)
				.getWeatherDataRecords()
				.get(dailyWeatherDataList
						.get(dailyWeatherDataList.size() - 1)
						.getWeatherDataRecords().size() - 1)
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