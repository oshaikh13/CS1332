/**
 * Your implementation of an ArrayList.
 *
 * @author YOUR NAME HERE
 * @userid YOUR USER ID HERE (i.e. gburdell3)
 * @GTID YOUR GT ID HERE (i.e. 900000000)
 * @version 1.0
 */
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

    private void resizeBackingArray() {
        resizeBackingArray(backingArray.length * 2);
    }

    private void resizeBackingArray(int newSize) {

        T[] tempBackingArray = (T[]) new Object[newSize];

        for (int i = 0; i < backingArray.length; i++) {
            tempBackingArray[i] = backingArray[i];
        }

        backingArray = tempBackingArray;

    }

    private void shiftBack(int n) {

        T current = backingArray[n];
        backingArray[n] = null;

        for (int i = n + 1; i < size; i++) {
            T nextElem = backingArray[i];
            backingArray[i] = current;
            current = nextElem;
        }

    }

    private void shiftFront(int n) {

        for (int i = n; i < size - 1; i++) {
            backingArray[n] = backingArray[n + 1];
        }

        backingArray[size - 1] = null;

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

        if (data == null) throw new IllegalArgumentException();
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (++size > backingArray.length) resizeBackingArray();

        if (backingArray[index] == null) backingArray[index] = data;
        else {
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
        if (++size > backingArray.length) resizeBackingArray();

        shiftBack(0);
        backingArray[0] = data;
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
        if (size++ > backingArray.length) resizeBackingArray();
        backingArray[size - 1] = data;
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
        T removedElement = backingArray[index];
        shiftFront(index);
        size--;
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
        T removedElement = backingArray[0];
        size--;
        shiftFront(0);
        return removedElement;
    }

    /**
     * Remove the last element in the list and return it.
     * 
     * Must be O(1).
     *
     * @return The data from the back of the list or null if the list is empty
     */
    public T removeFromBack() {
        if (size <= 0) return null;

        T lastElement = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;

        return lastElement;
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
