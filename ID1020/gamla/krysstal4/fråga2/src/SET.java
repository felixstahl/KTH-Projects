import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeSet;

/**
 *              README - Felix Ståhl - Assignment2
 *
 */
public class SET<Key extends Comparable<Key>> implements Iterable<Key> {
    private TreeSet<Key> set;

    // Initializes an empty set.
    public SET() {
        set = new TreeSet<Key>();
    }

    // Initializes a new set that is an independent copy of the specified set.
    public SET(SET<Key> x) {
        set = new TreeSet<Key>(x.set);
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

    // Returns the largest key in this set.
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty set");
        return set.last();
    }

    // Returns the smallest key in this set.
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty set");
        return set.first();
    }


    // Returns the smallest key in this set greater than or equal to key.
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("called ceiling() with a null key");
        Key k = set.ceiling(key);
        if (k == null) throw new NoSuchElementException("all keys are less than " + key);
        return k;
    }

    // Returns the largest key in this set less than or equal to key.
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("called floor() with a null key");
        Key k = set.floor(key);
        if (k == null) throw new NoSuchElementException("all keys are greater than " + key);
        return k;
    }

    // Returns the union of this set and that set.
    public SET<Key> union(SET<Key> that) {
        if (that == null) throw new IllegalArgumentException("called union() with a null argument");
        SET<Key> c = new SET<Key>();
        for (Key x : this) {
            c.add(x);
        }
        for (Key x : that) {
            c.add(x);
        }
        return c;
    }

    // Returns the intersection of this set and that set.
    public SET<Key> intersects(SET<Key> that) {
        if (that == null) throw new IllegalArgumentException("called intersects() with a null argument");
        SET<Key> c = new SET<Key>();
        if (this.size() < that.size()) {
            for (Key x : this) {
                if (that.contains(x)) c.add(x);
            }
        }
        else {
            for (Key x : that) {
                if (this.contains(x)) c.add(x);
            }
        }
        return c;
    }

    // Compares this set to the specified set.
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        SET that = (SET) other;
        return this.set.equals(that.set);
    }

    // This operation is not supported because sets are mutable.
    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("hashCode() is not supported because sets are mutable");
    }

    // Returns a string representation of this set.
    @Override
    public String toString() {
        String s = set.toString();
        return "{ " + s.substring(1, s.length() - 1) + " }";
    }
}
