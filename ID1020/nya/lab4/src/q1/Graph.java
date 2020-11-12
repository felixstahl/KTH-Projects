package q1;
import java.util.NoSuchElementException;
import princeton.*;
/**
 *              README
 *       Author: Felix Ståhl
 *       Lab4 - Question 1
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 * Implementation of a Graph, using Bag data structure from lab1.
 *
 */

public class Graph {
    private final int V;        // number of vertices
    private int E;              // number of edges
    private Bag<Integer>[] adj; // all of the adjacent vertices to that specific vertex

    // Initializes an empty graph with V vertices and 0 edges. param V the number of vertices
    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
        this.V = V; // number of vertices
        this.E = 0; // number of edges
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    // Initializes a graph from the specified input stream.
    public Graph(In in) {
        try {
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Graph must be non-negative");
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();    // create an array of bags that include each edge to each vertex.
            }
            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("number of edges in a Graph must be non-negative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                validateVertex(v);
                validateVertex(w);
                addEdge(v, w);
            }
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }

    // Returns the number of vertices in this graph.
    public int V() {
        return V;
    }

    // Returns the number of edges in this graph.
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    //Adds the undirected edge v-w to this graph.
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].add(w);  // add w to the Bag on array index v
        adj[w].add(v);  // add v to the Bag on array index w
    }

    // Returns the vertices adjacent to vertex v.
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    // Returns a string to print for this graph.
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
