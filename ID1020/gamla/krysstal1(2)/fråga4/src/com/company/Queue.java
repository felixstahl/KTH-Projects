import java.util.Iterator;

/* *
*                   README -- Felix Ståhl
* This program implements a generic iterable circular linked list which allows the user to
* insert and remove elements to/from the front and back end of the queue.
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
    public boolean isEmpty() { return first == null; } // returns true or false if the queue is empty or not.
    public int size() { return N; }
    public void enqueue_front(Item item) { // Add item to the front of the list.
        if(isEmpty()){
            first = new Node();
            last = first;
            first.item = item;
        }else {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            first.previous = last;
            oldfirst.previous = first;
            last.next = first;
        }
        N++;
    }
    public void enqueue_back(Item item) { // Add item to the end of the list.
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = first;
        if (isEmpty()) first = last;
        else{
            last.previous = oldlast;
            oldlast.next = last;
        }
        N++;
    }
    public Item dequeue_front() { // Remove item from the beginning of the list.
        Item item = first.item;
        first = first.next;
        first.previous = last;
        last.next = first;
        if (isEmpty()) last = null;
        N--;
        return item;
    }
    public Item dequeue_back() { // Remove item from the back of the list.
        Item item = last.item;
        last = last.previous;
        last.next = first;
        if (isEmpty()) last = null;
        N--;
        return item;
    }
    public Iterator<Item> iterator() { return new ListIterator(); }
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public void remove() { }
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public String toString(){                                       // in this assignment the stringbuilder is used
        StringBuilder stringbuilder = new StringBuilder();          // compaired to assignment 2 where i just
        Iterator<Item> itera = iterator();                          // printed each character as a separate symbol.
        int n = this.N;
        for(int i = 0; i < N; i++){
            stringbuilder.append("[");
            stringbuilder.append(itera.next());
            stringbuilder.append("],");
        }
        if(stringbuilder.length() > 0)
            stringbuilder.setLength(stringbuilder.length() - 1);
        return stringbuilder.toString();
    }

    public static void main(String[] args){                         // whole lot of tests
        Queue<String> circular_queue = new Queue<>();               // queues from the front
        circular_queue.enqueue_front("x");
        circular_queue.enqueue_front("i");
        circular_queue.enqueue_front("l");
        circular_queue.enqueue_front("e");
        circular_queue.enqueue_front("f");
        System.out.println(circular_queue.N);
        System.out.println(circular_queue.toString());

        circular_queue.dequeue_front();                             //dequeues from the front
        System.out.println(circular_queue.N);
        System.out.println(circular_queue.toString());

        circular_queue.dequeue_back();                              // dequeues from the back
        System.out.println(circular_queue.N);
        System.out.println(circular_queue.toString());

        circular_queue.enqueue_front("f");                    // enqueues for the front
        System.out.println(circular_queue.N);
        System.out.println(circular_queue.toString());
        circular_queue.enqueue_back("x");                     // enqueues from the back
        System.out.println(circular_queue.N);
        System.out.println(circular_queue.toString());

        System.out.print("done");
    }
}