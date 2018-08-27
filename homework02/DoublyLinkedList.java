/**
 * Your implementation of a non-circular doubly linked list with a tail pointer.
 *
 * @author Omar Shaikh
 * @userid oshaikh3
 * @GTID 903403821
 * @version 1.0
 */
public class DoublyLinkedList<T> {
    // Do not add new instance variables or modify existing ones.
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    /**
     * Initializes the head and tail references with data
     * 
     * @param data the data for the new element
     */
    private void initLinkedList(T data) {
        head = new LinkedListNode<T>(data);
        tail = head;
    }

    /**
     * Returns a node object at an index in the linked list
     * Traverses the linked list from the closer side
     *
     * @param index the index of the requested node
     * @return the node
     * @throws java.lang.IndexOutOfBoundsException if index is negative or
     * index >= size
     */
    private LinkedListNode<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index provided (" 
                                                + index + ") does not " 
                                                + "fall within getNode's "
                                                + "bounds: " 
                                                + "[" + 0 + ", " 
                                                + (size) + ")");   
        }
        
        boolean startFromFront = size / 2 < index;
        LinkedListNode current;
        int trackingIndex;

        if (startFromFront) {

            current = head;
            trackingIndex = 0;

            while (current != null) {
                if (trackingIndex == index) {
                    return current;
                }
                trackingIndex++;
                current = current.getNext();
            }

        } else {

            current = tail;
            trackingIndex = size - 1;

            while (current != null) {
                if (trackingIndex == index) {
                    return current;
                }
                trackingIndex--;
                current = current.getPrevious();
            }  

        }

        throw new IllegalStateException("The linked list references" 
        + " are incorrectly set");
    }

    /**
     * Adds the element to the index specified.
     *
     * Adding to indices 0 and {@code size} should be O(1), all other cases are
     * O(n).
     *
     * @param index the requested index for the new element
     * @param data the data for the new element
     * @throws java.lang.IndexOutOfBoundsException if index is negative or
     * index > size
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addAtIndex(int index, T data) {

        if (data == null) {
            throw new IllegalArgumentException("Data of type null cannot" 
            + " be inserted");
        }

        if (index == 0) {
            addToFront(data);
            return;
        }

        if (index == size) {
            addToBack(data);
            return;
        }

        LinkedListNode currentAtIndex = getNode(index);
        LinkedListNode newNode = 
            new LinkedListNode<T>(currentAtIndex.getPrevious(), data, 
                                  currentAtIndex);
        currentAtIndex.getPrevious().setNext(newNode);
        currentAtIndex.setPrevious(newNode);
        size++;
    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1) for all cases.
     *
     * @param data the data for the new element
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    public void addToFront(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Data of type null cannot" 
            + " be inserted");
        }
    
        if (size == 0) {
            initLinkedList(data);
            size++;
            return;
        }
    
        LinkedListNode newHead = new LinkedListNode<T>(null, data, head);
        head.setPrevious(newHead);
        head = newHead;
        size++;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1) for all cases.
     *
     * @param data the data for the new element
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    public void addToBack(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Data of type null cannot" 
            + " be inserted");
        }

        if (size == 0) {
            initLinkedList(data);
            size++;
            return;
        }

        LinkedListNode newTail = new LinkedListNode<T>(tail, data, null);
        tail.setNext(newTail);
        tail = newTail;
        size++;
    }

    /**
     * Removes and returns the element from the index specified.
     *
     * Removing from index 0 and {@code size - 1} should be O(1), all other
     * cases are O(n).
     *
     * @param index the requested index to be removed
     * @return the data formerly located at index
     * @throws java.lang.IndexOutOfBoundsException if index is negative or
     * index >= size
     */
    public T removeAtIndex(int index) {

        if (index < 0 || index >= size) { 
            throw new IndexOutOfBoundsException("The index provided (" 
                                                + index + ") does not " 
                                                + "fall within removeAtIndex's "
                                                + "bounds: " 
                                                + "[" + 0 + ", " 
                                                + (size) + ")");
        }
        
        if (index == size - 1) {
            return removeFromBack();
        } else if (index == 0) {
            return removeFromFront();
        }

        LinkedListNode<T> removedNode = getNode(index);
        T removedData = removedNode.getData();
        removedNode.getPrevious().setNext(removedNode.getNext());
        removedNode.getNext().setPrevious(removedNode.getPrevious());
        size--;
        return removedData;
    }

    /**
     * Removes and returns the element at the front of the list. If the list is
     * empty, return {@code null}.
     *
     * Must be O(1) for all cases.
     *
     * @return the data formerly located at the front, null if empty list
     */
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        }

        T oldHead = head.getData();

        head = head.getNext();

        if (--size == 0) {
            clear();
            return oldHead;
        }

        head.setPrevious(null);

        return oldHead;
    }

    /**
     * Removes and returns the element at the back of the list. If the list is
     * empty, return {@code null}.
     *
     * Must be O(1) for all cases.
     *
     * @return the data formerly located at the back, null if empty list
     */
    public T removeFromBack() {
        if (isEmpty()) {
            return null;
        } 
        
        T oldTail = tail.getData();

        tail = tail.getPrevious();
        
        if (--size == 0) {
            clear();
            return oldTail;
        }

        tail.setNext(null);
        
        return oldTail;
    }

    /**
     * Returns the index of the last occurrence of the passed in data in the
     * list or -1 if it is not in the list.
     *
     * If data is in the tail, should be O(1). In all other cases, O(n).
     *
     * @param data the data to search for
     * @throws java.lang.IllegalArgumentException if data is null
     * @return the index of the last occurrence or -1 if not in the list
     */
    public int lastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data of type null cannot" 
            + " be inserted");
        }

        if (isEmpty()) {
            return -1;
        }
        
        LinkedListNode current = tail;
        int index = size - 1;
        while (current != null) {
            if (current.getData().equals(data)) {
                return index;
            }
            index--;
            current = current.getPrevious();
        }

        return -1;
    }

    /**
     * Returns the element at the specified index.
     *
     * Getting the head and tail should be O(1), all other cases are O(n).
     *
     * @param index the index of the requested element
     * @return the object stored at index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     * index >= size
     */
    public T get(int index) {
        return getNode(index).getData();
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return an array of length {@code size} holding all of the objects in
     * this list in the same order from head to tail
     */
    public Object[] toArray() {
        Object[] elements = new Object[size];
        LinkedListNode current = head;
        int index = 0;
        while (current != null) {
            elements[index] = current.getData();
            current = current.getNext();
            index++;
        }
        return elements;
    }

    /**
     * Returns a boolean value indicating if the list is empty.
     *
     * Must be O(1) for all cases.
     *
     * @return true if empty; false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list of all data and resets the size.
     *
     * Must be O(1) for all cases.
     */
    public void clear() {
        head = null;
        tail = head;
        size = 0;
    }

    /**
     * Returns the number of elements in the list.
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
     * Returns the head node of the linked list.
     * Normally, you would not do this, but it's necessary for testing purposes.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return node at the head of the linked list
     */
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the linked list.
     * Normally, you would not do this, but it's necessary for testing purposes.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return node at the tail of the linked list
     */
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}