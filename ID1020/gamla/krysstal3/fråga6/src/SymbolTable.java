import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;

/**
 *                  README - Felix Ståhl - Assignment6
 * time in nanoseconds = 1213457268184114
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

    public int getSize() {  //returns the size
        return N;
    }

    public boolean isEmpty() {      // returns true if empty, false if not
        return N == 0;
    }

    public boolean contains(Key key) {  //checks if ST contains the key
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public Value get(Key key) { // returns the value of the key
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
        return keys[N - 1];
    }

    public int rank(Key key) {
        int low = 0, high = N - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = key.compareTo(keys[mid]);

            if (cmp < 0) high = mid - 1;
            else if (cmp > 0) low = mid + 1;
            else return mid;
        }
        return low;
    }

    public void put(Key key, Value value) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (isEmpty()) return;
        // compute rank
        int i = rank(key);
        // key not in table
        if (i == N || keys[i].compareTo(key) != 0) {
            return;
        }
        for (int j = i; j < N - 1; j++) {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }
        N--;
        keys[N] = null;  // to avoid loitering
        values[N] = null;
    }
    public void freqFromNToX(SymbolTable st, int n, int x) {
        n = n - 1;
        x = x - 1;
        SymbolTable<String, Integer> tmpST = new SymbolTable<>(st.getSize() + 1);
        String[] orderedByValue = new String[st.getSize()];

        for (int i = 0; i < st.getSize(); i++) {
            tmpST.put((String)st.keys[i], (Integer)st.get(st.keys[i]));
        }
        for (int i = 0; i < tmpST.getSize() - 1; i++) {
            String max = "";
            tmpST.put(max, 0);
            for (String word : tmpST.keys())
                if (tmpST.get(word) > tmpST.get(max))
                    max = word;
            orderedByValue[i] = max;
            tmpST.delete(max);
        }
        while (n < x + 1) {
            System.out.println("Word: " + orderedByValue[n] + " Frequency: " + st.get(orderedByValue[n]));
            n++;
        }
    }
}