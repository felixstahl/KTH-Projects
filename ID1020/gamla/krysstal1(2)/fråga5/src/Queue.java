import java.util.Iterator;

/**
 *                      README -- Felix Ståhl
 * Implements a generalized queue which allows the user to remove the k:th element from the queue.
 * The most recently added element has index 1.
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
        int index;
    }
    public boolean isEmpty() { return first == null; }  // returns true or false depending on if its empty or not
    public int size() { return N; }
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public void remove(){ }
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public String toString(){                                       // Stringbuilder is used. creating a string for
        StringBuilder stringbuilder = new StringBuilder();          // for printouts.
        Iterator<Item> itera = iterator();

        for(int i = 0; i < N; i++){
            stringbuilder.append("[");
            stringbuilder.append(itera.next());
            stringbuilder.append("],");
        }
        if(stringbuilder.length() > 0)                              // removes las comma
            stringbuilder.setLength(stringbuilder.length() - 1);
        return stringbuilder.toString();
    }

    private void indexATM(){                                        // everytime a nodes is removed or queued
        int sizeATM = N;                                            // it will use index ATM to update the index
        Node currentNode = first;
        while(sizeATM > 0){
            currentNode.index = sizeATM;
            currentNode = currentNode.next;
            sizeATM--;
        }
    }

    public void enqueue_back(Item item) { // Add item to the end of the list. and updates index.
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = first;
        if(isEmpty()){
            first = last;
        }
        else{
            last.previous = oldlast;
            oldlast.next = last;
        }
        N++;
        indexATM();
    }

    public Item remove_at_position(int position){          // removes the k:th element and updates the index
        Node current = first;
        while(current != null){
            if(current.index == position){
                if(current.index==N){
                    first = first.next;
                    first.previous = null;
                }
                if(current.index == last.index){            //checks if the node to be removed is the last.
                    last = current.previous;
                    last.next = null;
                }
                else if((current.index != N) && (current.index != last.index)){
                    current.next.previous = current.previous;
                    current.previous.next = current.next;
                }
                N--;
                indexATM();
                return current.item;
            }
            else{
                current = current.next;
            }
        }
        return null;
    }

    public static void main(String[] args){
        Queue<String> circular_queue = new Queue<>();
        circular_queue.enqueue_back("f");
        circular_queue.enqueue_back("e");
        circular_queue.enqueue_back("l");
        circular_queue.enqueue_back("i");
        circular_queue.enqueue_back("x");
        System.out.println(circular_queue.N);
        System.out.println(circular_queue.toString());

        System.out.println(circular_queue.remove_at_position(5));   // ***the following lines are for test****
        System.out.println(circular_queue.toString());              // it does give nullpointerexception when
        System.out.println(circular_queue.remove_at_position(4));   // everything all of this lines are running
        System.out.println(circular_queue.toString());              // after each other but that is because it runs out
        System.out.println(circular_queue.remove_at_position(3));   // of numbers. try just printing one line at a time.
        System.out.println(circular_queue.toString());              // and use // in front of the other test lines.
        System.out.println(circular_queue.remove_at_position(2));
        System.out.println(circular_queue.toString());
        System.out.println(circular_queue.remove_at_position(1));
        System.out.println(circular_queue.toString());

        System.out.print("done");
    }
}