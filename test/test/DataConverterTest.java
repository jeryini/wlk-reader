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
		// without conversion to metric units while the second is for conversion to
		// metric units.
		dataConverter = new DataConverter(false);
		dataConverterUnit = new DataConverter(true);
	}

	/**
	 * Test method for {@link data.DataConverter#convertPressure(java.lang.Short)}.
	 */
	@Test
	public void testConvertPressure() {
		// Check for correct null values if invalid value is passed.
		assertNull(dataConverter.convertPressure(Short.MIN_VALUE));
		
		// Check for correct value in imperial units.
		assertEquals("The converted value does not match!", 29.972, dataConverter.convertPressure((short) 29.972e3), 0.1);
		
		// Check for conversion to metric units.
		assertEquals("The converted value does not match!", 1015, dataConverterUnit.convertPressure((short) 29.972e3), 0.1);
		assertEquals("The converted value does not match!", 981.5, dataConverterUnit.convertPressure((short) 28.983e3), 0.1);
		assertEquals("The converted value does not match!", 1041.3, dataConverterUnit.convertPressure((short) 30.749e3), 0.1);
	}

	/**
	 * Test method for {@link data.DataConverter#convertWindRun(java.lang.Short)}.
	 */
	@Test
	public void testConvertWindRun() {
		// Check for correct null values if invalid value is passed.
		assertNull(dataConverter.convertWindRun(Short.MIN_VALUE));
		
		// Check for correct value in imperial units.
		assertEquals("The converted value does not match!", 567.5, dataConverter.convertWindRun((short) 567.5e1), 0.1);
		
		// Check for conversion to metric units.
		assertEquals("The converted value does not match!", 345.3, dataConverterUnit.convertWindRun((short) 214.6e1), 0.1);
		assertEquals("The converted value does not match!", 980.1, dataConverterUnit.convertWindRun((short) 609.0e1), 0.1);
		assertEquals("The converted value does not match!", 1350.9, dataConverterUnit.convertWindRun((short) 839.4e1), 0.1);
	}

	/**
	 * Test method for {@link data.DataConverter#convertPrecipitation(java.lang.Short)}.
	 */
	@Test
	public void testConvertPrecipitation() {
		// Check for correct null values if invalid value is passed.
		assertNull(dataConverter.convertPrecipitation(Short.MIN_VALUE));
		
		// Check for correct value in imperial units.
		assertEquals("The converted value does not match!", 1, dataConverter.convertPrecipitation((short) 1e3), 0.1);
		
		// Check for conversion to metric units.
		assertEquals("The converted value does not match!", 13.6, dataConverterUnit.convertPrecipitation((short) 0.534e3), 0.1);
		assertEquals("The converted value does not match!", 27.5, dataConverterUnit.convertPrecipitation((short) 1.082e3), 0.1);
		assertEquals("The converted value does not match!", 120, dataConverterUnit.convertPrecipitation((short) 4.724e3), 0.1);
	}

	/**
	 * Test method for {@link data.DataConverter#convertTemperature(java.lang.Short)}.
	 */
	@Test
	public void testConvertTemperature() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.DataConverter#convertHumidity(java.lang.Short)}.
	 */
	@Test
	public void testConvertHumidity() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.DataConverter#convertWindSpeed(java.lang.Short)}.
	 */
	@Test
	public void testConvertWindSpeed() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.DataConverter#convertWindDirection(java.lang.Short)}.
	 */
	@Test
	public void testConvertWindDirection() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.DataConverter#convertRainRate(java.lang.Short)}.
	 */
	@Test
	public void testConvertRainRate() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.DataConverter#convertUV(java.lang.Short)}.
	 */
	@Test
	public void testConvertUV() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link data.DataConverter#convertSolarEnergy(java.lang.Short)}.
	 */
	@Test
	public void testConvertSolarEnergy() {
		//fail("Not yet implemented");
	}

}
