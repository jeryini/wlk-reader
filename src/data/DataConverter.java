/**
 * 
 */
package data;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.measure.quantity.Length;
import javax.measure.quantity.Pressure;
import javax.measure.quantity.Temperature;
import javax.measure.quantity.Velocity;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

/**
 * Helper class for converting from decimal/mili representation of data to
 * integer. If user specified it also converts from imperial units (fahrenheit)
 * to metric units (celsius).
 * 
 * @author Jernej Jerin <jernej.jerin@gmail.com>
 * @version %I%, %G%
 * @since 1.0
 */
public class DataConverter {
	// Properties
	private boolean unit = true;

	// Units for conversion from imperial to metric system.
	static Unit<Pressure> HECTO_PASCAL = SI.HECTO(SI.PASCAL);
	static Unit<Pressure> INCH_OF_MERCURY_MILI = SI
			.MILLI(NonSI.INCH_OF_MERCURY);
	static Unit<Temperature> FAHRENHEIT = NonSI.FAHRENHEIT;
	static Unit<Temperature> FAHRENHEIT_DECI = SI.DECI(NonSI.FAHRENHEIT);
	static Unit<Temperature> CELSIUS = SI.CELSIUS;
	static Unit<Velocity> MPH_DECI = SI.DECI(NonSI.MILES_PER_HOUR);
	static Unit<Velocity> MPS = SI.METERS_PER_SECOND;
	static Unit<Length> MILIMETER = SI.MILLIMETER;
	static Unit<Length> INCH_MILI = SI.MILLI(NonSI.INCH);
	static Unit<Length> KILOMETER = SI.KILOMETER;
	static Unit<Length> MILE_DECI = SI.DECI(NonSI.MILE);

	/**
	 * Constructor.
	 * 
	 * @param unit
	 */
	public DataConverter(boolean unit) {
		this.unit = unit;
	}

	/**
	 * If unit conversion is disabled than it converts pressure in thousand
	 * inches of mercury to inches of mercury. If unit conversion is enabled
	 * then it converts thousand inches of mercury to hectopascal.
	 * 
	 * @param pressure
	 * @return converted pressure
	 */
	public Double convertPressure(Short pressure) {
		if (pressure == Short.MIN_VALUE) {
			return null;
		} else {
			// Is unit conversion enabled?
			if (this.unit) {
				// Conversion from imperial unit (inch of mercury mili) to
				// metric units (hecto pascal).
				return INCH_OF_MERCURY_MILI.getConverterTo(HECTO_PASCAL)
						.convert(pressure);
			} else {
				// Only division with 1e3.
				return (double) (pressure / 1e3);
			}
		}
	}

	/**
	 * If unit conversion is disabled than it converts windrun in deci miles to
	 * miles. If unit conversion is enabled then it converts deci miles to
	 * kilometers.
	 * 
	 * @param windRun
	 * @return converted windrun
	 */
	public Double convertWindRun(Short windRun) {
		if (windRun == Short.MIN_VALUE) {
			return null;
		} else {
			// Is unit conversion enabled?
			if (this.unit) {
				// Conversion from imperial unit (deci mile) to metric units
				// (kilometer).
				return MILE_DECI.getConverterTo(KILOMETER).convert(windRun);
			} else {
				// Only division with 1e1.
				return (double) (windRun / 1e1);
			}
		}
	}

	/**
	 * The precipitation which is represented in 1/1000'th of an inch.
	 * 
	 * @param precipitation
	 * @return converted precipitation
	 */
	public Double convertPrecipitation(Short precipitation) {
		if (precipitation == Short.MIN_VALUE) {
			return null;
		} else {
			// Is unit conversion enabled?
			if (this.unit) {
				// Conversion from imperial unit (inch) to metric units (mm).
				return INCH_MILI.getConverterTo(MILIMETER).convert(
						precipitation);
			} else {
				// Only division with 1e3.
				return (double) (precipitation / 1e3);
			}
		}
	}

	/**
	 * Converting temperature from deci fahrenheit to celsius.
	 * 
	 * @param temperature
	 * @return converted temperature
	 */
	public Double convertTemperature(Short temperature) {
		if (temperature == Short.MIN_VALUE) {
			return null;
		} else {
			// Is unit conversion enabled?
			if (this.unit) {
				// Conversion from imperial unit (fahrenheti deci) to metric
				// units (celsius).
				return FAHRENHEIT_DECI.getConverterTo(CELSIUS).convert(
						temperature);
			} else {
				// Only division with 1e1.
				return (double) (temperature / 1e1);
			}
		}
	}

	/**
	 * Converting humidity from deci humidity to integer humidity. Humidity is
	 * always represented in procentage.
	 * 
	 * @param humidity
	 * @return converted humidity
	 */
	public Integer convertHumidity(Short humidity) {
		if (humidity == Short.MIN_VALUE) {
			return null;
		} else {
			// Round to nearest integer.
			BigDecimal roundedHumidity = new BigDecimal((double) humidity / 10);
			roundedHumidity = roundedHumidity.setScale(0, RoundingMode.HALF_UP);
			return roundedHumidity.intValue();
		}
	}

	/**
	 * Converting wind speed of tenths mile per hour to meters per second.
	 * 
	 * @param windSpeed
	 * @return converted wind speed
	 */
	public Double convertWindSpeed(Short windSpeed) {
		if (windSpeed == Short.MIN_VALUE) {
			return null;
		} else {
			// Is unit conversion enabled?
			if (this.unit) {
				// Conversion from imperial unit (mph deci) to metric units
				// (m/s).
				return MPH_DECI.getConverterTo(MPS).convert(windSpeed);
			} else {
				// Only division with 1e1.
				return (double) (windSpeed / 1e1);
			}
		}
	}

	/**
	 * Converting wind direction code (0 - 15) to wind direction in degrees.
	 * 
	 * @param windDirection
	 * @return converted wind direction
	 */
	public Double convertWindDirection(Short windDirection) {
		// If wind direction is 255 then it means that the wind
		// speed was 0 and direction is undefined.
		if (windDirection == 255) {
			return null;
		} else {
			// Wind direction code multiplied by 22.5.
			return windDirection * 22.5;
		}
	}

	/**
	 * Conversion from tenths of an UV index to UV index.
	 * 
	 * @param UV
	 * @return converted UV index
	 */
	public Double convertUV(Short UV) {
		if (UV == Short.MIN_VALUE) {
			return null;
		} else {
			// No conversion only division.
			return UV / 1e1;
		}
	}

	/**
	 * Conversion from Ly (Langley) to J/m^2.
	 * 
	 * @param solarEnergy
	 * @return converted solar energy
	 */
	public Double convertSolarEnergy(Short solarEnergy) {
		if (solarEnergy == Short.MIN_VALUE) {
			return null;
		} else {
			// Is unit conversion enabled?
			if (this.unit) {
				// 1 Ly = 41840 J/m^2
				return (double) (solarEnergy * 4184);
			} else {
				// Only division with 1e1.
				return solarEnergy / 1e1;
			}
		}
	}
}
