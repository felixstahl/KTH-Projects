package q3;
import princeton.*;
/**
 *              README
 *       Author: Felix Ståhl
 *       Lab4 - Question 3
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 * Implements a Digraph, very similar to Graph.java in Question1. addEdge() instead adds only the edge to the Bag
 * of node1, instead of adding it to both node1 & 2. This will make the graph to behave as directed.
 *
 */

public class Digraph {
    private final int V;        // number of vertices
    private int E;              // number of edges
    private Bag<Integer>[] adj; // all of the adjacent vertices to that specific vertex

    // constructor for digraph
    public Digraph(int V) {
        if (V < 0)
            throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative");
        this.V = V; // number of vertices
        this.E = 0; // number of edges
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    // return number of vertices
    public int V() {
        return V;
    }

    // return number of edges
    public int E() {
        return E;
    }

    // validate if vertex exist
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    //Adds the directed edge v-w to this graph.
    public void addEdge(int v, int w) {
        validateVertex(v);  // validate that the vertices actually belong in the graph
        validateVertex(w);
        adj[v].add(w);      // only adds the edge to w in the Bag of v, this make the graph directed.
        E++;
    }

    // Returns the vertices adjacent to vertex v.
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    // Returns a string to print for this graph.
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges \n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + ": ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}