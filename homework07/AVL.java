import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Omar Shaikh
 * @userid oshaikh3
 * @GTID 903403821
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    // Credit: TAs psuedocode

    /**
     * A no-argument constructor that should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it appears in the Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular
     * for loop will not work here. What other type of loop would work?
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Collection of data cannot" 
            + " be null");
        }
        for (T element : data) {
            add(element);
        }
    }

    /**
     * Add the data to the AVL. Start by adding it as a leaf and rotate the tree
     * as needed. Should traverse the tree to find the appropriate location.
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors going up the tree,
     * rebalancing if necessary.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data to add to" 
            + " BST must not be null");
        }

        root = addHelper(root, data);
    }


    /**
     * Helper method to add data to a leaf in a BST. Does all the recursive 
     * hard work. Compares a node, and picks a path to go down, then, it 
     * adds the node to the correct position.
     *
     * @param node the node to start the add process from
     * @param data the data to be added
     * 
     * @return the updated root of the tree
     */
    private AVLNode<T> addHelper(AVLNode<T> node, T data) {
        if (node == null) {
            size++;
            return new AVLNode<T>(data);
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(addHelper(node.getRight(), data));   
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(addHelper(node.getLeft(), data));
        }
        
        updateHeightAndBalanceFactor(node);
        return rotate(node);
    }

    /**
     * Helper method to update height and balance for a node.
     *
     * @param node the node to update height and balance factor
     */
    private void updateHeightAndBalanceFactor(AVLNode<T> node) {

        int leftHeight = -1;
        int rightHeight = -1;

        
        if (node.getLeft() != null) {
            leftHeight = node.getLeft().getHeight();
        }

        if (node.getRight() != null) {
            rightHeight = node.getRight().getHeight();
        }

        node.setBalanceFactor(leftHeight - rightHeight);
        node.setHeight(Math.max(rightHeight, leftHeight) + 1);

    }

    /**
     * Removes the data from the tree. There are 3 cases to consider:
     *
     * 1: the data is a leaf. In this case, simply remove it.
     * 2: the data has one child. In this case, simply replace it with its
     * child.
     * 3: the data has 2 children. Use the predecessor to replace the data,
     * not the successor.
     * You must use recursion to find and remove the predecessor (you will
     * likely need an additional helper method to handle this case efficiently).
     *
     * Remember to recalculate heights going up the tree, rebalancing if
     * necessary.
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to remove from the tree.
     * @return the data removed from the tree. Do not return the same data
     * that was passed in.  Return the data that was stored in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data removed cannot be null");
        }
        AVLNode<T> dummy = new AVLNode<T>(null);
        root = removeHelper(root, data, dummy);
        size--;
        return dummy.getData();
    }

    /**
     * Helper method to remove the data from the tree. Uses pointer re-
     * inforcement to avoid edge cases.
     *  
     * @throws java.util.NoSuchElementException if the data is not found
     * @param node the head of the subtree to search & remove from.
     * @param data the data to remove from the tree.
     * @param dummy a dummy node to store removed data in
     * @return the node removed from the tree
     */
    private AVLNode<T> removeHelper(AVLNode<T> node, T data, AVLNode<T> dummy) {
        if (node == null) {
            throw new NoSuchElementException("Node to remove was not found!");
        }

        if (data.compareTo(node.getData()) == 0) {
            dummy.setData(node.getData());
            // no children
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            // both children
            } else if (node.getLeft() != null && node.getRight() != null) {
                AVLNode<T> tempDummy = new AVLNode<T>(null);
                node.setLeft(predecessor(node.getLeft(), tempDummy));
                node.setData(tempDummy.getData());
            // one child
            } else {
                return 
                    node.getRight() == null ? node.getLeft() : node.getRight();
            }


        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(removeHelper(node.getLeft(), data, dummy));
        } else  {
            node.setRight(removeHelper(node.getRight(), data, dummy));
        }

        updateHeightAndBalanceFactor(node);
        return rotate(node); // rotates the nodes if neccesarry

    }

    /**
     * Helper for node remove helper. Find a predecessor by looking at the 
     * left-most node that's a leaf. Then, it returns the reference to
     * the right child to "re-attach" it
     * 
     * @param node the head of the subtree to find a predecessor from
     * @param temp a node to predecessor data in
     * @return the node that replaces the predecessors position
     */
    private AVLNode<T> predecessor(AVLNode<T> node, AVLNode<T> temp) {
        if (node.getRight() == null) {
            temp.setData(node.getData());
            return node.getLeft();
        } else {
            node.setRight(predecessor(node.getRight(), temp));
        }
        
        updateHeightAndBalanceFactor(node);
        return rotate(node);
    }

    /**
     * Helper for node rotation. Rotates a node if it's out of balance
     * 
     * @param target the node of the subtree to check for rotation
     * @return the rotated node
     */
    private AVLNode<T> rotate(AVLNode<T> target) {
        if (target.getBalanceFactor() == 2) {
            // double rotation
            if (target.getLeft().getBalanceFactor() == -1) {
                target.setLeft(rotateLeft(target.getLeft()));
            }
            return rotateRight(target);
        } else if (target.getBalanceFactor() == -2) {
            // double rotation
            if (target.getRight().getBalanceFactor() == 1) {
                target.setRight(rotateRight(target.getRight()));
            }
            return rotateLeft(target);
        }
        return target;
    }


    /**
     * Helper for node left rotation. Rotates a node in the left direction
     * 
     * @param node the node of the subtree to rotate
     * @return the rotated node
     */
    private AVLNode<T> rotateLeft(AVLNode<T> node) {
        AVLNode<T> newRoot = node.getRight();
        node.setRight(newRoot.getLeft());
        newRoot.setLeft(node);
        updateHeightAndBalanceFactor(node);
        updateHeightAndBalanceFactor(newRoot);
        return newRoot;
    }
    
    /**
     * Helper for node right rotation. Rotates a node in the right direction
     * 
     * @param node the node of the subtree to rotate
     * @return the rotated node
     */
    private AVLNode<T> rotateRight(AVLNode<T> node) {
        AVLNode<T> newRoot = node.getLeft();
        node.setLeft(newRoot.getRight());
        newRoot.setRight(node);
        updateHeightAndBalanceFactor(node);
        updateHeightAndBalanceFactor(newRoot);
        return newRoot;
    }

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use value equality or reference equality?).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to search for in the tree.
     * @return the data in the tree equal to the parameter. Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot get data of null");
        } else {
            return getHelper(root, data);
        } 
    }

    /**
     * Helper method for 'get.' Picks subtree by comparing nodes, recursively.
     *
     * @throws java.util.NoSuchElementException if the data is not found
     * @param node the node of the subtree to search from.
     * @param data the data to search for in the tree.
     * @return the data in the tree equal to the parameter. Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
     */
    private T getHelper(AVLNode<T> node, T data) {
        if (node == null) {
            throw new NoSuchElementException("Node to get was not found!");
        }
        if (data.compareTo(node.getData()) > 0) {
            return getHelper(node.getRight(), data);
        } else if (data.compareTo(node.getData()) < 0) {
            return getHelper(node.getLeft(), data);
        } else {
            return node.getData();
        }
    }

    /**
     * Returns whether or not data equivalent to the given parameter is
     * contained within the tree. The same type of equality should be used as
     * in the get method.
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
     * Returns the data in the deepest node. If there are more than one node
     * with the same deepest depth, return the right most node with the
     * deepest depth.
     *
     * Must run in O(log n) for all cases
     *
     * Example
     * Tree:
     *           2
     *        /    \
     *       0      3
     *        \
     *         1
     * Max Deepest Node:
     * 1 because it is the deepest node
     *
     * Example
     * Tree:
     *           2
     *        /    \
     *       0      4
     *        \    /
     *         1  3
     * Max Deepest Node:
     * 3 because it is the maximum deepest node (1 has the same depth but 3 > 1)
     *
     * @return the data in the maximum deepest node or null if the tree is empty
     */
    public T maxDeepestNode() {
        if (root == null) {
            return null;
        }
        return maxDeepestNodeSubtree(root).getData();
    }


    /**
     * Helper method for 'maxdeepestnode.' 
     * Returns the deepest node in a subtree -- recursive helper
     *
     * @throws java.util.NoSuchElementException if the data is not found
     * @param root the root node.
     * @return the deepest node in in the tree
     */
    public AVLNode<T> maxDeepestNodeSubtree(AVLNode<T> root) {
        
        if (root.getLeft() == null && root.getRight() == null) {
            return root;
        }

        if (root.getRight() == null) {
            return maxDeepestNodeSubtree(root.getLeft());
        }

        if (root.getLeft() == null) {
            return maxDeepestNodeSubtree(root.getRight());
        }

        if (root.getLeft().getHeight() == root.getRight().getHeight()) {
            return maxDeepestNodeSubtree(root.getRight());
        }

        return root.getLeft().getHeight() > root.getRight().getHeight() 
            ? maxDeepestNodeSubtree(root.getLeft()) 
            : maxDeepestNodeSubtree(root.getRight());
    }

    /**
     * Returns the data of the deepest common ancestor between two nodes with
     * the given data. The deepest common ancestor is the lowest node (i.e.
     * deepest) node that has both data1 and data2 as descendants.
     * If the data are the same, the deepest common ancestor is simply the node
     * that contains the data. You may not assume data1 < data2.
     * (think carefully: should you use value equality or reference equality?).
     *
     * Must run in O(log n) for all cases
     *
     * Example
     * Tree:
     *           2
     *        /    \
     *       0      3
     *        \
     *         1
     * deepestCommonAncestor(3, 1): 2
     *
     * Example
     * Tree:
     *           3
     *        /    \
     *       1      4
     *      / \
     *     0   2
     * deepestCommonAncestor(0, 2): 1
     *
     * @param data1 the first data
     * @param data2 the second data
     * @throws java.lang.IllegalArgumentException if one or more of the data
     *          are null
     * @throws java.util.NoSuchElementException if one or more of the data are
     *          not in the tree
     * @return the data of the deepest common ancestor
     */
    public T deepestCommonAncestor(T data1, T data2) {
        if (data1 == null || data2 == null) {
            throw new IllegalArgumentException();
        }
        
        AVLNode<T> lcaNode = lowestCommonAncestorHelper(root, data1, data2);

        getHelper(lcaNode, data1);
        getHelper(lcaNode, data2);

        return lowestCommonAncestorHelper(root, data1, data2).getData();
    }
    

    /**
     * Helper method for 'deepestCommonAncestor.' Picks subtree by 
     * comparing nodes, recursively.
     *
     * @throws java.util.NoSuchElementException if the data is not found
     * @param root the node of the subtree to search from.
     * @param data1 the first data
     * @param data2 the second data
     * @return the node where the recursive paths diverge (the LCA)
     */
    public AVLNode<T> lowestCommonAncestorHelper(AVLNode<T> root, T data1, 
        T data2) {

        if (root == null) {
            throw new 
                NoSuchElementException("Data could not be found in the tree");
        }
 
        if (root.getData().compareTo(data1) > 0 
            && root.getData().compareTo(data2) < 0) {
            return root;  
        } else if (root.getData().compareTo(data1) > 0 
            && root.getData().compareTo(data2) > 0) {
            return lowestCommonAncestorHelper(root.getLeft(), data1, data2);
        } else if (root.getData().compareTo(data1) < 0 
            && root.getData().compareTo(data2) < 0) {
            return lowestCommonAncestorHelper(root.getRight(), data1, data2);
        }
     
        return root;
    }

    /**
     * Clear the tree.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Return the height of the root of the tree.
     *
     * Since this is an AVL, this method does not need to traverse the tree
     * and should be O(1)
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (root == null) {
            return -1;
        }
        return root.getHeight();
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
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
