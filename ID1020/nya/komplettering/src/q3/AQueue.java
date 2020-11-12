package q3;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *              README
 *       Author: Felix Ståhl
 *       Extra lab - 3
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 * Implementation of a fifo-queue
 *
 */
public class AQueue<Item> implements Iterable<Item> {
    private int size;   // number of elements in queue
    private Node first;
    private Node last;

    // nested node class, each entry in queue is a node
    private class Node {
        private Item item;
        private Node next;
    }

    public AQueue() {
        first = null;
        last = null;
        size = 0;
    }

    // add item
    public void add(Item item) {
        Node oldLast = last;
        last = new Node();
        last.next = null;
        last.item = item;
        if (size == 0) {
            first = last;
        } else {
            oldLast.next = last;
        }
        ++size;
    }

    // return length
    public int length() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public void remove() { }
        public Item next() {
            if (current == null) {
                throw  new NoSuchElementException();
            } else {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }
}