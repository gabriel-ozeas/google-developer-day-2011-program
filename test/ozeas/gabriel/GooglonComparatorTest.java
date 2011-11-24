package ozeas.gabriel;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GooglonComparatorTest {
	
	private GooglonComparator comparator;
	
	@Before
	public void setUp() throws Exception {
		comparator = new GooglonComparator();
	}
	
	@Test
	public void compareOneChar() throws Exception {
		assertEquals(0, comparator.compare("d", "d"));
		assertEquals(1, comparator.compare("d", "b"));
		assertEquals(-1, comparator.compare("p", "v"));
	}
	
	@Test
	public void compareTwoChar() throws Exception {
		assertEquals(1, comparator.compare("dd", "db"));
		assertEquals(-1, comparator.compare("db", "dd"));
		assertEquals(0, comparator.compare("db", "db"));		
	}
	
	@Test
	public void compareDifferenceLength() throws Exception {
		assertEquals(1, comparator.compare("ddd", "db"));
		assertEquals(1, comparator.compare("dbd", "db"));
		assertEquals(-1, comparator.compare("db", "dbd"));
		assertEquals(-1, comparator.compare("jxf", "jxft"));
		assertEquals(1, comparator.compare("dbl", "jxft"));
	}
}
