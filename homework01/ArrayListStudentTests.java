import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileInputStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


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

    @Test
    public void emptyLists () {
        assertEquals(list.removeFromBack(), null);
        assertEquals(list.removeFromFront(), null);
        assertEquals(list.size(), 0);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBackEmptyList() {
        assertEquals(0, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];

        assertEquals(null, list.removeFromBack());

        assertEquals(0, list.size());

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontEmptyList() {
        assertEquals(0, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];

        assertEquals(null, list.removeFromFront());

        assertEquals(0, list.size());

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testDoubleBackingArrayAddToBack() {
        assertEquals(0, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 2];
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY * 2; i++) {
            expected[i] = i + "";
            list.addToBack(i + "");
        }

        assertEquals(ArrayList.INITIAL_CAPACITY * 2, list.size());

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testDoubleBackingArrayAddToFront() {
        assertEquals(0, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 2];
        int j = ArrayList.INITIAL_CAPACITY * 2 - 1;
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY * 2; i++) {
            expected[j] = i + "";
            j--;
            list.addToFront(i + "");
        }

        assertEquals(ArrayList.INITIAL_CAPACITY * 2, list.size());

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testQuadBackingArrayAddToBack() {
        assertEquals(0, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 4];
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY * 4; i++) {
            expected[i] = i + "";
            list.addToBack(i + "");
        }

        assertEquals(ArrayList.INITIAL_CAPACITY * 4, list.size());

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testQuadBackingArrayAddToFront() {
        assertEquals(0, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 4];
        int j = ArrayList.INITIAL_CAPACITY * 4 - 1;
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY * 4; i++) {
            expected[j] = i + "";
            j--;
            list.addToFront(i + "");
        }

        assertEquals(ArrayList.INITIAL_CAPACITY * 4, list.size());

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexIndexOutOfBounds() {
        try {
            list.addAtIndex(-1, "Bad Add");
        } catch (IndexOutOfBoundsException e) {
            try {
                list.addAtIndex(list.size() + 1, "Bad Add");
            } catch (IndexOutOfBoundsException e2) {
                System.out.println(e2);
                return;
            }
            assertEquals("Throw an IndexOutOfBoundsException on index > size", "Did not throw IndexOutOfBoundsException");
        }
        assertEquals("Throw an IndexOutOfBoundsException on index < 0", "Did not throw IndexOutOfBoundsException");
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexIllegalArgument() {
        try {
            list.addAtIndex(0, null);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return;
        }
        assertEquals("Throw an IllegalArgumentException", "Did not throw IllegalArgumentException");
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFrontIllegalArgument() {
        try {
            list.addToFront( null);
        } catch (IllegalArgumentException e) {
            return;
        }
        assertEquals("Throw an IllegalArgumentException", "Did not throw IllegalArgumentException");
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBackIllegalArgument() {
        try {
            list.addToBack( null);
        } catch (IllegalArgumentException e) {
            return;
        }
        assertEquals("Throw an IllegalArgumentException", "Did not throw IllegalArgumentException");
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexIndexOutOfBounds() {
        try {
            list.removeAtIndex(-1);
        } catch (IndexOutOfBoundsException e) {
            try {
                list.removeAtIndex(list.size());
            } catch (IndexOutOfBoundsException e2) {
                return;
            }
            assertEquals("Throw an IndexOutOfBoundsException on index >= size", "Did not throw IndexOutOfBoundsException");
        }
        assertEquals("Throw an IndexOutOfBoundsException on index < 0", "Did not throw IndexOutOfBoundsException");
    }

    @Test(timeout = TIMEOUT)
    public void testGetIndexOutOfBounds() {
        try {
            list.get(-1);
        } catch (IndexOutOfBoundsException e) {
            try {
                list.get(list.size());
            } catch (IndexOutOfBoundsException e2) {
                System.out.println(e2);
                return;
            }
            assertEquals("Throw an IndexOutOfBoundsException on index >= size", "Did not throw IndexOutOfBoundsException");
        }
        assertEquals("Throw an IndexOutOfBoundsException on index < 0", "Did not throw IndexOutOfBoundsException");
    }

    @Test(timeout = 30000)
    public void testAddToBackEfficiency() {
        //  This test assumes INITIAL_CAPACITY to be set to 13.
        //  This test tries to quantify the efficiency of your addToBack
        //  method. You may not pass this test if you have a slower computer.
        //  My MacBook Pro has an i7 at 2.5 GHz. If you fail this test,
        //  that is not to say your method is not O(1). Be wary, read more about
        //  efficiency here! https://www.geeksforgeeks.org/analysis-algorithms-big-o-analysis/ (:

        assertEquals(0, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 16384];
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY * 16384; i++) {
            expected[i] = i + "";
            list.addToBack(i + "");
        }

        assertEquals(ArrayList.INITIAL_CAPACITY * 16384, list.size());

        assertArrayEquals(expected, list.getBackingArray());

    }

    private final String[] FORBIDDEN = {"package ", "System.arraycopy()", "clone()", "assert()",
            " new Arrays(", " new Array(", " new Thread(", " new Collections( ", "Collection.toArray()"};

    private final String[] FORBIDDEN_ITEMS = {"package", "System.arraycopy()", "clone()", "assert()",
            "Arrays", "Array", "Thread", "Collections", "Collection.toArray()"};

    @Test(timeout = 1000)
    public void scanForForbiddenUsages() {
        try {
            FileInputStream javaFile = new FileInputStream("./ArrayList.java");
            Scanner file = new Scanner(javaFile);
            int j = 1;
            boolean singleComment = false;
            boolean blockComment = false;
            while (file.hasNext()) {
                String line = file.nextLine();
                if (line.contains("//")) {
                    blockComment = true;
                } else if (line.contains("/**")) {
                    singleComment = true;
                }
                if (!singleComment && !blockComment) {
                    for (int i = 0; i < FORBIDDEN.length; i++) {
                        if (line.contains(FORBIDDEN[i])) {
                            assertEquals("Illegal use of " + FORBIDDEN_ITEMS[i], "In line " + j);
                        }
                    }
                }
                singleComment = false;
                j++;
                if (line.contains("*/")) {
                    blockComment = false;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            assertEquals("", " Could not find ArrayList.java. Please make sure it is saved with the " +
                    "correct name and located in the same path.");
        }
    }

    @Test
    public void testAdd() {
        list.addAtIndex(0, "b");
        list.addToBack("c");
        list.addToBack("e");
        list.addAtIndex(2, "d");
        list.addToFront("a");
        String[] exp = {"a", "b", "c", "d", "e"};
        for (int i = 0; i < 5; i++) {
            assertEquals(exp[i], list.get(i));
        }
    }

    @Test
    public void testRemove() {
        list.addToFront("a");
        list.addToBack("b");
        list.addToBack("c");
        list.addToBack("d");
        list.addToBack("e");
        String[] exp = {"a", "e", "d"};
        String[] act = new String[3];
        act[0] = list.removeFromFront();
        act[1] = list.removeFromBack();
        act[2] = list.removeAtIndex(2);
        assertArrayEquals(exp, act);
    }

    @Test
    public void testRegrow() {
        for (int i = 0; i < list.INITIAL_CAPACITY; i++) {
            list.addToBack(Integer.toString(i));
        }
        String[] exp = new String[list.getBackingArray().length * 2];
        list.addToBack("a");
        for (int i = 0; i < list.size() - 1; i++) {
            exp[i] = Integer.toString(i);
        }
        exp[list.size() - 1] = "a";
        assertEquals(list.size(), list.INITIAL_CAPACITY + 1);
        assertArrayEquals(exp, list.getBackingArray());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testClear() {
        for (int i = 0; i < list.INITIAL_CAPACITY; i++) {
            list.addToBack(Integer.toString(i));
        }
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
        list.get(0);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.addToBack("a");
        assertFalse(list.isEmpty());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testAddIndexGreaterThanSize() {
        for (int i = 0; i < list.INITIAL_CAPACITY; i++) {
            list.addToBack(Integer.toString(i));
        }
        list.addAtIndex(list.INITIAL_CAPACITY + 1, "a");
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testAddNegativeIndex() {
        list.addAtIndex(-1, "a");
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testGetIndexGreaterThanSize() {
        for (int i = 0; i < list.INITIAL_CAPACITY; i++) {
            list.addToBack(Integer.toString(i));
        }
        list.get(list.INITIAL_CAPACITY + 1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testGetNegativeIndex() {
        for (int i = 0; i < list.INITIAL_CAPACITY; i++) {
            list.addToBack(Integer.toString(i));
        }
        list.get(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullAdd() {
        list.addToFront(null);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testRemoveIndexGreaterThanSize() {
        for (int i = 0; i < list.INITIAL_CAPACITY; i++) {
            list.addToBack(Integer.toString(i));
        }
        list.removeAtIndex(list.INITIAL_CAPACITY + 1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testRemoveNegativeIndex() {
        for (int i = 0; i < list.INITIAL_CAPACITY; i++) {
            list.addToBack(Integer.toString(i));
        }
        list.removeAtIndex(-1);
    }

}
