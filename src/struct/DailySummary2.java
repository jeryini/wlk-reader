package struct;

import java.nio.ByteOrder;
import javolution.io.Struct;

/**
 * DailySummary2 class represents summary data of type 2 for each day in month.
 * The type 1 summary data is defined in DailySummary1 class.
 *
 * @author Jernej Jerin <jernej.jerin@gmail.com>
 * @version %I%, %G%
 * @since 1.0
 */
public class DailySummary2 extends Struct {
	/**
	 * Safety check to make sure we are reading correct record type.
	 * For daily summary 2 the dataType = 3.
	 */
	public final Signed8 dataType = new Signed8();
	
	/**
	 * This will cause the rest of the fields to start on an even address. 
	 */
	public final Signed8 reserved = new Signed8();
	
	/**
	 * Bitmapped weather conditions (Fog, T-Storm, Hurricane, etc).
	 * This field is not used now. 
	 */
	public final Unsigned16 todayWeather = new Unsigned16();
	
	/**
	 * The number of valid packets containing wind data, 
	 * this is used to indicate reception quality. 
	 */
	public final Unsigned16 numWindPackets = new Unsigned16();
	
	/**
	 * The highest solar energy in day is represented in
	 * watts per meter squared.
	 */
	public final Signed16 hiSolar = new Signed16();
	
	/**
	 * 1/10'th Ly. 
	 */
	public final Signed16 dailySolarEnergy = new Signed16();
	
	/**
	 * Number of accumulated minutes where the avg
	 * solar rad > 150.
	 */
	public final Signed16 minSunLight = new Signed16();
	
	/**
	 * Daily total of evapotranspiration represented
	 * in 1/1000'th of an inch. 
	 */
	public final Signed16 dailyETTotal = new Signed16();
	
	/**
	 * Highest Heat Index represented in tenths of a 
	 * degree F. 
	 */
	public final Signed16 hiHeat = new Signed16();
	
	/**
	 * Lowest Heat Index represented in tenths of a 
	 * degree F. 
	 */
	public final Signed16 lowHeat = new Signed16();
	
	/**
	 * Average Heat Index represented in tenths of a 
	 * degree F. 
	 */
	public final Signed16 avgHeat = new Signed16();
	
	/**
	 * Highest THSW Index represented in tenths of a 
	 * degree F. 
	 */
	public final Signed16 hiTHSW = new Signed16();
	
	/**
	 * Lowest THSW Index represented in tenths of a 
	 * degree F. 
	 */
	public final Signed16 lowTHSW = new Signed16();
	
	/**
	 * Highest THW Index represented in tenths of a 
	 * degree F. 
	 */
	public final Signed16 hiTHW = new Signed16();
	
	/**
	 * Lowest THW Index represented in tenths of a 
     * degree F. 
	 */
	public final Signed16 lowTHW = new Signed16();
	
	/**
	 * Integrated Heating Degree Days (65F threshold)
	 * represented in tenths of a degree F - Day. 
	 */
	public final Signed16 integratedHeatDD65 = new Signed16();
	
	/**
	 * Wet bulb values are not calculated.
	 * Tenths of a degree F.
	 */
	public final Signed16 hiWetBulb = new Signed16();
	
	/**
	 * Wet bulb values are not calculated.
	 * Tenths of a degree F. 
	 */
	public final Signed16 lowWetBulb = new Signed16();
	
	/**
	 * Wet bulb values are not calculated.
	 * Tenths of a degree F.
	 */
	public final Signed16 avgWetBulb = new Signed16();
	
	/**
	 * Space for 16 direction bins (Used to
	 * calculate monthly dominant Dir).
	 */
	public final Signed8[] dirBins = array(new Signed8[24]);
	
	/**
	 * Space for 10 time values:
	 * Index values for Daily Summary Record 2 time values 
	 * Time of High Solar Rad             0
	 * Time of High Outside Heat Index    1
	 * Time of Low Outside Heat Index     2
	 * Time of High Outside THSW Index    3
	 * Time of Low Outside THSW Index     4
	 * Time of High Outside THW Index     5
	 * Time of Low Outside THW Index      6
	 * Time of High Outside Wet Bulb Temp 7
	 * Time of Low Outside Wet Bulb Temp  8
	 * (Time value 9 is not used)
	 */
	public final Unsigned8[] timeValues = array(new Unsigned8[15]);
	
	/**
	 * Integrated Cooling Degree Days (65F threshold)
	 * represented in tenths of a degree F - Day.
	 */
	public final Signed16 integratedCoolDD65 = new Signed16();
	
	/**
	 * Reserved space. 
	 */
	public final Signed8[] reserved2 = array(new Signed8[11]);
	
	/** 
	 * We set the byte order for this struct to LITTLE ENDIAN.
	 * http://javolution.org/target/site/apidocs/javolution/io/Struct.html#byteOrder%28%29
	 */
	public ByteOrder byteOrder() {
		return ByteOrder.LITTLE_ENDIAN;
	}
	
	/**
	 * DailySummary2 is packed. That means no bytes are added to make
	 * the fields on the correct address boundaries. The alignment is 1.
	 * http://javolution.org/target/site/apidocs/javolution/io/Struct.html#isPacked%28%29
	 */
	public boolean isPacked() {
	    return true;
	}
}