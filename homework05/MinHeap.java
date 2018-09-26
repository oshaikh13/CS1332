import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a min heap.
 *
 * @author Omar Shaikh
 * @userid oshaikh3
 * @GTID 903403821
 * @version 1.0
 */
public class MinHeap<T extends Comparable<? super T>> {

    public static final int INITIAL_CAPACITY = 13;

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial capacity of {@code INITIAL_CAPACITY}
     * for the backing array.
     *
     * Use the constant field provided. Do not use magic numbers!
     */
    public MinHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY]; 
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the Build Heap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     *
     * The data in the backingArray should be in the same order as it appears
     * in the ArrayList before you start the Build Heap Algorithm.
     *
     * The {@code backingArray} should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY from
     * the interface). Index 0 should remain empty, indices 1 to n should
     * contain the data in proper order, and the rest of the indices should
     * be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public MinHeap(ArrayList<T> data) {

        if (data == null) {
            throw new IllegalArgumentException();
        }

        backingArray = (T[]) new Comparable[2 * data.size() + 1]; 
        size = data.size();
        // copy
        for (int i = 0; i < data.size(); i++) {
            T addedElement = data.get(i);
            if (addedElement == null) {
                throw new IllegalArgumentException();
            }
            backingArray[i + 1] = addedElement;
        }

        // heapify
        for (int i = size / 2; i >= 1; i--) {
            minHeapifyDown(i);
        }
    }

    private void resizeBacking () {
        T[] newBacking = (T[]) new Comparable[backingArray.length * 2];
        for (int i = 1; i < backingArray.length; i++) {
            newBacking[i] = backingArray[i];
        }
        backingArray = newBacking;
    }

    /**
     * Adds an item to the heap. If the backing array is full and you're trying
     * to add a new item, then double its capacity.
     *
     * @throws IllegalArgumentException if the item is null
     * @param item the item to be added to the heap
     */
    public void add(T item) {
        if (item == null) throw new IllegalArgumentException();

        if (++size >= backingArray.length) {
            resizeBacking();
        }

        backingArray[size] = item;
        minHeapifyUp(size);

    }

    /**
     * Removes and returns the min item of the heap. Null out all elements not
     * existing in the heap after this operation. Do not decrease the capacity
     * of the backing array.
     *
     * @throws java.util.NoSuchElementException if the heap is empty
     * @return the removed item
     */
    public T remove() {
        if (size == 0) throw new NoSuchElementException();

        T minElement = getMin();

        swap(1, size);
        backingArray[size] = null;
        size--;


        minHeapifyDown(1);
        return minElement;
    }

    /**
     * Returns the minimum element in the heap.
     *
     * @return the minimum element, null if the heap is empty
     */
    public T getMin() {
        return backingArray[1];
    }

    /**
     * Returns if the heap is empty or not.
     *
     * @return true if the heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the heap and returns array to {@code INITIAL_CAPACITY}.
     */
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY]; 
        size = 0;
    }

    private void minHeapifyUp (int heapifyIdx) {
        int parent = parent(heapifyIdx);
        int smallest = heapifyIdx;

        if (parent > 0 && backingArray[parent].compareTo(backingArray[heapifyIdx]) > 0){
            smallest = parent;
        };

        if (smallest != heapifyIdx) {
            swap(heapifyIdx, smallest);
            minHeapifyUp(smallest);
        }
    }

    private void minHeapifyDown (int heapifyIdx) {
        int leftChild = leftChild(heapifyIdx);
        int rightChild = rightChild(heapifyIdx);

        int smallest = heapifyIdx;

        if (leftChild <= size && backingArray[heapifyIdx].compareTo(backingArray[leftChild]) > 0){
            smallest = leftChild;
        };

        if (rightChild <= size && backingArray[smallest].compareTo(backingArray[rightChild]) > 0){
            smallest = rightChild;
        };

        if (smallest != heapifyIdx) {
            swap(heapifyIdx, smallest);
            minHeapifyDown(smallest);
        }
    }

    private void swap (int idxA, int idxB) {
        T temp = backingArray[idxA];
        backingArray[idxA] = backingArray[idxB];
        backingArray[idxB] = temp;
    }

    private int parent (int idx) {
        return idx / 2;
    }

    private int leftChild (int idx) {
        return idx * 2;
    }

    private int rightChild (int idx) {
        return idx * 2 + 1;
    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the number of elements in the heap
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Return the backing array for the heap.
     *
     * For grading purposes only. DO NOT USE THIS METHOD IN YOUR CODE!
     *
     * @return the backing array for the heap
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

}