import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Sample JUnit test cases for BST.
 *
 * @version 1.0
 * @author Kyle Stachowicz
 * @author someone completely unqualified
 */
public class BSTAnotherFuzzer {
    private BST<Integer> bet;

    @Before
    public void setup() {
        bet = new BST<>();
    }

    int AAaAaaaaaAaaAmericaAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa = 0;
    void seed(int x) {
        AAaAaaaaaAaaAmericaAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa = x;
    }
    int next() {
        AAaAaaaaaAaaAmericaAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa =
                1103515245* AAaAaaaaaAaaAmericaAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa +12345;
        return AAaAaaaaaAaaAmericaAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa;
    }

    private List<BSTNode<Integer>> dump(BST<Integer> lmao) {
        List<BSTNode<Integer>> aaa = new ArrayList<>();
        ArrayDeque<BSTNode<Integer>> dank = new ArrayDeque<>();
        dank.add(lmao.getRoot());
        while (!dank.isEmpty()) {
            BSTNode<Integer> obama = dank.remove();
            aaa.add(obama);
            if (obama.getLeft()!=null)
                dank.add(obama.getLeft());
            if (obama.getRight()!=null)
                dank.add(obama.getRight());
        }
        return aaa;
    }

    @Test()
    public void doDaLalalalalalalalalala() {
        HashSet<Integer> oracle = new HashSet<>();
        seed((int)System.currentTimeMillis());

        for (int i = 0; i < 100000; i++) {
            int searchElement = next()%100;
//            System.out.println(bet.size() + " " + oracle.size());
//            System.out.println(bet.inorder());
            int op =next()%10;
//            System.out.println("op: " + op + " on " + searchElement);
            switch (op) {
            case 0:
                bet.add(searchElement);
                oracle.add(searchElement);
                break;
            case 1:
                assertEquals(bet.contains(searchElement), oracle.contains(searchElement));
                break;
            case 2:
                if (oracle.contains(searchElement)) {
                    bet.remove(searchElement);
                    oracle.remove(searchElement);
                } else {
                    assertFalse(bet.contains(searchElement));
                }
                break;
            case 3:
                if (bet.size() == 0) break;
                int k = next() % bet.size();
                List<Integer> lol = new ArrayList<>(oracle);
                Collections.sort(lol);
                List<Integer> asdf = bet.kLargest(k);
                for (int jamboree = 0; jamboree < k; jamboree++) {
                    assertEquals(asdf.get(jamboree),
                            lol.get(lol.size()-k+jamboree));
                }
                break;
            case 4:
                List<Integer> scuttlebug = bet.preorder();
                int potato = Integer.MIN_VALUE;
                assertEquals(scuttlebug.size(), bet.size());
                Stack<Integer> whatever = new Stack<>();
                for (int qwerty : scuttlebug) {
                    assertFalse ("not in preorder, you messed up!!!",
                            qwerty < potato);
                    while (!whatever.isEmpty()&&qwerty > whatever.peek()) potato =
                            whatever.pop();
                    whatever.push(qwerty);
                }
                break;
            case 5:
                List<Integer> scuttlebug2 = bet.inorder();
                List<Integer> wow = new ArrayList<>(scuttlebug2);
                Collections.sort(wow);
                List<Integer> america = new ArrayList<>(oracle);
                Collections.sort(america);
                assertEquals(wow, scuttlebug2);
                assertEquals(wow, america);
                break;
            case 6:
                 List<Integer> dfffffffffffffs = bet.postorder();
                for (int i1 = dfffffffffffffs.size() - 1; i1 >= 0; i1--) {
                    int iggggggg = dfffffffffffffs.get(i1);
                    BST<Integer> lololol = new BST<>();
                    Field fffffffff = null;
                    try {
                        fffffffff = lololol.getClass().getDeclaredField(
                                "root");
                        fffffffff.setAccessible(true);
                        fffffffff.set(lololol, dump(bet).stream().filter(x -> x.getData().equals(iggggggg)).findFirst().get().getLeft());
                        Field money =
                                lololol.getClass().getDeclaredField("size");
                        money.setAccessible(true);
                        money.set(lololol, bet.size());
                        assertTrue(dfffffffffffffs.subList(0, i1).containsAll(lololol.levelorder()));
                        lololol=new BST<>();
                        fffffffff.set(lololol,
                                dump(bet).stream().filter(x -> x.getData().equals(iggggggg)).findFirst().get().getRight());
                        money.set(lololol, bet.size());
                        assertTrue(dfffffffffffffs.subList(0, i1).containsAll(lololol.levelorder()));
                    } catch (ReflectiveOperationException e) {
                        e.printStackTrace();
                    }
                }
                 break;
            default:
                break;
            }
//            System.out.println(bet.inorder());
            assertEquals(bet.size(), oracle.size());
            assertEquals(oracle,new HashSet<>(bet.preorder()));
        }
    }
}