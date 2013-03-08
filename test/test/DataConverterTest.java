/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import data.DataConverter;

/**
 * JUnit tests for testing conversion from *.wlk reader specific data format to
 * normal data format in imperial units or metric units.
 * 
 * @author Jernej Jerin <jernej.jerin@gmail.com>
 * @version %I%, %G%
 * @since 1.0
 */
public class DataConverterTest {
	DataConverter dataConverter, dataConverterUnit;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// We create environment for two different possibilities. The first is
		// without conversion to metric units while the second is for conversion
		// to metric units.
		dataConverter = new DataConverter(false);
		dataConverterUnit = new DataConverter(true);
	}

	/**
	 * Test method for
	 * {@link data.DataConverter#convertPressure(java.lang.Short)}.
	 */
	@Test
	public void testConvertPressure() {
		// Check for correct null values if invalid value is passed.
		assertNull(dataConverter.convertPressure(Short.MIN_VALUE));

		// Check for correct value in imperial units.
		assertEquals("The converted value does not match!", 29.972,
				dataConverter.convertPressure((short) 29.972e3), 0.1);

		// Check for conversion to metric units.
		assertEquals("The converted value does not match!", 1015,
				dataConverterUnit.convertPressure((short) 29.972e3), 0.1);
		assertEquals("The converted value does not match!", 981.5,
				dataConverterUnit.convertPressure((short) 28.983e3), 0.1);
		assertEquals("The converted value does not match!", 1041.3,
				dataConverterUnit.convertPressure((short) 30.749e3), 0.1);
	}

	/**
	 * Test method for
	 * {@link data.DataConverter#convertWindRun(java.lang.Short)}.
	 */
	@Test
	public void testConvertWindRun() {
		// Check for correct null values if invalid value is passed.
		assertNull(dataConverter.convertWindRun(Short.MIN_VALUE));

		// Check for correct value in imperial units.
		assertEquals("The converted value does not match!", 567.5,
				dataConverter.convertWindRun((short) 567.5e1), 0.1);

		// Check for conversion to metric units.
		assertEquals("The converted value does not match!", 345.3,
				dataConverterUnit.convertWindRun((short) 214.6e1), 0.1);
		assertEquals("The converted value does not match!", 980.1,
				dataConverterUnit.convertWindRun((short) 609.0e1), 0.1);
		assertEquals("The converted value does not match!", 1350.9,
				dataConverterUnit.convertWindRun((short) 839.4e1), 0.1);
	}

	/**
	 * Test method for
	 * {@link data.DataConverter#convertPrecipitation(java.lang.Short)}.
	 */
	@Test
	public void testConvertPrecipitation() {
		// Check for correct null values if invalid value is passed.
		assertNull(dataConverter.convertPrecipitation(Short.MIN_VALUE));

		// Check for correct value in imperial units.
		assertEquals("The converted value does not match!", 1,
				dataConverter.convertPrecipitation((short) 1e3), 0.1);

		// Check for conversion to metric units.
		assertEquals("The converted value does not match!", 13.6,
				dataConverterUnit.convertPrecipitation((short) 0.534e3), 0.1);
		assertEquals("The converted value does not match!", 27.5,
				dataConverterUnit.convertPrecipitation((short) 1.082e3), 0.1);
		assertEquals("The converted value does not match!", 120,
				dataConverterUnit.convertPrecipitation((short) 4.724e3), 0.1);
	}

	/**
	 * Test method for
	 * {@link data.DataConverter#convertTemperature(java.lang.Short)}.
	 */
	@Test
	public void testConvertTemperature() {
		// Check for correct null values if invalid value is passed.
		assertNull(dataConverter.convertTemperature(Short.MIN_VALUE));

		// Check for correct value in imperial units.
		assertEquals("The converted value does not match!", 50.5,
				dataConverter.convertTemperature((short) 50.5e1), 0.1);

		// Check for conversion to metric units.
		assertEquals("The converted value does not match!", -21.4,
				dataConverterUnit.convertTemperature((short) -6.5e1), 0.1);
		assertEquals("The converted value does not match!", 0,
				dataConverterUnit.convertTemperature((short) 32e1), 0.1);
		assertEquals("The converted value does not match!", 36.7,
				dataConverterUnit.convertTemperature((short) 98e1), 0.1);
	}

	/**
	 * Test method for
	 * {@link data.DataConverter#convertHumidity(java.lang.Short)}.
	 */
	@Test
	public void testConvertHumidity() {
		// Check for correct null values if invalid value is passed.
		assertNull(dataConverter.convertHumidity(Short.MIN_VALUE));

		// Check for correct value in metric units.
		assertEquals("The converted value does not match!", 51,
				dataConverter.convertHumidity((short) 50.9e1), 0.1);
	}

	/**
	 * Test method for
	 * {@link data.DataConverter#convertWindSpeed(java.lang.Short)}.
	 */
	@Test
	public void testConvertWindSpeed() {
		// Check for correct null values if invalid value is passed.
		assertNull(dataConverter.convertWindSpeed(Short.MIN_VALUE));

		// Check for correct value in imperial units.
		assertEquals("The converted value does not match!", 12.7,
				dataConverter.convertWindSpeed((short) 12.7e1), 0.1);

		// Check for conversion to metric units.
		assertEquals("The converted value does not match!", 1,
				dataConverterUnit.convertWindSpeed((short) 2.2e1), 0.1);
		assertEquals("The converted value does not match!", 5,
				dataConverterUnit.convertWindSpeed((short) 11.2e1), 0.1);
		assertEquals("The converted value does not match!", 15,
				dataConverterUnit.convertWindSpeed((short) 33.6e1), 0.1);
	}

	/**
	 * Test method for
	 * {@link data.DataConverter#convertWindDirection(java.lang.Short)}.
	 */
	@Test
	public void testConvertWindDirection() {
		// Check for correct null values if invalid value is passed.
		assertNull(dataConverter.convertWindDirection((short) 255));

		// Check for correct value in metric units.
		assertEquals("The converted value does not match!", 0,
				dataConverter.convertWindDirection((short) 0), 0.1);
		assertEquals("The converted value does not match!", 157.5,
				dataConverter.convertWindDirection((short) 7), 0.1);
	}

	/**
	 * Test method for {@link data.DataConverter#convertUV(java.lang.Short)}.
	 */
	@Test
	public void testConvertUV() {
		// Check for correct null values if invalid value is passed.
		assertNull(dataConverter.convertUV(Short.MIN_VALUE));

		// Check for correct value in metric units.
		assertEquals("The converted value does not match!", 5,
				dataConverter.convertUV((short) 5e1), 0.1);
	}

	/**
	 * Test method for
	 * {@link data.DataConverter#convertSolarEnergy(java.lang.Short)}.
	 */
	@Test
	public void testConvertSolarEnergy() {
		// Check for correct null values if invalid value is passed.
		assertNull(dataConverter.convertSolarEnergy(Short.MIN_VALUE));

		// Check for correct value in imperial units.
		assertEquals("The converted value does not match!", 1,
				dataConverter.convertSolarEnergy((short) 1e1), 0.1);

		// Check for conversion to metric units.
		assertEquals("The converted value does not match!", 41840,
				dataConverterUnit.convertSolarEnergy((short) 1e1), 0.1);
		assertEquals("The converted value does not match!", 104600,
				dataConverterUnit.convertSolarEnergy((short) 2.5e1), 0.1);
		assertEquals("The converted value does not match!", 209200,
				dataConverterUnit.convertSolarEnergy((short) 5e1), 0.1);
	}
}
