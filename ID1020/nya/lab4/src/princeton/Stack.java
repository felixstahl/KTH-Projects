package princeton;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 *              README
 *       Author: Felix Ståhl
 *       Lab4 - Question 1 & 2
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 *  The Stack class represents a last-in-first-out (LIFO) stack of generic items.
 *
 */

public class Stack<Item> implements Iterable<Item> {
    private Node<Item> first;     // top of stack
    private int n;                // size of the stack

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    //Initializes an empty stack.
    public Stack() {
        first = null;
        n = 0;
    }

    //Returns true if this stack is empty.
    public boolean isEmpty() {
        return first == null;
    }

    //Returns the number of items in this stack.
    public int size() {
        return n;
    }

    //Adds the item to this stack.
    public void push(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    //Removes and returns the item most recently added to this stack.
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        n--;
        return item;                   // return the saved item
    }

    //Returns a string representation of this stack.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    //Returns an iterator to this stack that iterates through the items in LIFO order.
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }

    //tests the stack data type.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<String> stack = new Stack<String>();
        while (scanner.hasNext()) {
            String item = scanner.next();
            if (!item.equals("-"))
                stack.push(item);
            else if (!stack.isEmpty())
                System.out.print(stack.pop() + " ");
        }
        System.out.println("(" + stack.size() + " left on stack)");
    }
}