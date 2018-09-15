import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your implementation of a binary search tree.
 *
 * @author YOUR NAME HERE
 * @userid YOUR USER ID HERE (i.e. gburdell3)
 * @GTID YOUR GT ID HERE (i.e. 900000000)
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Initializes the BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular
     * for loop will not work here. What other type of loop would work?
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        for (T element : data) {
            add(element);
        }
    }

    /**
     * Add the data as a leaf in the BST. Should traverse the tree to find the
     * appropriate location. If the data is already in the tree, then nothing
     * should be done (the duplicate shouldn't get added, and size should not be
     * incremented).
     * 
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    public void add(T data) {
        size++;
        if (root == null) {
            root = new BSTNode<T>(data);
        } else addHelper(root, data);
    }

    private void addHelper(BSTNode<T> node, T data) {
        if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                node.setRight(new BSTNode<T>(data));
            } else {
                addHelper(node.getRight(), data);
            }
        } else if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new BSTNode<T>(data));
            } else {
                addHelper(node.getLeft(), data);
            }
        } else return;
    }

    /**
     * Removes the data from the tree. There are 3 cases to consider:
     *
     * 1: the data is a leaf. In this case, simply remove it.
     * 2: the data has one child. In this case, simply replace it with its
     * child.
     * 3: the data has 2 children. Use the successor to replace the data.
     * You must use recursion to find and remove the successor (you will likely
     * need an additional helper method to handle this case efficiently).
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to remove from the tree.
     * @return the data removed from the tree. Do not return the same data
     * that was passed in.  Return the data that was stored in the tree.
     */
    public T remove(T data) {
        BSTNode<T> dummy = new BSTNode<T>(null);
        root = removeHelper(root, data, dummy);
        size--;
        return dummy.getData();
    }

    public BSTNode<T> removeHelper (BSTNode<T> node, T data, BSTNode<T> dummy) {
        if (node == null) {
            throw new NoSuchElementException();
        }

        if (data.compareTo(node.getData()) == 0) {
            System.out.println(node.getData());
            dummy.setData(node.getData());
            // no children
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            // both children
            } else if (node.getLeft() != null && node.getRight() != null) {
                BSTNode<T> tempDummy = new BSTNode<T>(null);
                node.setRight(successor(node.getRight(), tempDummy));
                node.setData(tempDummy.getData());
            // one child
            } else {
                return node.getRight() == null ? node.getLeft() : node.getRight();
            }


        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(removeHelper(node.getLeft(), data, dummy));
        } else  {
            node.setRight(removeHelper(node.getRight(), data, dummy));
        }

        return node;

    }

    public BSTNode<T> successor (BSTNode<T> node, BSTNode<T> temp) {
        if (node.getLeft() == null) {
            temp.setData(node.getData());
            return node.getRight();
        } else {
            node.setLeft(successor(node.getLeft(), temp));
        }
        return node;
    }

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use value equality or reference equality?).
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to search for in the tree.
     * @return the data in the tree equal to the parameter. Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
     */
    public T get(T data) {
        if (root == null) {
            throw new NoSuchElementException();
        } else return getHelper(root, data);
    }

    public T getHelper(BSTNode<T> node, T data) {
        if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                throw new NoSuchElementException();
            } else {
                return getHelper(node.getRight(), data);
            }
        } else if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                throw new NoSuchElementException();
            } else {
                return getHelper(node.getLeft(), data);
            }
        } else return node.getData();
    }

    /**
     * Returns whether or not data equivalent to the given parameter is
     * contained within the tree. The same type of equality should be used as
     * in the get method.
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to search for in the tree.
     * @return whether or not the parameter is contained within the tree.
     */
    public boolean contains(T data) {
        try {
            get(data);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    /**
     * Should run in O(n).
     *
     * @return a preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> list = new ArrayList<T>();
        return preorderHelper(root, list);
    }

    public List<T> preorderHelper(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return list;
        } else {
            list.add(node.getData());
            preorderHelper(node.getLeft(), list);
            preorderHelper(node.getRight(), list); 
        }
        return list;
    }

    /**
     * Should run in O(n).
     *
     * @return an inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> list = new ArrayList<T>();
        return inorderHelper(root, list);
    }

    public List<T> inorderHelper(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return list;
        } else {
            inorderHelper(node.getLeft(), list);
            list.add(node.getData());
            inorderHelper(node.getRight(), list); 
        }
        return list;
    }

    /**
     * Should run in O(n).
     *
     * @return a postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> list = new ArrayList<T>();
        return postorderHelper(root, list);
    }

    public List<T> postorderHelper(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return list;
        } else {
            postorderHelper(node.getLeft(), list);
            postorderHelper(node.getRight(), list); 
            list.add(node.getData());
        }
        return list;
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * To do this, add the root node to a queue. Then, while the queue isn't
     * empty, remove one node, add its data to the list being returned, and add
     * its left and right child nodes to the queue. If what you just removed is
     * {@code null}, ignore it and continue with the rest of the nodes.
     *
     * Should run in O(n).
     *
     * @return a level order traversal of the tree
     */
    public List<T> levelorder() {
        Queue<BSTNode<T>> nodeQueue = new LinkedList<BSTNode<T>>();
        List<T> levelOrder = new ArrayList<T>();

        if (size == 0) return levelOrder;

        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {

            BSTNode<T> dequeued = nodeQueue.remove();
            levelOrder.add(dequeued.getData());

            if (dequeued.getLeft() != null) {
                nodeQueue.add(dequeued.getLeft());
            }

            if (dequeued.getRight() != null) {
                nodeQueue.add(dequeued.getRight());
            }

        }

        return levelOrder;
    }

    /**
     * Finds and retrieves the k-largest elements from the BST in sorted order,
     * least to greatest.
     *
     * In most cases, this method will not need to traverse the entire tree to
     * function properly, so you should only traverse the branches of the tree
     * necessary to get the data and only do so once. Failure to do so will
     * result in the efficiency penalty.
     *
     * EXAMPLE: Given the BST below composed of Integers:
     *
     *                50
     *              /    \
     *            25      75
     *           /  \
     *          12   37
     *         /  \    \
     *        10  15    40
     *           /
     *          13
     *
     * kLargest(5) should return the list [25, 37, 40, 50, 75].
     * kLargest(3) should return the list [40, 50, 75].
     *
     * Should have a running time of O(log(n) + k) for a balanced tree and a
     * worst case of O(n + k).
     *
     * @throws java.lang.IllegalArgumentException if k > n, the number of data
     * in the BST
     * @param k the number of largest elements to return
     * @return sorted list consisting of the k largest elements
     */
    public List<T> kLargest(int k) {
        if (k > size) throw new IllegalArgumentException();
        List<T> kLargest = new LinkedList<T>();
        kLargestHelper(root, kLargest, k);
        return kLargest;
    }

    public void kLargestHelper(BSTNode<T> node, List<T> largestList, int k) {

        if (node == null) { return; }
        kLargestHelper(node.getRight(), largestList, k);
        if (largestList.size() == k) { return; }
        largestList.add(0, node.getData());
        kLargestHelper(node.getLeft(), largestList, k);
        
    }

    /**
     * Clears the tree.
     *
     * Should run in O(1).
     */
    public void clear() {
        size = 0;
        root = null;
    }

    /**
     * Calculate and return the height of the root of the tree. A node's
     * height is defined as {@code max(left.height, right.height) + 1}. A leaf
     * node has a height of 0 and a null child should be -1.
     *
     * Should be calculated in O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (root == null) return -1;
        return heightHelper(root, 0);
    }

    public int heightHelper (BSTNode<T> node, int currentHeight) {
        if (node.getLeft() == null && node.getRight() == null) {
            return currentHeight;
        }

        int rightHeight = 0;
        int leftHeight = 0;

        if (node.getRight() != null) {
            rightHeight = heightHelper(node.getRight(), currentHeight + 1);
        }

        if (node.getLeft() != null) {
            leftHeight = heightHelper(node.getLeft(), currentHeight + 1);
        }

        return rightHeight > leftHeight ? rightHeight : leftHeight;
    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the number of elements in the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
