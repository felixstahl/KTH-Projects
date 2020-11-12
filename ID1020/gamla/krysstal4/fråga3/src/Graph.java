import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import edu.princeton.cs.algs4.*;

/**
 *              README - Felix Ståhl - Assignment3
 *
 */
public class Graph{
    // symbol table, key = string vertex, value = set of neighboring vertices
    private ST<String, SET<String>> st;
    public ArrayList<Edge> edge;
    int edgeWeight = 1;

    // number of edges
    private int E;

    // Initializes an empty graph with no vertices or edges.
    public Graph() {
        st = new ST<String, SET<String>>();
        edge = new ArrayList<Edge>();
    }

    // Initializes a graph from the specified file, using the specified delimiter.
    public Graph(String filename, String delimiter) {
        st = new ST<String, SET<String>>();
        edge = new ArrayList<Edge>();
        In in = new In(filename);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] names = line.split(delimiter);
            for (int i = 1; i < names.length; i++) {
                addEdge(names[0], names[i]);
            }
        }
    }

    public ArrayList<Edge> getEdges(){
        return edge;
    }

    // Returns the number of vertices in this graph.
    public int V() {
        return st.size();
    }

    // Returns the number of edges in this graph.
    public int E() {
        return E;
    }

    // throw an exception if v is not a vertex
    private void validateVertex(String v) {
        if (!hasVertex(v)) throw new IllegalArgumentException(v + " is not a vertex");
    }

    // Returns the degree of vertex v in this graph.
    public int degree(String v) {
        validateVertex(v);
        return st.get(v).size();
    }

    // Adds the edge v-w to this graph (if it is not already an edge).
    // param  v one vertex in the edge
    // param  w the other vertex in the edge
    public void addEdge(String v, String w) {
        if (!hasVertex(v)) addVertex(v);
        if (!hasVertex(w)) addVertex(w);
        if (!hasEdge(v, w)) {
            E++;
            addWeightedEdge(v, w, edgeWeight);
            edgeWeight++;
        }
        st.get(v).add(w);
        st.get(w).add(v);
    }

    public void addWeightedEdge(String v, String w, int weight){
        Edge weightedEdge = new Edge(v, w, weight);
        edge.add(weightedEdge);
    }

    // Adds vertex v to this graph (if it is not already a vertex).
    public void addVertex(String v) {
        if (!hasVertex(v)) st.put(v, new SET<String>());
    }

    // Returns the vertices in this graph.
    public Iterable<String> vertices() {
        return st.keys();
    }

    // Returns the set of vertices adjacent to v in this graph.
    public Iterable<String> adjacentTo(String v) {
        validateVertex(v);
        return st.get(v);
    }

    // Returns true if v is a vertex in this graph.
    public boolean hasVertex(String v) {
        return st.contains(v);
    }

    // Returns true if v-w is an edge in this graph.
    public boolean hasEdge(String v, String w) {
        validateVertex(v);
        validateVertex(w);
        return st.get(v).contains(w);
    }

    // Returns a string representation of this graph.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (String v : st) {
            s.append(v + ": ");
            for (String w : st.get(v)) {
                s.append(w + " ");
            }
            s.append('\n');
        }
        return s.toString();
    }
}