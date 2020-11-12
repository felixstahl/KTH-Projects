package princeton;
import java.util.Iterator;
import java.util.TreeMap;
/**
 *              README
 *       Author: Felix Ståhl
 *       Lab4 - Question 1 & 2
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 * Implementation of ordered Symbol Table.
 *
 */

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {
    private TreeMap<Key, Value> st;
    //Initializes an empty symbol table.
    public ST() {
        st = new TreeMap<Key, Value>();
    }
    //Returns the value associated with the given key in this symbol table.
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("calls get() with null key");
        return st.get(key);
    }

    /* Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is null.*/
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("calls put() with null key");
        if (val == null) st.remove(key);
        else             st.put(key, val);
    }

    //Removes the specified key and its associated value from this symbol table
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("calls delete() with null key");
        st.remove(key);
    }

    //Returns true if this symbol table contain the given key.
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("calls contains() with null key");
        return st.containsKey(key);
    }

    //Returns the number of key-value pairs in this symbol table.
    public int size() {
        return st.size();
    }

    //Returns true if this symbol table is empty.
    public boolean isEmpty() {
        return size() == 0;
    }

    //Returns all the keys as an iterable for the user to iterate over
    public Iterable<Key> keys() {
        return st.keySet();
    }

    // Returns all of the keys in this ST to iterate over @deprecated Replaced by #keys().
    public Iterator<Key> iterator() {
        return st.keySet().iterator();
    }
}