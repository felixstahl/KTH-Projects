import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;
/**
 *                      README - Felix Ståhl - Assignment4
 * BinarySearchTree in nanoseconds = 1213574906277587
 *
 *
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {
	private Node root; // root of BST
	private class Node {
		private Key key; // key
		private Value val; // associated value
		private Node left, right; // links to subtrees
		private int N; // # nodes in subtree rooted here
		
		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}
    public BinarySearchTree() {
    	//empty constructor
    }
	public int size() {
		return size(root); 
	}
	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.N;
	}
	public Value get(Key key) {
		return get(root, key);
	}
	private Value get(Node x, Key key) { // Return value associated with key in the subtree rooted at x;
		if (x == null) return null;		 // return null if key not present in subtree rooted at x.
			int cmp = key.compareTo(x.key);
		if (cmp < 0) 
			return get(x.left, key);
		else if (cmp > 0) 
			return get(x.right, key);
		else
			return x.val;
	}
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        if (val == null) {
            //delete(key);
            return;
        }
        root = put(root, key, val);
        //assert check();
    }
	private Node put(Node x, Key key, Value val) {
		// Change keys value to val if key in subtree rooted at x.
		// Otherwise, add new node to subtree associating key with val.
		if (x == null) 
			return new Node(key, val, 1);
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
    public boolean isEmpty() {
        return size() == 0;
    }
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }
    private Node max(Node x) {
        if (x.right == null) return x; 
        else                 return max(x.right); 
    }
    private Node min(Node x) { 
        if (x.left == null) return x; 
        else                return min(x.left); 
    }
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) { 
        if (x == null) return; 
        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key); 
        if (cmplo < 0) keys(x.left, queue, lo, hi); 
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key); 
        if (cmphi > 0) keys(x.right, queue, lo, hi); 
    }
    public Iterable<Key> keys() {
        if (isEmpty())
        	return new Queue<Key>();
        return keys(min(), max());
    }
}