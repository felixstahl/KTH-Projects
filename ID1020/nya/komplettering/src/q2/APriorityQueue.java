package q2;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *              README
 *       Author: Felix Ståhl
 *       Extra lab - Question 2
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 * Implementation of a priority queue (binary min heap)
 *
 */
public class APriorityQueue<Key> implements Iterable<Key> {
    private Key[] pq;                    // store items at indices 1 to n
    private int n;                       // number of items on priority queue
    private Comparator<Key> comparator;

    //Initializes an empty priority queue with the given initial capacity.
    public APriorityQueue(int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    // Initializes an empty priority queue with the given initial capacity,
    public APriorityQueue(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    //Returns true if this priority queue is empty.
    public boolean isEmpty() {
        return n == 0;
    }

    // Returns the number of keys on this priority queue.
    public int size() {
        return n;
    }


    // Adds a new key to this priority queue. O(log n) time complexity
    public void insert(Key x) {
        pq[++n] = x;    // increase first, then add
        swim(n);
    }

    // Removes and returns a smallest key on this priority queue.
    public Key delMin() {
        if (isEmpty())
            throw new NoSuchElementException("Priority queue underflow");
        Key min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n+1] = null;     // garbage collection
        return min;
    }

    // when a new key is lower than the old key it needs to go up in the heap
    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    // when a new key is higher than the old key it has to go down
    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    // returns true if element1 is larger than element2, otherwise false
    private boolean greater(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        }
        else {  // if (pq[i] is lesser than pq[j] return negative)
            return comparator.compare(pq[i], pq[j]) > 0;  // return true if(element1 is larger than element2)
        }
    }

    // swap places
    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    //Returns an iterator for iteration
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {
        private APriorityQueue<Key> copy;    // create a new pq

        public HeapIterator() { // add all items to copy of heap
            if (comparator == null) copy = new APriorityQueue<Key>(size());
            else                    copy = new APriorityQueue<Key>(size(), comparator);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }
        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }
        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }
}