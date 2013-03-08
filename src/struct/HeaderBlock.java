package struct;

import java.nio.ByteOrder;

import javolution.io.Struct;

/**
 * Header class represents header for each monthly file.
 * Razred GlavaBlok predstavlja glavo z
 * 
 * @author Jernej Jerin <jernej.jerin@gmail.com>
 * @version %I%, %G%
 * @since 1.0
 */
public class HeaderBlock extends Struct {
	/**
	 * The first 16 bytes are used to identify a weather database file
	 * and to identify diffrent file formats (used for converting 
	 * older database files).
	 */
	public final UTF8String idCode = new UTF8String(16);
	
	/**
	 * The total number of records for this month. The number depends
	 * on the selected archive interval recording. It also contains
	 * two additional records (daily summary) on a day. The size is 4 bytes.
	 */
	public final Signed32 totalRecords = new Signed32();
	
	/**
	 * An array of DayIndex, which contains index record for each day.
	 * Index 0 is not used (i.e. the 1'st is at index 1, not index 0).
	 * The size of one object is 6B.
	 */
	public final DayIndex[] dayIndex = array(new DayIndex[32]);
	
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
	 * HeaderBlock is packed. That means no bytes are added to make
	 * the fields on the correct address boundaries. The alignment is 1.
	 * We also need to define it in DayIndex because the directive is
	 * not inherited by inner structs.
	 * http://javolution.org/target/site/apidocs/javolution/io/Struct.html#isPacked%28%29
	 */
	public boolean isPacked() {
	    return true;
	}
}