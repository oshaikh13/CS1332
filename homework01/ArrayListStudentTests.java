import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * This is a basic set of unit tests for ArrayList. Passing these does
 * NOT guarantee any grade on this assignment. This is only a sanity check to
 * help you get started on the homework and writing JUnits in general.
 * 
 * @author CS 1332 TAs
 * @version 1.0
 */
public class ArrayListStudentTests {

    private ArrayList<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new ArrayList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsFront() {
        assertEquals(0, list.size());

        list.addToFront("0a"); // 0a
        list.addToFront("1a"); // 1a 0a
        list.addToFront("2a"); // 2a 1a 0a
        list.addToFront("3a"); // 3a 2a 1a 0a
        list.addToFront("4a"); // 4a 3a 2a 1a 0a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "4a";
        expected[1] = "3a";
        expected[2] = "2a";
        expected[3] = "1a";
        expected[4] = "0a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsBack() {
        assertEquals(0, list.size());

        list.addToBack("0a"); // 0a
        list.addToBack("1a"); // 0a 1a
        list.addToBack("2a"); // 0a 1a 2a
        list.addToBack("3a"); // 0a 1a 2a 3a

        assertEquals(4, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsGeneral() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "2a"); // 2a
        list.addAtIndex(0, "1a"); // 1a 2a
        list.addAtIndex(2, "4a"); // 1a 2a 4a
        list.addAtIndex(2, "3a"); // 1a 2a 3a 4a

        assertEquals(4, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "1a";
        expected[1] = "2a";
        expected[2] = "3a";
        expected[3] = "4a";
        for (int i = 4; i < ArrayList.INITIAL_CAPACITY; i++) {
            expected[i] = null;
        }
        
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsFront() {
        assertEquals(0, list.size());
        String temp = new String("0a"); // For equality checking.
        list.addAtIndex(0, temp); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a
        list.addAtIndex(5, "5a"); // 0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeFromFront()); // 1a 2a 3a 4a 5a

        assertEquals(5, list.size());
        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "1a";
        expected[1] = "2a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsBack() {
        assertEquals(0, list.size());
        String temp = new String("5a"); // For equality checking.
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a
        list.addAtIndex(5, temp); // 0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeFromBack()); // 0a 1a 2a 3a 4a

        assertEquals(5, list.size());
        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = null;
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsGeneral() {
        assertEquals(0, list.size());
        String temp = new String("2a"); // For equality checking.
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, temp); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a
        list.addAtIndex(5, "5a"); // 0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeAtIndex(2)); // 0a 1a 3a 4a 5a

        assertEquals(5, list.size());
        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        for (int i = 5; i < ArrayList.INITIAL_CAPACITY; i++) {
            expected[i] = null;
        }
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testGetGeneral() {
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmptyAndClear() {
        // Should be empty at initialization
        assertEquals(true, list.isEmpty());

        // Should not be empty after adding elements
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a
        assertEquals(false, list.isEmpty());

        // Clearing the list should empty the array and reset size
        list.clear();
        assertEquals(true, list.isEmpty());
        assertEquals(0, list.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
            list.getBackingArray());
    }

	@Test
	public void testListInit(){
		assertTrue(list.isEmpty());
		assertTrue(list.size() == 0);
	}
	
	
	@Test
	public void testAddElements(){
		list.addAtIndex(0, "Karol");
		list.addAtIndex(1, "Vanessa");
		list.addAtIndex(2, "Amanda");
		
		assertEquals("Karol", list.get(0));
		assertEquals("Vanessa", list.get(1));
		assertEquals("Amanda", list.get(2));
		
		list.addAtIndex(1, "Mariana");
		
		assertEquals("Karol", list.get(0));
		assertEquals("Mariana", list.get(1));
		assertEquals("Vanessa", list.get(2));
		assertEquals("Amanda", list.get(3));	
		
		assertTrue(list.size()==4);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddElementNull(){
		list.addAtIndex(0, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetElementNull(){
		list.addAtIndex(0, "Kheyla");
		list.addAtIndex(0, null);
	}
	
	@Test
	public void testSetElement(){
		list.addAtIndex(0, "Karol");
		list.addAtIndex(1, "Vanessa");
		list.addAtIndex(2, "Amanda");
		
		list.addAtIndex(1, "Livia");
		
		assertEquals("Karol", list.get(0));
		assertEquals("Livia", list.get(1));
		assertEquals("Amanda", list.get(3));
	}
	
	@Test
	public void testRemoveElement(){
		list.addAtIndex(0, "Karol");
		list.addAtIndex(1, "Vanessa");
		list.addAtIndex(2, "Amanda");
		
		assertEquals("Amanda", list.removeAtIndex(2));
		assertTrue(list.size() == 2);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testRemoveWithEmptyList(){
		list.removeAtIndex(0);
    }
    
	@Test (expected = IndexOutOfBoundsException.class)
	public void testRemoveOutOfBounds(){
		list.removeAtIndex(14);
    }

    @Test
    public void doublesCorrectly() {

        for (int i = 0; i < 13; i++) {
            if (Math.random() > .5)
                list.addAtIndex(i, i + "");
            else list.addToBack(i + "");
        }
        assertEquals(list.getBackingArray().length, 13);

        list.addToBack(14 + "");

        assertEquals(list.getBackingArray().length, 26);

    }

}
