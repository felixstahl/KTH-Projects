import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *                   README -- Felix Ståhl
 * This one Implements a generic iterable FIFO-queue based on a double linked list.
 *
 */

public class Queue<Item> implements Iterable<Item> {
    private Node first; // link to least recently added node
    private Node last; // link to most recently added node
    private int N; // number of items on the queue
    private class Node { // nested class to define nodes
        Item item;
        Node next;
        Node previous;
    }
    public boolean isEmpty() { return first == null; } // returns true or false if empty or not
    public int size() { return N; }
    public void enqueue(Item item) { // Add item to the end of the list.
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else {
            last.previous = oldlast;
            oldlast.next = last;
        }
        N++;
    }
    public Item dequeue() { // Remove item from the beginning of the list.
        if(isEmpty())
            throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        return item;
    }
    public Iterator<Item> iterator() { return new ListIterator(); }     // in this assignment i actually use
    private class ListIterator implements Iterator<Item> {              // the iterator
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public void remove() { }
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public String toString(){                                           // in this assignment i actually
        StringBuilder stringbuilder = new StringBuilder();              // made a full string, not just separated
        Iterator<Item> itera = iterator();                              // printouts.
        while(itera.hasNext()){
            stringbuilder.append("[");
            stringbuilder.append(itera.next());
            stringbuilder.append("],");
        }
        if(stringbuilder.length() > 0)
            stringbuilder.setLength(stringbuilder.length() - 1);
        return stringbuilder.toString();
    }

    public static void main(String[] args){                             // a queue is created, main enqueues some items
        Queue<String> char_queue = new Queue<>();                       //
        char_queue.enqueue("f");                                   // later on i dequeue one.
        char_queue.enqueue("e");                                   // printout for results.
        char_queue.enqueue("l");
        char_queue.enqueue("i");
        char_queue.enqueue("x");
        System.out.println(char_queue.N);
        System.out.println(char_queue.toString());

        char_queue.dequeue();
        System.out.println(char_queue.N);
        System.out.println(char_queue.toString());

    }
}