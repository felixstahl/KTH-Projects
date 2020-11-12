package princeton;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 *              README
 *       Author: Felix Ståhl
 *       Lab4 - Question 2
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 *  Implements a (FIFO singly linked list) Queue data structure used by BreadthFirstSearch.java
 *
 */

public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private int n;               // number of elements on queue

    //helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    //Initializes an empty queue.
    public Queue() {
        first = null;
        last  = null;
        n = 0;
    }

    //Returns true if this queue is empty.
    public boolean isEmpty() {
        return first == null;
    }

    //Returns the number of items in this queue.
    public int size() {
        return n;
    }

    //Adds the item to this queue.
    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        n++;
    }

    //removes and returns the item on this queue that was least recently added.
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    //Returns a string representation of this queue.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    } 

    //Returns an iterator that iterates over the items in this queue in FIFO order.
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
}
