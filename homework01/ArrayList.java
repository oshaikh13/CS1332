/**
 * Your implementation of an ArrayList.
 * 
 * @userid oshaikh3
 * @GTID 903403821
 * @author Omar Shaikh
 * @version 1.0
 */

 // 95/100
public class ArrayList<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * The initial capacity of the array list.
     */
    public static final int INITIAL_CAPACITY = 13;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray  = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Copies all elements from the current backing array and creates a 
     * new one of size newSize
     * 
     * @param newSize The new size of the backing array.
     */
    private void resizeBackingArray(int newSize) {

        T[] tempBackingArray = (T[]) new Object[newSize];

        for (int i = 0; i < backingArray.length; i++) {
            tempBackingArray[i] = backingArray[i];
        }

        backingArray = tempBackingArray;

    }

    /**
     * Copies all elements from the current backing array and creates a 
     * new one of size backingArray.length * 2
     * .
     */
    private void resizeBackingArray() {
        resizeBackingArray(backingArray.length * 2);
    }

    /**
     * Moves all elements, from (and including) index i, one position back
     *  
     * @param index The index where you want the shifting to start.
     */
    private void shiftBack(int index) {

        T current = backingArray[index];
        backingArray[index] = null;

        for (int i = index + 1; i < size; i++) {
            T nextElem = backingArray[i];
            backingArray[i] = current;
            current = nextElem;
        }

    }

    /**
     * Moves all elements, from (and including) index i, one position up
     * 
     * Note that if index = size - 1, then the runtime of this is O(1)
     * 
     * @param index The index where you want the shifting to start.
     */
    private void shiftFront(int index) {
        
        for (int i = index; i < size; i++) {
            backingArray[i] = backingArray[i + 1];
        }

        backingArray[size] = null;

    }

    /**
     * Adds the element to the index specified.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Adding to index {@code size} should be amortized O(1),
     * all other adds are O(n).
     *
     * @param index The index where you want the new element.
     * @param data The data to add to the list.
     * @throws java.lang.IndexOutOfBoundsException if index is negative
     * or index > size
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addAtIndex(int index, T data) {

        if (data == null) {
            throw new IllegalArgumentException("Cannot insert data of" 
                                                + " type null into structure");
        } 

        if (index < 0 || index > size) { 
            throw new IndexOutOfBoundsException("The index provided (" 
                                                + index + ") does not " 
                                                + "fall within addAtIndex's "
                                                + "bounds: " 
                                                + "[" + 0 + ", " 
                                                + (size) + "]");  
        }

        if (++size > backingArray.length) {
            resizeBackingArray();
        }

        if (backingArray[index] == null) {
            backingArray[index] = data;
        } else {
            shiftBack(index);
            backingArray[index] = data;
        }

    }

    /**
     * Add the given data to the front of your array list.
     *
     * Remember that this add may require elements to be shifted.
     * 
     * Must be O(n).
     *
     * @param data The data to add to the list.
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Cannot insert data of" 
                                                + " type null into structure");
        } 

        addAtIndex(0, data);
    }

    /**
     * Add the given data to the back of your array list.
     *
     * Must be amortized O(1).
     *
     * @param data The data to add to the list.
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Cannot insert data of" 
                                                + " type null into structure");
        } 

        addAtIndex(size, data);
    }

    /**
     * Removes and returns the element at index.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * This method should be O(1) for index {@code size - 1} and O(n) in 
     * all other cases.
     *
     * @param index The index of the element
     * @return The object that was formerly at that index.
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
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

        T removedElement = backingArray[index];
        size--;
        shiftFront(index);
        return removedElement;
    }

    /**
     * Remove the first element in the list and return it.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @return The data from the front of the list or null if the list is empty
     */
    public T removeFromFront() {
        if (this.isEmpty()) {
            return null;
        }

        return removeAtIndex(0);
    }

    /**
     * Remove the last element in the list and return it.
     * 
     * Must be O(1).
     *
     * @return The data from the back of the list or null if the list is empty
     */
    public T removeFromBack() {
        if (this.isEmpty()) {
            return null;
        }

        return removeAtIndex(size - 1);
    }

    /**
     * Returns the element at the given index.
     *
     * Must be O(1).
     *
     * @param index The index of the element
     * @return The data stored at that index.
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     * index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) { 
            throw new IndexOutOfBoundsException("The index provided (" 
                                                + index + ") does not " 
                                                + "fall within get's "
                                                + "bounds: " 
                                                + "[" + 0 + ", " 
                                                + (size) + ")");  
        }
        return backingArray[index];
    }

    /**
     * Return a boolean value representing whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty; false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clear the list. Reset the backing array to a new array of the initial
     * capacity.
     *
     * Must be O(1).
     */
    public void clear() {
        size = 0;
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Return the size of the list as an integer.
     *
     * For grading purposes only. DO NOT USE THIS METHOD IN YOUR CODE!
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Return the backing array for this list.
     *
     * For grading purposes only. DO NOT USE THIS METHOD IN YOUR CODE!
     *
     * @return the backing array for this list
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }
}
