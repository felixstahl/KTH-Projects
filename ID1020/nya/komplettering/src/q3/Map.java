package q3;

/**
 *              README
 *       Author: Felix Ståhl
 *       Extra lab - 3
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 * Implementation of a map and a linked list.
 * (using AQueue.java class)
 *
 * basic idea of the structure:
 * Map [hash]  -> [key(the word), value(word frequency), first (points back)]
 *    [hash2] -> [j]
 *    [hash3] -> [j]
 * Initially creates a big linked list, each index in that list is also linked list.
 * MostCommonWords.java adds words and map.java makes sure that they are put in the right place..
 *
 *
 * key is the word to add
 * hash is the integer hash of the word
 *
 */
public class Map {
    private LinkedList[] words;
    private int size = 0;   // number of words
    private int capacity;   // prime number, good for modulus is hashfunction

    // constructor
    public Map(int capacity) {
        this.capacity = capacity;
        words = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            words[i] = new LinkedList();
        }
    }

    // add key to map
    public void add(String key) {
        int hash = hash(key);   // make a hash for the String word (key)
        LinkedList.Node node = words[hash].get(key);
        if (node == null) {
            size++; // only increase if new word found
        }
        words[hash].insert(key);    // insert either way
    }

    // return word(key)
    public int get(String key) {
        int hash = hash(key);
        return words[hash].get(key).value;
    }

    // return a non-negative hash
    private int hash(String key) {
        return Math.floorMod(key.hashCode(), capacity);
    }

    // return the keys (queue)
    public AQueue<String> keys() {
        AQueue<String> queue = new AQueue<String>();
        for (int i = 0; i < capacity; i++) {
            for (String word : words[i].keys()) {
                queue.add(word);
            }
        }
        return queue;
    }

    // return size of map
    public int size() {
        return size;
    }

    // nested linked list class, check structure in readme at the top
    private class LinkedList {
        private int size;
        private Node first;

        // nested node class, every entry in linked list is a node
        private class Node {
            private String key;     // hash value
            private Integer value;  // number of words following
            private Node next;

            private Node(String key, Integer value, Node next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }

        // get a node from linked list
        public Node get(String key) {
            for (Node i = first; i != null; i = i.next) {
                if (key.equals(i.key)) {
                    return i;
                }
            }
            return null;
        }

        // insert in linked list
        public void insert(String key) {
            for (Node i = first; i != null; i = i.next) {
                if (key.equals(i.key)) {
                    i.value++; // number of words following
                    return;
                }
            }
            first = new Node(key, 1, first); // add new node with frequency 1 and point back to index node (hash.)
            size++;
        }

        // iterable for iteration
        public Iterable<String> keys() {
            AQueue<String> queue = new AQueue<String>();
            for (Node i = first; i != null; i = i.next) {
                queue.add(i.key);
            }
            return queue;
        }
    }
}