import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import javax.accessibility.AccessibleAttributeSequence;

/**
 * This is a basic set of unit tests for DoublyLinkedList.
 * 
 * Passing these tests doesn't guarantee any grade on these assignments. These
 * student JUnits that we provide should be thought of as a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * We highly encourage you to write your own set of JUnits for each homework
 * to cover edge cases you can think of for each data structure. Your code must
 * work correctly and efficiently in all cases, which is why it's important
 * to write comprehensive tests to cover as many cases as possible.
 *
 * @author The 1332 TAs
 * @version 1.0
 */
public class LinkedListStudentTests {
    private DoublyLinkedList<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new DoublyLinkedList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsIndexA() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a

        assertEquals(4, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertNull(current.getPrevious());
        assertEquals("0a", current.getData());

        LinkedListNode<String> prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("1a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("2a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("3a", current.getData());
        assertSame(list.getTail(), current);

        current = current.getNext();
        assertNull(current);
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsFrontA() {
        assertEquals(0, list.size());

        list.addToFront("0a"); // 0a
        list.addToFront("1a"); // 1a 0a
        list.addToFront("2a"); // 2a 1a 0a
        list.addToFront("3a"); // 3a 2a 1a 0a
        list.addToFront("4a"); // 4a 3a 2a 1a 0a
        list.addToFront("5a"); // 5a 4a 3a 2a 1a 0a

        assertEquals(6, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertNull(current.getPrevious());
        assertEquals("5a", current.getData());

        LinkedListNode<String> prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("4a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("3a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("2a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("1a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("0a", current.getData());
        assertSame(list.getTail(), current);

        current = current.getNext();
        assertNull(current);
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsBackA() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addToBack("0a"); //0a
        list.addToBack("1a"); //0a 1a
        list.addToBack("2a"); //0a 1a 2a
        list.addToBack("3a"); //0a 1a 2a 3a

        assertEquals(4, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertNull(current.getPrevious());
        assertEquals("0a", current.getData());

        LinkedListNode<String> prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("1a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("2a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("3a", current.getData());
        assertSame(list.getTail(), current);

        current = current.getNext();
        assertNull(current);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsIndexA() {
        assertEquals(0, list.size());
        String temp = new String("2a");
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, temp); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a
        list.addAtIndex(5, "5a"); // 0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals(temp, list.removeAtIndex(2)); // 0a 1a 3a 4a 5a

        assertEquals(5, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertNull(current.getPrevious());
        assertEquals("0a", current.getData());

        LinkedListNode<String> prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("1a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("3a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("4a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("5a", current.getData());
        assertSame(list.getTail(), current);

        current = current.getNext();
        assertNull(current);

        list.removeAtIndex(1);
        list.removeAtIndex(1);
        list.removeAtIndex(1);
        list.removeAtIndex(1);

        assertEquals(list.getHead(), list.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsFrontA() {
        assertEquals(0, list.size());
        String temp = new String("0a");
        list.addToBack(temp); // 0a
        list.addToBack("1a"); // 0a 1a
        list.addToBack("2a"); // 0a 1a 2a
        list.addToBack("3a"); // 0a 1a 2a 3a

        assertEquals(4, list.size());

        assertEquals(temp, list.removeFromFront()); // 1a 2a 3a

        assertEquals(3, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertNull(current.getPrevious());
        assertEquals("1a", current.getData());

        LinkedListNode<String> prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("2a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("3a", current.getData());
        assertSame(list.getTail(), current);

        current = current.getNext();
        assertNull(current);

        list.removeFromFront();
        list.removeFromFront();
        list.removeFromFront();

        assertEquals(list.getHead(), list.getTail());

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsBackA() {
        assertEquals(0, list.size());
        String temp = new String("3a");
        list.addToBack("0a"); // 0a
        list.addToBack("1a"); // 0a 1a
        list.addToBack("2a"); // 0a 1a 2a
        list.addToBack(temp); // 0a 1a 2a 3a

        assertEquals(4, list.size());

        assertEquals(temp, list.removeFromBack()); // 0a 1a 2a

        assertEquals(3, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertNull(current.getPrevious());
        assertEquals("0a", current.getData());

        LinkedListNode<String> prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("1a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("2a", current.getData());
        assertSame(list.getTail(), current);

        current = current.getNext();
        assertNull(current);

        list.removeFromBack();
        list.removeFromBack();
        // list.removeFromBack();

        assertEquals(list.getHead(), list.getTail());
        
        list.removeFromBack();
        assertEquals(list.getHead(), list.getTail());


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
    public void emptyLists () {
        assertEquals(list.removeFromBack(), null);
        assertEquals(list.removeFromFront(), null);
        assertEquals(list.size(), 0);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBackEmptyList() {
        assertEquals(0, list.size());

        Object[] expected = {};

        assertEquals(null, list.removeFromBack());

        assertEquals(0, list.size());

        assertArrayEquals(expected, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontEmptyList() {
        assertEquals(0, list.size());

        Object[] expected = {};

        assertEquals(null, list.removeFromFront());

        assertEquals(0, list.size());

        assertArrayEquals(expected, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexIndexOutOfBounds() {
        try {
            list.addAtIndex(-1, "Bad Add");
        } catch (IndexOutOfBoundsException e) {
            try {
                list.addAtIndex(list.size() + 1, "Bad Add");
            } catch (IndexOutOfBoundsException e2) {
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
                return;
            }
            assertEquals("Throw an IndexOutOfBoundsException on index >= size", "Did not throw IndexOutOfBoundsException");
        }
        assertEquals("Throw an IndexOutOfBoundsException on index < 0", "Did not throw IndexOutOfBoundsException");
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
        list.removeFromFront();
        assertEquals(list.getHead(), list.getTail());
        list.removeAtIndex(0);
        assertEquals(list.getHead(), list.getTail());

    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void testClear() {
        for (int i = 0; i < 13; i++) {
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
        for (int i = 0; i < 13; i++) {
            list.addToBack(Integer.toString(i));
        }
        list.addAtIndex(13 + 1, "a");
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testAddNegativeIndex() {
        list.addAtIndex(-1, "a");
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testGetIndexGreaterThanSize() {
        for (int i = 0; i < 13; i++) {
            list.addToBack(Integer.toString(i));
        }
        list.get(13 + 1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testGetNegativeIndex() {
        for (int i = 0; i < 13; i++) {
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
        for (int i = 0; i < 13; i++) {
            list.addToBack(Integer.toString(i));
        }
        list.removeAtIndex(13 + 1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testRemoveNegativeIndex() {
        for (int i = 0; i < 13; i++) {
            list.addToBack(Integer.toString(i));
        }
        list.removeAtIndex(-1);
    }

    @Test
    public void testLastOccurrence() {
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, new String("3a")); // 0a 1a 2a 3a
        list.addAtIndex(4, new String("3a")); // 0a 1a 2a 3a 3a
        list.addAtIndex(5, "4a"); // 0a 1a 2a 3a 3a 4a

        assertEquals(0, list.lastOccurrence(new String("0a")));
        assertEquals(1, list.lastOccurrence(new String("1a")));
        assertEquals(2, list.lastOccurrence(new String("2a")));
        assertEquals(4, list.lastOccurrence(new String("3a")));
        assertEquals(5, list.lastOccurrence(new String("4a")));
        assertEquals(-1, list.lastOccurrence(new String("lmao")));
    }
    
    @Test
    public void addAtIndex() {
        for (int i = 0; i < 10; i++) {
            list.addAtIndex(i, "Initial String #" + i);
        }
        list.addAtIndex(3, "String added at index 3");
        list.addAtIndex(7, "String added at index 7");
        list.addAtIndex(9, "String added at index 9");
        assertEquals(13, list.size());
        assertArrayEquals(list.toArray(), new String[]{
            "Initial String #0", "Initial String #1", "Initial String #2",
            "String added at index 3", "Initial String #3",
            "Initial String #4", "Initial String #5",
            "String added at index 7", "Initial String #6",
            "String added at index 9", "Initial String #7",
            "Initial String #8", "Initial String #9"
        });
        list.addAtIndex(0, "Beginning String");
        assertEquals("Beginning String", list.getHead().getData());
        assertEquals("Initial String #9", list.getTail().getData());
        assertEquals(14, list.size());
        assertEquals("Beginning String", list.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAtIndexNegativeIndex() {
        list.addAtIndex(-1, "Test");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAtIndexLargeIndex() {
        list.addToFront("Test");
        list.addAtIndex(1, "Legal Index");
        list.addAtIndex(3, "Illegal Index");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAtIndexNullData() {
        list.addAtIndex(0, null);
    }

    @Test
    public void addToFront() {
        list.addToFront("First added");
        assertEquals(list.getHead(), list.getTail());
        list.addToFront("Second added");
        list.addToFront("Third added");
        list.addToFront("Last added");
        assertEquals("Last added", list.getHead().getData());
        assertEquals("First added", list.getTail().getData());
        assertEquals("Third added", list.get(1));
        assertEquals(4, list.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToFrontNullData() {
        list.addToFront(null);
    }

    @Test
    public void addToBack() {
        list.addToBack("First added");
        assertEquals(list.getHead(), list.getTail());
        list.addToBack("Second added");
        list.addToBack("Third added");
        list.addToBack("Last added");
        assertEquals("Last added", list.getTail().getData());
        assertEquals("First added", list.getHead().getData());
        assertEquals("Second added", list.get(1));
        assertEquals(4, list.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToBackNullData() {
        list.addToBack(null);
    }

    @Test
    public void removeAtIndex() {
        list.addToBack("1");
        list.addToBack("2");
        list.addToBack("3");
        assertEquals("1", list.removeAtIndex(0));
        assertEquals("3", list.removeAtIndex(1));
        list.addToBack("4");
        list.addToBack("6");
        assertEquals("4", list.removeAtIndex(1));
        assertEquals(2, list.size());
        assertEquals("6", list.removeAtIndex(1));
        assertEquals(list.getHead(), list.getTail());
        assertEquals("2", list.removeAtIndex(0));
        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.getTail());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeAtIndexNegative() {
        list.removeAtIndex(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeAtIndexLarge() {
        list.addToFront("Test");
        list.removeAtIndex(1);
    }

    @Test
    public void removeFromFront() {
        list.addToBack("Element 0");
        list.addToBack("Element 1");
        list.addToBack("Element 2");
        assertEquals("Element 0", list.removeFromFront());
        assertEquals("Element 1", list.removeFromFront());
        assertEquals("Element 2", list.removeFromFront());
        assertNull(list.removeFromFront());
    }

    @Test
    public void removeFromBack() {
        list.addToBack("Element 0");
        list.addToBack("Element 1");
        list.addToBack("Element 2");
        assertEquals("Element 2", list.removeFromBack());
        assertEquals("Element 1", list.removeFromBack());
        assertEquals("Element 0", list.removeFromBack());
        assertNull(list.removeFromBack());
    }

    @Test
    public void lastOccurrence() {
        for (int i = 0; i < 10; i++) {
            list.addToBack("Element " + i);
        }
        assertEquals(5, list.lastOccurrence("Element 5"));
        assertEquals(7, list.lastOccurrence("Element 7"));
        list.addToFront("Element 5");
        assertEquals(6, list.lastOccurrence("Element 5"));
        list.addToBack("Element 6");
        assertEquals(11, list.lastOccurrence("Element 6"));
        assertEquals(-1, list.lastOccurrence("String that does not exist"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void lastOccurrenceNull() {
        list.lastOccurrence(null);
    }

    @Test
    public void get() {
        for (int i = 0; i < 10; i++) {
            list.addToBack("Element " + i);
        }
        assertEquals("Element 2", list.get(2));
        assertEquals("Element 0", list.get(0));
        assertEquals("Element 9", list.get(9));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getNegative() {
        list.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getLarge() {
        list.addToFront("Test");
        list.get(1);
    }

    @Test
    public void toArray() {
        assertArrayEquals(list.toArray(), new String[]{});
        for (int i = 0; i < 10; i++) {
            list.addToBack("" + i);
        }
        assertArrayEquals(list.toArray(), new String[]{
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
        });
        list.removeFromFront();
        assertArrayEquals(list.toArray(), new String[]{
            "1", "2", "3", "4", "5", "6", "7", "8", "9"
        });
        list.addToBack("10");
        assertArrayEquals(list.toArray(), new String[]{
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
        });
    }

    @Test
    public void isEmpty() {
        assertTrue(list.isEmpty());
        list.addToBack("Test");
        assertFalse(list.isEmpty());
        list.removeFromFront();
        assertTrue(list.isEmpty());
    }

    @Test
    public void clear() {
        assertTrue(list.isEmpty());
        for (int i = 0; i < 10; i++) {
            list.addToBack("" + i);
        }
        list.clear();
        assertTrue(list.isEmpty());
        assertNull(list.getHead());
        assertNull(list.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsIndex() {
      assertEquals(0, list.size());
      assertNull(list.getHead());

      list.addAtIndex(0, "0a"); // 0a
      list.addAtIndex(1, "1a"); // 0a 1a
      list.addAtIndex(2, "2a"); // 0a 1a 2a
      list.addAtIndex(3, "3a"); // 0a 1a 2a 3a

      assertEquals(4, list.size());

      LinkedListNode<String> current = list.getHead();
      assertNotNull(current);
      assertNull(current.getPrevious());
      assertEquals("0a", current.getData());

      LinkedListNode<String> prev = current;
      current = current.getNext();
      assertNotNull(current);
      assertSame(prev, current.getPrevious());
      assertEquals("1a", current.getData());

      prev = current;
      current = current.getNext();
      assertNotNull(current);
      assertSame(prev, current.getPrevious());
      assertEquals("2a", current.getData());

      prev = current;
      current = current.getNext();
      assertNotNull(current);
      assertSame(prev, current.getPrevious());
      assertEquals("3a", current.getData());
      assertSame(list.getTail(), current);

      current = current.getNext();
      assertNull(current);
  }

  @Test(timeout = TIMEOUT)
  public void testAddStringsFront() {
      assertEquals(0, list.size());

      list.addToFront("0a"); // 0a
      list.addToFront("1a"); // 1a 0a
      list.addToFront("2a"); // 2a 1a 0a
      list.addToFront("3a"); // 3a 2a 1a 0a
      list.addToFront("4a"); // 4a 3a 2a 1a 0a
      list.addToFront("5a"); // 5a 4a 3a 2a 1a 0a

      assertEquals(6, list.size());

      LinkedListNode<String> current = list.getHead();
      assertNotNull(current);
      assertNull(current.getPrevious());
      assertEquals("5a", current.getData());

      LinkedListNode<String> prev = current;
      current = current.getNext();
      assertNotNull(current);
      assertSame(prev, current.getPrevious());
      assertEquals("4a", current.getData());

      prev = current;
      current = current.getNext();
      assertNotNull(current);
      assertSame(prev, current.getPrevious());
      assertEquals("3a", current.getData());

      prev = current;
      current = current.getNext();
      assertNotNull(current);
      assertSame(prev, current.getPrevious());
      assertEquals("2a", current.getData());

      prev = current;
      current = current.getNext();
      assertNotNull(current);
      assertSame(prev, current.getPrevious());
      assertEquals("1a", current.getData());

      prev = current;
      current = current.getNext();
      assertNotNull(current);
      assertSame(prev, current.getPrevious());
      assertEquals("0a", current.getData());
      assertSame(list.getTail(), current);

      current = current.getNext();
      assertNull(current);
  }

  @Test(timeout = TIMEOUT)
  public void testAddStringsBack() {
      assertEquals(0, list.size());
      assertNull(list.getHead());

      list.addToBack("0a"); //0a
      list.addToBack("1a"); //0a 1a
      list.addToBack("2a"); //0a 1a 2a
      list.addToBack("3a"); //0a 1a 2a 3a

      assertEquals(4, list.size());

      LinkedListNode<String> current = list.getHead();
      assertNotNull(current);
      assertNull(current.getPrevious());
      assertEquals("0a", current.getData());

      LinkedListNode<String> prev = current;
      current = current.getNext();
      assertNotNull(current);
      assertSame(prev, current.getPrevious());
      assertEquals("1a", current.getData());

      prev = current;
      current = current.getNext();
      assertNotNull(current);
      assertSame(prev, current.getPrevious());
      assertEquals("2a", current.getData());

      prev = current;
      current = current.getNext();
      assertNotNull(current);
      assertSame(prev, current.getPrevious());
      assertEquals("3a", current.getData());
      assertSame(list.getTail(), current);

      current = current.getNext();
      assertNull(current);
  }

  @Test(timeout = TIMEOUT)
  public void testRemoveStringsIndex() {
      assertEquals(0, list.size());
      String temp = new String("2a");
      list.addAtIndex(0, "0a"); // 0a
      list.addAtIndex(1, "1a"); // 0a 1a
      list.addAtIndex(2, temp); // 0a 1a 2a
      list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
      list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a
      list.addAtIndex(5, "5a"); // 0a 1a 2a 3a 4a 5a

      assertEquals(6, list.size());

      assertEquals(temp, list.removeAtIndex(2)); // 0a 1a 3a 4a 5a

      assertEquals(5, list.size());

      LinkedListNode<String> current = list.getHead();
      assertNotNull(current);
      assertNull(current.getPrevious());
      assertEquals("0a", current.getData());

      LinkedListNode<String> prev = current;
      current = current.getNext();
      assertNotNull(current);
      assertSame(prev, current.getPrevious());
      assertEquals("1a", current.getData());

      prev = current;
      current = current.getNext();
      assertNotNull(current);
      assertSame(prev, current.getPrevious());
      assertEquals("3a", current.getData());

      prev = current;
      current = current.getNext();
      assertNotNull(current);
      assertSame(prev, current.getPrevious());
      assertEquals("4a", current.getData());

      prev = current;
      current = current.getNext();
      assertNotNull(current);
      assertSame(prev, current.getPrevious());
      assertEquals("5a", current.getData());
      assertSame(list.getTail(), current);

      current = current.getNext();
      assertNull(current);
  }

  @Test(timeout = TIMEOUT)
  public void testRemoveStringsFront() {
      assertEquals(0, list.size());
      String temp = new String("0a");
      list.addToBack(temp); // 0a
      list.addToBack("1a"); // 0a 1a
      list.addToBack("2a"); // 0a 1a 2a
      list.addToBack("3a"); // 0a 1a 2a 3a

      assertEquals(4, list.size());

      assertEquals(temp, list.removeFromFront()); // 1a 2a 3a

      assertEquals(3, list.size());

      LinkedListNode<String> current = list.getHead();
      assertNotNull(current);
      assertNull(current.getPrevious());
      assertEquals("1a", current.getData());

      LinkedListNode<String> prev = current;
      current = current.getNext();
      assertNotNull(current);
      assertSame(prev, current.getPrevious());
      assertEquals("2a", current.getData());

      prev = current;
      current = current.getNext();
      assertNotNull(current);
      assertSame(prev, current.getPrevious());
      assertEquals("3a", current.getData());
      assertSame(list.getTail(), current);

      current = current.getNext();
      assertNull(current);
  }

  @Test(timeout = TIMEOUT)
  public void testRemoveStringsBack() {
      assertEquals(0, list.size());
      String temp = new String("3a");
      list.addToBack("0a"); // 0a
      list.addToBack("1a"); // 0a 1a
      list.addToBack("2a"); // 0a 1a 2a
      list.addToBack(temp); // 0a 1a 2a 3a

      assertEquals(4, list.size());

      assertEquals(temp, list.removeFromBack()); // 0a 1a 2a

      assertEquals(3, list.size());

      LinkedListNode<String> current = list.getHead();
      assertNotNull(current);
      assertNull(current.getPrevious());
      assertEquals("0a", current.getData());

      LinkedListNode<String> prev = current;
      current = current.getNext();
      assertNotNull(current);
      assertSame(prev, current.getPrevious());
      assertEquals("1a", current.getData());

      prev = current;
      current = current.getNext();
      assertNotNull(current);
      assertSame(prev, current.getPrevious());
      assertEquals("2a", current.getData());
      assertSame(list.getTail(), current);

      current = current.getNext();
      assertNull(current);
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
  public void testToArray() {
      String[] expectedItems = new String[10];

      for (int x = 0; x < expectedItems.length; x++) {
          expectedItems[x] = x + "a";
          list.addToBack(expectedItems[x]);
      }

      Object[] array = list.toArray();
      assertArrayEquals(expectedItems, array);
  }

  @Test(timeout = TIMEOUT)
  public void testClearIsEmpty() {
      list.addAtIndex(0, "0a"); // 0a
      list.addAtIndex(1, "1a"); // 0a 1a
      list.addAtIndex(2, "2a"); // 0a 1a 2a
      list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
      list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a

      list.clear();
      assertEquals(0, list.size());
      assertNull(list.getHead());
      assertNull(list.getTail());
      assertTrue(list.isEmpty());
  }


  /*
   * Following tests added by Alex Gurung, rest were created by the 1332 TAs
   */
  

  @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
  public void testAddAtIndexLowerIndexOutOfBoundsException() {
      list.addAtIndex(-1, "0a");
  }
  
  @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
  public void testAddAtIndexUpperIndexOutOfBoundsException() {
      list.clear();
      assertEquals(0, list.size());
      list.addAtIndex(2, "0a");
  }

  @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
  public void testAddAtIndexIllegalArgumentException() {
      list.addAtIndex(0, null);
  }

  @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
  public void testAddToFrontIllegalArgumentException() {
      list.addToFront(null);
  }

  @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
  public void testAddToBackIllegalArgumentException() {
      list.addToBack(null);
  }

  @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
  public void testRemoveAtIndexLowerIndexOutOfBoundsException() {
      list.removeAtIndex(-1);
  }
  
  @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
  public void testRemoveAtIndexUpperIndexOutOfBoundsException() {
      list.clear();
      assertEquals(0, list.size());
      list.removeAtIndex(2);
  }
   
  @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
  public void testLastOccurenceIllegalArgumentException() {
      list.lastOccurrence(null);
  }
 
}