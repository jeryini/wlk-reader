package struct;

import java.nio.ByteOrder;
import javolution.io.Struct;

/**
 * DailySummary1 class represents summary data of type 1 for each day in month.
 * It contains statistical data for some of the meteorology attributes.
 * The type 2 summary data is defined in DailySummary2 class.
 *
 * @author Jernej Jerin <jernej.jerin@gmail.com>
 * @version %I%, %G%
 * @since 1.0
 */
public class DailySummary1 extends Struct {
	
	/**
	 * Safety check to make sure we are reading correct record type.
	 * For daily summary 1 the dataType = 2.
	 */
	public final Signed8 dataType = new Signed8();
	
	/**
	 * This will cause the rest of the fields to start on an even address. 
	 */
	public final Signed8 reserved = new Signed8();
	
	/**
	 * Total number of minutes accounted for by physical records
	 * for this day. If for example we have 1min recording interval
	 * the value will be 0x05a0 = 1440.
	 */
	public final Signed16 dataSpan = new Signed16();
	
	/**
	 * Highest outside temperature of the day represented in
	 * tenths of a degree F.
	 */
	public final Signed16 hiOutTemp = new Signed16();
	
	/**
	 * Lowest outside temperature of the day represented in
	 * tenths of a degree F. 
	 */
	public final Signed16 lowOutTemp = new Signed16();
	
	/**
	 * Highest inside temperature of the day represented in
	 * tenths of a degree F.
	 */
	public final Signed16 hiInTemp = new Signed16();

	/**
	 * Lowest inside temperature of the day represented in
	 * tenths of a degree F.
	 */
	public final Signed16 lowInTemp = new Signed16();
	
	/**
	 * Average outside temperature of the day represented in
	 * tenths of a degree F (integrated over the day).
	 */
	public final Signed16 avgOutTemp = new Signed16();
	
	/**
	 * Average inside temperature of the day represented in
	 * tenths of a degree F (integrated over the day).
	 */
	public final Signed16 avgInTemp = new Signed16();
	
	/**
	 * Highest wind chill of the day represented in
	 * tenths of a degree F.
	 */
	public final Signed16 hiChill = new Signed16();
	
	/**
	 * Lowest wind chill of the day represented in
	 * tenths of a degree F. 
	 */
	public final Signed16 lowChill = new Signed16();
	
	/**
	 * Highest dewpoint of the day represented in
	 * tenths of a degree F. 
	 */
	public final Signed16 hiDew = new Signed16();
	
	/**
	 * Lowest dewpoint of the day represented in
	 * tenths of a degree F. 
	 */
	public final Signed16 lowDew = new Signed16();
	
	/**
	 * Average wind chill of the day represented in
	 * tenths of a degree F. 
	 */
	public final Signed16 avgChill = new Signed16();
	
	/**
	 * Average dewpoint of the day represented in
	 * tenths of a degree F. 
	 */
	public final Signed16 avgDew = new Signed16();
	
	/**
	 * Highest outside humidity of the day represented in
	 * tenths of a percent.
	 */
	public final Signed16 hiOutHum = new Signed16();
	
	/**
	 * Lowest outside humidity of the day represented in
	 * tenths of a percent. 
	 */
	public final Signed16 lowOutHum = new Signed16();
	
	/**
	 * Highest inside humidity of the day represented in
	 * tenths of a percent. 
	 */
	public final Signed16 hiInHum = new Signed16();
	
	/**
	 * Lowest inside humidity of the day represented in
	 * tenths of a percent. 
	 */
	public final Signed16 lowInHum = new Signed16();
	
	/**
	 * Average outside humidity of the day represented in
	 * tenths of a percent.
	 */
	public final Signed16 avgOutHum = new Signed16();
	
	/**
	 * Highest pressure of the day represented in
	 * thousandths of an inch Hg. 
	 */
	public final Signed16 hiBar = new Signed16();
	
	/**
	 * Lowest pressure of the day represented in
	 * thousandths of an inch Hg. 
	 */
	public final Signed16 lowBar = new Signed16();
	
	/**
	 * Average pressure of the day represented in
	 * thousandths of an inch Hg. 
	 */
	public final Signed16 avgBar = new Signed16();
	
	/**
	 * Highest wind speed of the day represented in
	 * tenths of an MPH. 
	 */
	public final Signed16 hiSpeed = new Signed16();
	
	/**
	 * Average wind speed of the day represented in
	 * tenths of an MPH. 
	 */
	public final Signed16 avgSpeed = new Signed16();
	
	/**
	 * Wind run of the day represented in
	 * 1/10'th of an mile. 
	 */
	public final Signed16 dailyWindRunTotal = new Signed16();
	
	/**
	 * The highest average 10min wind speed record in
	 * tenths of an MPH. 
	 */
	public final Signed16 hi10MinSpeed = new Signed16();
	
	/**
	 * Direction of the highest wind speed represented in
	 * direction code (0-15, 255). 
	 */
	public final Unsigned8 dirHiSpeed = new Unsigned8();
	
	/**
	 * Direction of the highest average 10min wind speed 
	 * represented in direction code (0-15, 255). 
	 */
	public final Unsigned8 hi10MinDir = new Unsigned8();
	
	/**
	 * The daily rain total represented in 1/1000'th
	 * of an inch. 
	 */
	public final Signed16 dailyRainTotal = new Signed16();
	
	/**
	 * The highest rain rate represented in 1/100'th inch/hr. 
	 */
	public final Signed16 hiRainRate = new Signed16();
	
	/**
	 * Daily UV dose represented in 1/10'th of a standard
	 * MED. 
	 */
	public final Signed16 dailyUVDose = new Signed16();
	
	/**
	 * UV Index represented in tenth of a UV Index. 
	 */
	public final Unsigned8 hiUV = new Unsigned8();
	
	/**
	 * Space for 18 time values:
	 * Index values for Daily Summary Record 1 time values:
	 * Time of High Outside Temperature   0
	 * Time of Low Outside Temperature    1
	 * Time of High Inside Temperature    2
	 * Time of Low Inside Temperature     3
	 * Time of High Wind Chill            4
	 * Time of Low Wind Chill             5
	 * Time of High Dew Point             6
	 * Time of Low Dew Point              7
	 * Time of High Outside Humidity      8
	 * Time of Low Outside Humidity       9
	 * Time of High Inside Humidity      10
	 * Time of Low Inside Humidity       11
	 * Time of High Barometer            12
	 * Time of Low Barometer             13
	 * Time of High Wind Speed           14
	 * Time of High Average Wind Speed   15
	 * Time of High Rain Rate            16
	 * Time of High UV                   17
	 */
	public final Unsigned8[] timeValues = array(new Unsigned8[27]);
	
	/**
	 * We set the byte order for this struct to LITTLE ENDIAN.
	 * We do not need to define byte order in DayIndex, because
	 * the byte order is inherited by inner structs.
	 * http://javolution.org/target/site/apidocs/javolution/io/Struct.html#byteOrder%28%29
	 */
	public ByteOrder byteOrder() {
		return ByteOrder.LITTLE_ENDIAN;
	}
	
	/**
	 * DailySummary1 is packed. That means no bytes are added to make
	 * the fields on the correct address boundaries. The alignment is 1.
	 * http://javolution.org/target/site/apidocs/javolution/io/Struct.html#isPacked%28%29
	 */
	public boolean isPacked() {
	    return true;
	}
}
