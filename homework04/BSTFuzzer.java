import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Sample JUnit test cases for BST.
 *
 * @version 1.0
 * @author Kyle Stachowicz
 */
public class BSTFuzzer {
    private BST<Integer> bst;
    public static final int TIMEOUT = 200;

    @Before
    public void setup() {
        bst = new BST<Integer>();
    }

    int oneToOne(int x) {
        return (int) ((x * 32416190071L) % (179426549));
    }

    @Test(timeout = 1000)
    public void fuzzAddRemove() {
        Random random = new Random();
        HashSet<Integer> oracle = new HashSet<>();

        for (int i = 0; i < 10000; i++) {
            int searchElement = oneToOne(random.nextInt(i + 1));
            switch (random.nextInt(3)) {
            case 0:
                bst.add(oneToOne(i));
                oracle.add(oneToOne(i));
                break;
            case 1:
                assertEquals(bst.contains(searchElement), oracle.contains(searchElement));
                break;
            case 2:
                if (oracle.contains(searchElement)) {
                    bst.remove(searchElement);
                    oracle.remove(searchElement);
                } else {
                    assertFalse(bst.contains(searchElement));
                }
                break;
            default:
                break;
            }
        }
    }
}