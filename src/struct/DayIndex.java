package struct;

import javolution.io.Struct;

/**
 * Meta data for each day.
 * 
 * @author Jernej Jerin <jernej.jerin@gmail.com>
 * @version %I%, %G%
 * @since 1.0
 */
public class DayIndex extends Struct {
	/**
	 * Number of records per day plus two daily summary records.
	 */
	public final Signed16 recordsInDay = new Signed16();
	
	/**
	 * The index of the first daily summary record. From this we can compute
	 * offset of the daily summary record 1 for each day. 
	 */
	public final Signed32 startPos = new Signed32();
	
	/**
	 * DayIndex is packed. That means no bytes are added to make
	 * the fields on the correct address boundaries. The alignment is 1.
	 * http://javolution.org/target/site/apidocs/javolution/io/Struct.html#isPacked%28%29
	 */
	public boolean isPacked() {
        return true;
    }
}