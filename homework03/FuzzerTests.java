import org.junit.Before;
import org.junit.Test;

import java.util.Deque;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.util.NoSuchElementException;

/*
 * Fuzzer test for the ArrayDeque and LinkedDeque classes.
 * I take no responsibility for the correctness of these tests.
 *
 * @author Kyle Stachowicz
 * @version 1.1
 */
public class FuzzerTests {

    private ArrayDeque<Integer> array;
    private LinkedDeque<Integer> linked;

    public static final int TIMEOUT = 200;

    @Before
    public void setup() {
        array = new ArrayDeque<>();
        linked = new LinkedDeque<>();
    }

    @Test
    public void fuzz() {
        Deque<Integer> oracle = new java.util.ArrayDeque<>();
        Random random = new Random();

        Integer resultOracle;
        Integer resultLinked;
        Integer resultArray;
        for (int i = 0; i < 10000; i++) {
            assertEquals(oracle.size(), linked.size());
            assertEquals(oracle.size(), array.size());

            // Whether or not they threw the correct exceptions
            boolean arrayFailureCorrect = false;
            boolean linkedFailureCorrect = false;
            int next = random.nextInt(4);
            switch (next) {
            case 0:
                oracle.addFirst(i);
                linked.addFirst(i);
                array.addFirst(i);
                break;
            case 1:
                oracle.addLast(i);
                linked.addLast(i);
                array.addLast(i);
                break;
            case 2:
                if (oracle.size() > 0) {
                    resultOracle = oracle.removeFirst();
                    resultLinked = linked.removeFirst();
                    resultArray = array.removeFirst();
                    assertEquals(resultOracle, resultLinked);
                    assertEquals(resultOracle, resultArray);
                } else {
                    try {
                        array.removeFirst();
                    } catch (NoSuchElementException e) {
                        arrayFailureCorrect = true;
                    }

                    try {
                        linked.removeFirst();
                    } catch (NoSuchElementException e) {
                        linkedFailureCorrect = true;
                    }

                    assertTrue(linkedFailureCorrect);
                    assertTrue(arrayFailureCorrect);
                }
                break;
            case 3:
                if (oracle.size() > 0) {
                    resultOracle = oracle.removeLast();
                    resultLinked = linked.removeLast();
                    resultArray = array.removeLast();
                    assertEquals(resultOracle, resultLinked);
                    assertEquals(resultOracle, resultArray);
                } else {
                    try {
                        array.removeLast();
                    } catch (NoSuchElementException e) {
                        arrayFailureCorrect = true;
                    }

                    try {
                        linked.removeLast();
                    } catch (NoSuchElementException e) {
                        linkedFailureCorrect = true;
                    }

                    assertTrue(linkedFailureCorrect);
                    assertTrue(arrayFailureCorrect);
                }
                break;
            default:
                break;
            }
        }
    }
}