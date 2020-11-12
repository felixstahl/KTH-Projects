import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;

/**
 *                  README - Felix Ståhl - Assignment2
 *time in nanoseconds = 1213457268184114
 *
 */
public class SymbolTable<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int N;

    public SymbolTable(int capacity) {      // constructor
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }
    public int getSize() {
        return N;
    }
    public boolean isEmpty() {
        return N == 0;
    }
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return values[i];
        return null;
    }
    public Iterable<Key> keys() {
        return keys(min(), max());
    }
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
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return keys[0];
    }
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return keys[N-1];
    }
    public int rank(Key key) {
        int low = 0, high = N - 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = key.compareTo(keys[mid]);

            if(cmp < 0) high = mid - 1;
            else if(cmp > 0) low = mid + 1;
            else return mid;
        }
        return low;
    }
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
