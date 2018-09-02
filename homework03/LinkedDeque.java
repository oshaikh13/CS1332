import java.util.NoSuchElementException;

/**
 * Your implementation of a linked deque.
 *
 * @author YOUR NAME HERE
 * @userid YOUR USER ID HERE (e.g. gburdell3)
 * @GTID YOUR GT ID HERE (e.g. 900000000)
 * @version 1.0
 */
public class LinkedDeque<T> {
    // Do not add new instance variables and do not add a new constructor.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    private void initDeque(T data) {
        head = new LinkedNode<T>(data);
        tail = head;
    }

    private void clearDeque() {
        head = null;
        tail = head;
    }

    /**
     * Adds the data to the front of the deque.
     *
     * This method must run in O(1) time.
     *
     * @param data the data to add to the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addFirst(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Data inserted into the deque cannot be null");
        }

        if (size++ == 0) { 
            initDeque(data);
            return;
        }

        LinkedNode<T> newHead = new LinkedNode<>(null, data, head);
        head.setPrevious(newHead);
        head = newHead;

    }

    /**
     * Adds the data to the back of the deque.
     *
     * This method must run in O(1) time.
     *
     * @param data the data to add to the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addLast(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Data inserted into the deque cannot be null");
        }

        if (size++ == 0) { 
            initDeque(data);
            return;
        }

        LinkedNode<T> newTail = new LinkedNode<T>(tail, data, null);
        tail.setNext(newTail);
        tail = newTail;

    }

    /**
     * Removes the data at the front of the deque.
     *
     * This method must run in O(1) time.
     *
     * @return the data formerly at the front of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeFirst() {

        if (size == 0) {
            throw new NoSuchElementException();
        } 
        
        T headData = head.getData();

        if (--size == 0) {
            clearDeque();
            return headData;
        }

        LinkedNode<T> newHead = head.getNext();
        newHead.setPrevious(null);
        head = newHead;
        return headData;
    }

    /**
     * Removes the data at the back of the deque.
     *
     * This method must run in O(1) time.
     *
     * @return the data formerly at the back of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeLast() {

        if (size == 0) {
            throw new NoSuchElementException();
        } 

        T tailData = tail.getData();

        if (--size == 0) {
            clearDeque();
            return tailData;
        }

        LinkedNode<T> newTail = tail.getPrevious();
        newTail.setNext(null);
        tail = newTail;
        return tailData;
    }

    /**
     * Returns the number of elements in the deque.
     *
     * Runs in O(1) for all cases.
     * 
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    /**
     * Returns the head node of the linked deque.
     * Normally, you would not do this, but it's necessary for testing purposes.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return node at the head of the linked deque
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the linked deque.
     * Normally, you would not do this, but it's necessary for testing purposes.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return node at the tail of the linked deque
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}