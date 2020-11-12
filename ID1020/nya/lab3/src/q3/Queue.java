package q3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 *              README
 *       Author: Felix Ståhl
 *       Lab3 - Question 3 & 4
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 *
 * Implemented by binary search Symbol Table (ordered Symbol Table).
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

    //create an empty queue.
    public Queue() {
        first = null;
        last  = null;
        n = 0;
    }

    //returns true if this queue is empty.
    public boolean isEmpty() {
        return first == null;
    }

    //returns the number of items in this queue.
    public int size() {
        return n;
    }

    //returns the item least recently added to this queue. exception if empty
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    //Adds the item to this queue.
    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty())
            first = last;
        else
            oldlast.next = last;
        n++;
    }

    //removes and returns the item on this queue that was least recently added. exception if empty
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty())
            last = null;   // to avoid loitering
        return item;
    }

    //returns a string of this queue. each item is a word.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    //returns an iterator that iterates over the items in this queue in FIFO order.
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);
    }

    //an iterator, doesn't implement remove() since it's optional
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

    // test the queue
    public static void main(String[] args)throws FileNotFoundException {

        File file = new File("C:\\Users\\mr_fe\\Desktop\\Algo\\Algo2\\nya\\lab3\\src\\editedVersion.txt");
        Scanner scanner = new Scanner(file);
        Queue<String> queue = new Queue<String>();
        while (scanner.hasNext()) {
            String item = scanner.next();
            if (!item.equals("-"))
                queue.enqueue(item);
            else if (!queue.isEmpty())
                System.out.println(queue.dequeue() + " ");
        }
        System.out.println("(" + queue.size() + " left in queue)");
    }
}
