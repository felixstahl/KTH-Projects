import edu.princeton.cs.algs4.*;

/**
 *          README - Felix Ståhl - Assignment5
 *          schysst readme finns i symboldigraph filen
 */
import java.util.NoSuchElementException;

public class Digraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V; // number of vertices in this digraph
    private int E; // number of edges in this digraph
    private Bag<Integer>[] adj; // adj[v] = adjacency list for vertex v
    private int[] indegree; // indegree[v] = indegree of vertex v

    // kontruktor för digraph
    public Digraph(int V) {
        if (V < 0)
            throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    // returnerar analet hörn
    public int V() {
        return V;
    }

    // returnerar antalet edges
    public int E() {
        return E;
    }

    // validerar om hörnet finns
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    // lägger till ett kant
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        E++;
    }

    // returnes adjacency list for vertex v
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    // en utskrift för digraph
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}