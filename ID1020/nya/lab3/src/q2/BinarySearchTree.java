package q2;
import java.util.NoSuchElementException;
/**
 *              README
 *       Author: Felix Ståhl
 *       Lab3 - Question 2
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 *
 * This class implements the Binary Search Tree data structure, the measurements of the build speed etc
 * is found in the FrequencyCounter.java file.
 *
 *
 * Binary Search Tree implements a tree structure of nodes.
 * Best case is O(log N) for search, insert and delete.
 * Worst case is O(n).
 *
 * This is similar to binary search Symbol Table which implements 2 arrays, one for keys and one for associated value.
 *
 */

public class BinarySearchTree<Key extends Comparable<Key>, Value> {
	private Node root;              // root of Binary search tree

    // the node class that the BST consist of. private since BST is the only one able to call it.
	private class Node {
		private Key key;            // key
		private Value val;          // associated value
		private Node left, right;   // links to subtrees
		private int N;              // number of nodes in subtree rooted here
		
		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	// Constructor, no size input here since the tree just keeps on adding nodes
    public BinarySearchTree() {
    	//empty constructor
    }

    // returns the number of nodes in the tree
	public int size() {
		return size(root); 
	}

	// return the number of nodes from this node onwards
	private int size(Node node) {
		if (node == null)
			return 0;
		else
			return node.N;
	}

	// returns the asked for key
	public Value get(Key key) {
		return get(root, key);
	}

	// helper method for the above get(Key key)
	private Value get(Node x, Key key) {        // Return value associated with key in the subtree rooted at x;
		if (x == null) return null;		        // return null if key not present in subtree rooted at x.
			int cmp = key.compareTo(x.key);
		if (cmp < 0) 
			return get(x.left, key);
		else if (cmp > 0) 
			return get(x.right, key);
		else
			return x.val;
	}

	// insert key/value
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        if (val == null) {
            //delete(key); this is not implemented
            return;
        }
        root = put(root, key, val);
    }

    // helper method for the above put(Key key, Value val)
	private Node put(Node x, Key key, Value val) {
		if (x == null)
			return new Node(key, val, 1);    // create new node to subtree associating key with val if null.
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val = val;
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	// is this tree empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // does this tree contain key?
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    // return the largest node
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    // helper method for the above max()
    private Node max(Node x) {
        if (x.right == null)
            return x;
        else
            return max(x.right);
    }

    // return the smallest node
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    // helper method for the above min()
    private Node min(Node x) {
        if (x.left == null)
            return x;
        else
            return min(x.left);
    }

    // returns all the keys between the given keys
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    // helper method for the above keys(Key lo, Key, hi)
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) { 
        if (x == null) return; 
        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key); 
        if (cmplo < 0) keys(x.left, queue, lo, hi); 
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key); 
        if (cmphi > 0) keys(x.right, queue, lo, hi); 
    }

    // returns all the keys as an iterable for the user to iterate over
    public Iterable<Key> keys() {
        if (isEmpty())
        	return new Queue<Key>();
        return keys(min(), max());
    }
}