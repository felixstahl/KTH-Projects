package q3;
import princeton.*;
/**
 *              README
 *       Author: Felix Ståhl
 *       Lab4 - Question 3
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 * This program implements a DFS on a Directed Graph. It works pretty much the same as Question1, since it is still
 * DFS. The only difference is how the graph is built. This time it implements the Digraph.java
 * (in Question1 the Graph.java was used)
 *
 */

public class SymbolDigraph {
    private ST<String, Integer> st;  // string -> index
    private String[] keys;           // index  -> string
    private Digraph digraph;        // the underlying graph

    // Initializes a graph from a file using the delimiter. Each line in the file contains the name of node_1,
    // followed by the name of node_2 adjacent to node_1, separated by the delimiter.
    public SymbolDigraph(String filename, String delimiter) {
        st = new ST<>();            // <String, Integer>
        In in = new In(filename);   // the following part helps getting an int index associated with the String name

        while (!in.isEmpty()) {                             // while(not empty)...
            String[] a = in.readLine().split(delimiter);    // ...read a line, split with delimiter
            String v = a[0];            // v is node1 on index 0
            String w = a[1];            // w is node2 on index 1, (v is directed to w).
            if (!st.contains(v))        // if(ST does not contain) add the string to the ST with the...
                st.put(v, st.size());   // ...value being the size of the ST at the moment,..
            if (!st.contains(w))        // ...first 0 then increase over time as it finds unique nodes.
                st.put(w, st.size());
        }

        // this part makes it easier to be able to ask for both name(String) & index(Int) in the future.
        keys = new String[st.size()];
        for (String name : st.keys()) {     // create an array the size of the ST
            keys[st.get(name)] = name;      // place each nodes name on the index that ...
        }                                   //... is the same as the value of the name

        // iterate through file again and add edges to the graph (by adding adjacent nodes to the Bag impl by Graph).
        digraph = new Digraph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);    // read the nodes from the file again
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {    // add the edges between the nodes in the file.
                int w = st.get(a[i]);
                digraph.addEdge(v, w);
            }
        }
    }

    // returns index of node s
    public int indexOf(String s) {
        return st.get(s);
    }

    // returns name(string) of node v
    public String nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    // returns digraph
    public Digraph digraph() {
        return digraph;
    }

    // validates node v
    private void validateVertex(int v) {
        int V = digraph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    // main function creates a symbol digraph and checks way from source to destination
    public static void main(String[] args) {
        String filename  = "C:\\Users\\mr_fe\\Desktop\\Algo\\Algo2\\nya\\lab4\\src\\inputData.txt";
        String delimiter = " ";

        SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
        Digraph graph = sg.digraph();

        int start = sg.indexOf("AL");           // AL to NC will be "AL-GA-NC" (they are connected)
        int destination = sg.indexOf("NC");     // FL to AL will be "FL to AL is not connected"
        DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(graph, start);

        if (dfs.hasPathTo(destination)) {
            for (int x : dfs.pathTo(destination)) {
                if (x == start) {
                    System.out.print(sg.nameOf(x));
                } else {
                    System.out.print("-" + sg.nameOf(x));
                }
            }
            System.out.println();
        } else {                   // when the 2 nodes are not connected in any way in the directed graph
            System.out.println(sg.nameOf(start) + " to " + sg.nameOf(destination) + " is not connected\n");
        }
    }
}