package q3;
import java.util.NoSuchElementException;
/**
 *              README
 *       Author: Felix Ståhl
 *       Lab3 - Question 3 & 4
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 *
 * This class implements the Binary Search Symbol Table data structure (ordered symbol table),
 * the measurements of the build speed, etc, is found in the FrequencyCounter.java file.
 *
 * binary search Symbol Table implements 2 arrays, one for keys and one for the associated value.
 * Best & Worst case O(n) for search but instant insertion and deletion.
 * This is because it implements Queue data structure. that has best & worst case O(n) for search but
 * instant insertion and deletion.
 *
 */

public class SymbolTable<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int N;

    // constructor. send it the size of the table.
    public SymbolTable(int capacity) {      // constructor, always have to send a size
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    // returns the size of the ST
    public int getSize() {
        return N;
    }

    // checks if this symbol table has given key
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    // is this table empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // get the value of specific key
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return values[i];
        return null;
    }

    // returns all the keys as an iterable for the user to iterate over
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    // returns all the keys between the given keys
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++)
            queue.enqueue(keys[i]);
        if (contains(hi)) queue.enqueue(keys[rank(hi)]);
        return queue;
    }

    // returns smallest key in ST
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return keys[0];
    }

    // returns the biggest key in ST
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return keys[N-1];
    }

    // returns an integer of the amount of keys that is less than this key
    public int rank(Key key) {
        int low = 0;
        int high = N - 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = key.compareTo(keys[mid]);

            if(cmp < 0)
                high = mid - 1;
            else if(cmp > 0)
                low = mid + 1;
            else
                return mid;
        }
        return low;
    }

    // add the key & value to the ST.
    public void put(Key key, Value value) {
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        for(int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }
}