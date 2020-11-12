package princeton;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 *              README
 *       Author: Felix Ståhl
 *       Lab4 - Question 1
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 *  The Bag class represents a bag (or multiset) of generic items.
 *  It supports insertion and iterating over the items in random order.
 *  This implementation uses a single linked list with a static nested class Node.
 *
 */

public class Bag<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of bag
    private int n;               // number of elements in bag

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    // Initializes an empty bag.
    public Bag() {
        first = null;
        n = 0;
    }

    //Returns true if this bag is empty.
    public boolean isEmpty() {
        return first == null;
    }

    // Returns the number of items in this bag.
    public int size() {
        return n;
    }

    //Adds the item to this bag.
    public void add(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    //Returns an iterator that iterates over the items in this bag in arbitrary order.
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

    // test the bag data type.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Bag<String> bag = new Bag<String>();
        while (scanner.hasNext()) {
            String item = scanner.next();
            bag.add(item);
        }

        System.out.println("size of bag = " + bag.size());
        for (String s : bag) {
            System.out.println(s);
        }
    }
}