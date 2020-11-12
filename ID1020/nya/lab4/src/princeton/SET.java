package princeton;
import java.util.Iterator;
import java.util.TreeSet;
/**
 *              README
 *       Author: Felix Ståhl
 *       Lab4 - Question 2
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 * Implementation of an ordered SET used in Graph.java
 * A SET can not store the same key twice.
 *
 */

public class SET<Key extends Comparable<Key>> implements Iterable<Key> {
    private TreeSet<Key> set;
    // Initializes an empty set.
    public SET() {
        set = new TreeSet<>();
    }
    // Adds the key to this set (if it is not already present).
    public void add(Key key) {
        if (key == null) throw new IllegalArgumentException("called add() with a null key");
        set.add(key);
    }

    // Returns true if this set contains the given key.
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("called contains() with a null key");
        return set.contains(key);
    }

    // Removes the specified key from this set (if the set contains the specified key).
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("called delete() with a null key");
        set.remove(key);
    }

    // Returns the number of keys in this set.
    public int size() {
        return set.size();
    }

    // Returns true if this set is empty.
    public boolean isEmpty() {
        return size() == 0;
    }

    //Returns all of the keys in this set, as an iterator.
    public Iterator<Key> iterator() {
        return set.iterator();
    }
}

