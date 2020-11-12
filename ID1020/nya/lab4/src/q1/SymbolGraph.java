package q1;
import princeton.*;
/**
 *              README
 *       Author: Felix Ståhl
 *       Lab4 - Question 1
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 * Implementation of a Symbol Graph (representing an undirected graph) for mapping the input Strings to indexes for ease of iteration through graph.
 *
 */

public class SymbolGraph {
    private ST<String, Integer> st;  // string -> index
    private String[] keys;           // index  -> string
    private Graph graph;             // the underlying graph

    // Initializes a graph from a file using the delimiter. Each line in the file contains
    // the name of node_1, followed by the name of node_2 adjacent to node_1, separated by the delimiter.
    public SymbolGraph(String filename, String delimiter) {
        st = new ST<>();    // <String, Integer>
        In in = new In(filename);   // the following part helps getting an int index associated with the String name
        while (!in.isEmpty()) {                         // while(not empty)...
            String[] a = in.readLine().split(delimiter);    // ...read a line, split with delimiter
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])) {       // if(ST does not contain) add the string to the ST with the...
                    st.put(a[i], st.size());    // ...value being the size of the ST at the moment,..
                }                               // ...first 0 then increase over time as it finds unique nodes.
            }
        }

        // this part makes it easier to be able to ask for both name(String) & index(Int) in the future.
        keys = new String[st.size()];
        for (String name : st.keys()) {     // create an array the size of the ST
            keys[st.get(name)] = name;      // place each nodes name on the index that ...
        }                                   //... is the same as the value of the name

        // iterate through file again and add edges to the graph (by adding adjacent nodes to the Bag impl by Graph).
        graph = new Graph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);    // read the nodes from the file again
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {    // add the edges between the nodes in the file.
                int w = st.get(a[i]);
                graph.addEdge(v, w);
            }
        }
    }

    // checks if graph contains the vertex named s
    public boolean contains(String s) {
        return st.contains(s);
    }

    // Returns the integer associated with the vertex named s.
    public int indexOf(String s) {
        return st.get(s);
    }

    // Returns the name of the vertex associated with the integer v.
    public String nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    // Returns the graph associated with the symbol graph.
    public Graph graph() {
        return graph;
    }

    // throw an IllegalArgumentException unless 0 <= v < V. make sure the vertex actually belong in the graph
    private void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public static void main(String[] args) {
        String filename  = "C:\\Users\\mr_fe\\Desktop\\Algo\\Algo2\\nya\\lab4\\src\\inputData.txt";
        String delimiter = " ";
        SymbolGraph sg = new SymbolGraph(filename, delimiter); //a symbol graph that 'converts' the string vertices
        //Graph graph = sg.graph();   // get the graph as Symbol graph points to.

        int source = sg.indexOf("FL");      // beginner node
        int destination = sg.indexOf("OH"); // destination node
        DepthFirstSearch dfs = new DepthFirstSearch(sg.graph(), source); // finds a path from source to all the other...
                                                                    //... vertexes in graph.
        if (dfs.hasPathTo(destination)) { // This code checks if there is a path from chosen source to destination.
            for (int x : dfs.pathTo(destination)){  // the following iterates through and prints each node in path
                if (x == source)
                    System.out.print(" " + sg.nameOf(x));
                else
                    System.out.print(" " + sg.nameOf(x));
            }
            System.out.println();
        } else{                             // if nodes are not connected, say so
            System.out.println(sg.nameOf(source) + " to " + sg.nameOf(destination) + "not connected\n");
        }
    }
}