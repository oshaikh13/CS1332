import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Omar Shaikh
 * @userid oshaikh3
 * @GTID 903403821
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement cocktail sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * NOTE: See pdf for last swapped optimization for cocktail sort. You
     * MUST implement cocktail sort with this optimization
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {

        if (arr == null || comparator == null) {
            throw new 
                IllegalArgumentException("array or comparator cannot be null.");
        }

        boolean swapped = true;
        int startBound = 0;
        int endBound = arr.length - 1;
        while (startBound < endBound && swapped) {

            int lastSwap = arr.length - 1;
            int firstSwap = 0;

            swapped = false;
            for (int i = startBound; i < endBound; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    swapped = true;
                    lastSwap = i;
                }
            }

            endBound = lastSwap;

            if (swapped) {
                swapped = false;
                for (int i = endBound; i > startBound; i--) {
                    if (comparator.compare(arr[i], arr[i - 1]) < 0) {
                        swap(arr, i, i - 1);
                        swapped = true;
                        firstSwap = i;
                    }
                }
            }
            startBound = firstSwap;
        }

    
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {

        if (arr == null || comparator == null) {
            throw new 
                IllegalArgumentException("array or comparator cannot be null.");
        }

        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j - 1], arr[j]) > 0) {
                swap(arr, j, j - 1);
                j--;
            }
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {

        if (arr == null || comparator == null) {
            throw new 
                IllegalArgumentException("array or comparator cannot be null.");
        }

        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new 
                IllegalArgumentException("array or comparator cannot be null.");
        }

        if (arr.length == 0 || arr.length == 1) {
            return;
        }

        T[] leftArray = copyArray(arr, 0, arr.length / 2);
        T[] rightArray = copyArray(arr, arr.length / 2, arr.length);

        mergeSort(leftArray, comparator);
        mergeSort(rightArray, comparator);

        int leftIndex = 0;
        int rightIndex = 0;
        int currentIndex = 0;
        
        while (leftIndex < leftArray.length 
            && rightIndex < rightArray.length) {
            if (comparator.compare(leftArray[leftIndex], 
                rightArray[rightIndex]) <= 0) {
                arr[currentIndex++] = leftArray[leftIndex++];
            } else {
                arr[currentIndex++] = rightArray[rightIndex++];
            }
        }

        while (leftIndex < leftArray.length) {
            arr[currentIndex++] = leftArray[leftIndex++];
        }

        while (rightIndex < rightArray.length) {
            arr[currentIndex++] = rightArray[rightIndex++];
        }
        
    }

    /**
     * Creates a copy of an array within bounds.
     * 
     * @param arr the specified array
     * @param start the starting index of copy (inclusive)
     * @param end the ending index of the copy (exclusive)
     * @param <T> the datatype of the array elements
     * @return the copied array
     */
    public static <T> T[] copyArray(T[] arr, int start, int end) {
        T[] copy = (T[]) new Object[end - start];
        for (int i = start, j = 0; i < end; i++, j++) {
            copy[j] = arr[i];
        }
        return copy;
    }


    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * Do NOT use anything from the Math class except Math.abs
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     */
    public static void lsdRadixSort(int[] arr) {

        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        int longest = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (Math.abs(arr[i]) > longest) {
                longest = Math.abs(arr[i]);
            } else if (arr[i] == Integer.MIN_VALUE) {
                longest = Integer.MAX_VALUE;
            }
        }

        int iterations = 0;
        while (longest != 0) {
            longest /= 10;
            iterations++;
        }

        LinkedList<Integer>[] buckets = new LinkedList[19];

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<Integer>();
        }

        long divisor = 1;
        for (int i = 0; i < iterations; i++, divisor *= 10) {
            for (int item : arr) {
                buckets[(int) (item / divisor) % 10 + 9].add(item);
            }

            int index = 0;
            for (LinkedList<Integer> list : buckets) {
                while (!list.isEmpty()) {
                    arr[index++] = list.remove();
                }
            }

        }
    }

    /**
     * Implement kth select.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null or k is not in the range of 1 to arr.length
     * @param <T> data type to sort
     * @param k the index to retrieve data from + 1 (due to 0-indexing) if
     *          the array was sorted; the 'k' in "kth select"; e.g. if k ==
     *          1, return the smallest element in the array
     * @param arr the array that should be modified after the method
     * is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @return the kth smallest element
     */
    public static <T> T kthSelect(int k, T[] arr, Comparator<T> comparator,
                                  Random rand) {
        if (rand == null || arr == null || comparator == null) {
            throw new 
                IllegalArgumentException("input array/comparator/rand is null");
        }
        
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("k is not within range of arr");
        }

        int kthElementIdx = 
            kthSelectHelper(k - 1, arr, 0, arr.length, comparator, rand);

        return arr[kthElementIdx];
    }

    /**
     * Recursive helper for kthSelect.
     * 
     * @param <T> data type to sort
     * @param k the index to retrieve data from + 1 (due to 0-indexing) if
     *          the array was sorted; the 'k' in "kth select"; e.g. if k ==
     *          1, return the smallest element in the array
     * @param arr the array that should be modified after the method
     * is finished executing as needed
     * @param left the leftbound of the array to find the kth element in.
     * @param right the rightbound of the array to find the kth element in.
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @return the index of the kth smallest element
     */
    private static <T> int kthSelectHelper(int k, T[] arr, int left, int right,
                                    Comparator<T> comparator, Random rand) {
        
        int pivotIdx = rand.nextInt(right - left) + left;
        int i = left + 1;
        int j = right - 1;

        T pivot = arr[pivotIdx];
        swap(arr, pivotIdx, left);

        while (i <= j) {
            while (i <= j && comparator.compare(arr[i], pivot) <= 0) {
                i++;
            }
            while (i <= j && comparator.compare(arr[j], pivot) >= 0) {
                j--;
            }
            if (i < j) {
                swap(arr, i, j);
                j--;
                i++;
            }
        }

        swap(arr, j, left);

        if (k > j) {
            return kthSelectHelper(k, arr, j + 1, right, comparator, rand);
        } else if (k < j) {
            return kthSelectHelper(k, arr, left, j, comparator, rand);
        }
        
        return j;
        
    }

    /**
     * Swaps two elements in a array.
     * 
     * @param arr the specified array
     * @param i the index of element i
     * @param j the index of element j 
     * @param <T> the datatype of the array elements
     */
    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
