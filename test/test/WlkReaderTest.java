/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import reader.WlkReader;

/**
 * @author Jernej
 *
 */
public class WlkReaderTest {
	WlkReader wlkReaderTest;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Create a reader where our two test files are located (current directory).
		// The first file represents winter month (february) while the second file
		// represents summer (august).
		wlkReaderTest = new WlkReader(new File("C:/Users/Jernej/git/wlk-reader/test/test/"));
	}

	/**
	 * Test method for {@link reader.WlkReader#WlkReader(java.io.File)}.
	 */
	@Test
	public void testWlkReaderFile() {
		// Check if the list is being filled with weather data from files.
		try {
			assertTrue("Returned list of weather data should not be empty!", wlkReaderTest.readData().isEmpty());
		} catch (IllegalArgumentException | UnsupportedOperationException
				| ArithmeticException | IOException e) {
			// Failed because of exceptions.
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link reader.WlkReader#WlkReader(java.io.File, org.joda.time.DateTime)}.
	 */
	@Test
	public void testWlkReaderFileDateTime() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link reader.WlkReader#WlkReader(java.io.File, boolean)}.
	 */
	@Test
	public void testWlkReaderFileBoolean() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link reader.WlkReader#WlkReader(java.io.File, org.joda.time.DateTime, boolean)}.
	 */
	@Test
	public void testWlkReaderFileDateTimeBoolean() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link reader.WlkReader#getBytesFromFile(java.io.File)}.
	 */
	@Test
	public void testGetBytesFromFile() {
		//fail("Not yet implemented");
	}

}
