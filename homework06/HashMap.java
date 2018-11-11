import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of HashMap.
 * 
 * @author Omar Shaikh
 * @userid oshaikh3
 * @GTID 903403821
 * @version 1.0
 */
public class HashMap<K, V> {

    // DO NOT MODIFY OR ADD NEW GLOBAL/INSTANCE VARIABLES
    public static final int INITIAL_CAPACITY = 13;
    public static final double MAX_LOAD_FACTOR = 0.67;
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(HashMap.INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = new MapEntry[initialCapacity];
    }

    /**
     * Adds the given key-value pair to the HashMap.
     * If an entry in the HashMap already has this key, replace the entry's
     * value with the new one passed in.
     *
     * In the case of a collision, use linear probing as your resolution
     * strategy.
     *
     * At the start of the method, you should check to see if the array would
     * violate the max load factor after adding the data (regardless of
     * duplicates). For example, let's say the array is of length 5 and the
     * current size is 3 (LF = 0.6). For this example, assume that no elements
     * are removed in between steps. If another entry is attempted to be added,
     * before doing anything else, you should check whether (3 + 1) / 5 = 0.8
     * is larger than the max LF. It is, so you would trigger a resize before
     * you even attempt to add the data or figure out if it's a duplicate.
     *
     * When regrowing, resize the length of the backing table to
     * 2 * old length + 1. You must use the resizeBackingTable method to do so.
     *
     * Return null if the key was not already in the map. If it was in the map,
     * return the old value associated with it.
     *
     * @param key key to add into the HashMap
     * @param value value to add into the HashMap
     * @throws IllegalArgumentException if key or value is null
     * @return null if the key was not already in the map. If it was in the
     * map, return the old value associated with it
     */
    public V put(K key, V value) {

        if (key == null || value == null) {
            throw new IllegalArgumentException("Cannot get null key!");
        }

        if ((size + 1) / ((double) table.length) > HashMap.MAX_LOAD_FACTOR) {
            this.resizeBackingTable(2 * this.table.length + 1);
        }

        return probePut(key, value);

    }


    /**
     * Helper probe method for put
     *
     * @param key key to add into the HashMap
     * @param value value to add into the HashMap
     * @throws IllegalStateException if value cannot be put into table
     * @return null if the key was not already in the map. If it was in the
     * map, return the old value associated with it
     */
    private V probePut(K key, V value) {
        // linear probing
        int removedLocation = -1;
        int nullLocation = -1;
        for (int i = hash(key), j = 0; 
            j < table.length; i = (i + 1) % table.length, j++) {
            
            if (table[i] == null) {
                nullLocation = i;
                break;
            }  
            
            if (table[i].isRemoved() && removedLocation == -1) {
                removedLocation = i;
            }  
            
            if (table[i].getKey().equals(key)) {
                V oldVal = table[i].getValue();
                table[i].setValue(value);
                if (table[i].isRemoved()) {
                    table[i].setRemoved(false);
                    size++;
                    return null;
                }
                return oldVal;
            }
            
        }

        if (removedLocation != -1) {
            table[removedLocation].setRemoved(false);
            table[removedLocation].setKey(key);
            table[removedLocation].setValue(value);
            size++;
            return null;
        }

        if (nullLocation != -1) {
            table[nullLocation] = new MapEntry<K, V>(key, value);
            size++;
            return null;
        }

        throw new IllegalStateException("Space to put cannot be found.");

    }

    /**
     * Removes the entry with a matching key from the HashMap.
     *
     * @param key the key to remove
     * @throws IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException if the key does not exist
     * @return the value previously associated with the key
     */
    public V remove(K key) {
        MapEntry<K, V> oldMapEntry = getMapEntry(key);
        V returnValue = oldMapEntry.getValue();
        oldMapEntry.setRemoved(true);
        size--;
        return returnValue;
    }

    /**
     * Gets the value associated with the given key.
     *
     * @param key the key to search for
     * @throws IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException if the key is not in the map
     * @return the value associated with the given key
     */
    public V get(K key) {
        return getMapEntry(key).getValue();
    }

    /**
     * Helper that gets the MapEntry associated with the given key.
     *
     * @param key the key to search for
     * @throws IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException if the key is not in the map
     * @return the MapEntry associated with the given key
     */
    private MapEntry<K, V> getMapEntry(K key) {
        // linear probing
        if (key == null) {
            throw new IllegalArgumentException("Cannot get null key!");
        }

        for (int i = hash(key), j = 0; 
            j < table.length; i = (i + 1) % table.length, j++) {
            if (table[i] == null) {
                throw new NoSuchElementException("The element cannot" 
                + " be found in the table");
            }

            if (table[i].isRemoved()) {
                continue;
            }

            if (table[i].getKey().equals(key)) {
                return table[i];
            }

        }

        throw new NoSuchElementException("The element cannot" 
        + " be found in the table");
    }

    /**
     * Returns whether or not the key is in the map.
     *
     * @param key the key to search for
     * @throws IllegalArgumentException if key is null
     * @return whether or not the key is in the map
     */
    public boolean containsKey(K key) {

        try {
            getMapEntry(key);
        } catch (NoSuchElementException e) {
            return false;
        }

        return true;

    }

    /**
     * Returns a Set view of the keys contained in this map.
     * Use {@code java.util.HashSet}.
     *
     * @return set of keys in this map
     */
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (int i = 0, j = 0; i < table.length && j < size; i++) {

            if (table[i] == null) {
                continue;
            }

            if (table[i].isRemoved()) {
                continue;
            }

            j++;
            keys.add(table[i].getKey());

        }

        return keys;
        
    }

    /**
     * Returns a List view of the values contained in this map.
     *
     * Use {@code java.util.ArrayList} or {@code java.util.LinkedList}.
     *
     * You should iterate over the table in order of increasing index and add 
     * entries to the List in the order in which they are traversed.
     *
     * @return list of values in this map
     */
    public List<V> values() {
        List<V> values = new ArrayList<>();
    
        for (int i = 0, j = 0; i < table.length && j < size; i++) {

            if (table[i] == null) {
                continue;
            }

            if (table[i].isRemoved()) {
                continue;
            }

            j++;
            values.add(table[i].getValue());

        }

        return values;
    }

    /**
     * Resize the backing table to {@code length}.
     *
     * Disregard the load factor for this method. So, if the passed in length is
     * smaller than the current capacity, and this new length causes the table's
     * load factor to exceed MAX_LOAD_FACTOR, you should still resize the table
     * to the specified length and leave it at that capacity.
     *
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     *
     * Remember that you cannot just simply copy the entries over to the new
     * array.
     *
     * Also, since resizing the backing table is working with the non-duplicate
     * data already in the table, you shouldn't need to check for duplicates.
     *
     * @param length new length of the backing table
     * @throws IllegalArgumentException if length is less than the number of
     * items in the hash map
     */
    public void resizeBackingTable(int length) {
        if (length < size) {
            throw new IllegalArgumentException("Length of resize cannot" 
            + " be less than size.");
        }

        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[length];
        int oldSize = size;
        for (int i = 0, j = 0; i < oldTable.length && j < oldSize; i++) {

            if (oldTable[i] == null) {
                continue;
            }

            if (oldTable[i].isRemoved()) {
                continue;
            }

            j++;
            this.probePut(oldTable[i].getKey(), oldTable[i].getValue());
        } 
        size = oldSize;
    }

    /**
     * Returns the hashcode of a key.
     *
     * @param key the hashmap key
     * @return the corresponding hashcode of the key
     */
    private int hash(K key) {
        int backingIndex = key.hashCode() % table.length;
        return backingIndex < 0 ? Math.abs(backingIndex) : backingIndex;
    }

    /**
     * Clears the table and resets it to {@code INITIAL_CAPACITY}.
     */
    public void clear() {
        table = new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }
    
    /**
     * Returns the number of elements in the map.
     *
     * DO NOT USE OR MODIFY THIS METHOD!
     *
     * @return number of elements in the HashMap
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * DO NOT USE THIS METHOD IN YOUR CODE. IT IS FOR TESTING ONLY.
     *
     * @return the backing array of the data structure, not a copy.
     */
    public MapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

}