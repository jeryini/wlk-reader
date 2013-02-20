/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import data.DataConverter;

/**
 * @author Jernej
 *
 */
public class DataConverterTest {
	DataConverter dataConverter, dataConverterUnit;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// We create environment for two different possibilities. The first is
		// without conversion to metric units while the second is also converting to
		// metric units.
		dataConverter = new DataConverter(false);
		dataConverterUnit = new DataConverter(true);
	}

	/**
	 * Test method for {@link data.DataConverter#convertPressure(java.lang.Short)}.
	 */
	@Test
	public void testConvertPressure() {
		// Check for correct null values if minimum value is passed.
		assertNull(dataConverter.convertPressure(Short.MIN_VALUE));
		
		// Check for correct value in imperial units.
		//assertEquals(expected, actual)
		
	}

	/**
	 * Test method for {@link data.DataConverter#convertDailyWindRun(java.lang.Short)}.
	 */
	@Test
	public void testConvertDailyWindRun() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.DataConverter#convertDailyRainTotal(java.lang.Short)}.
	 */
	@Test
	public void testConvertDailyRainTotal() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.DataConverter#convertTemperature(java.lang.Short)}.
	 */
	@Test
	public void testConvertTemperature() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.DataConverter#convertHumidity(java.lang.Short)}.
	 */
	@Test
	public void testConvertHumidity() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.DataConverter#convertWindSpeed(java.lang.Short)}.
	 */
	@Test
	public void testConvertWindSpeed() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.DataConverter#convertWindDirection(java.lang.Short)}.
	 */
	@Test
	public void testConvertWindDirection() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.DataConverter#convertRainRate(java.lang.Short)}.
	 */
	@Test
	public void testConvertRainRate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.DataConverter#convertUV(java.lang.Short)}.
	 */
	@Test
	public void testConvertUV() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.DataConverter#convertSolarEnergy(java.lang.Short)}.
	 */
	@Test
	public void testConvertSolarEnergy() {
		fail("Not yet implemented");
	}

}
