import edu.princeton.cs.algs4.*;

/**
 *              README - Felix Ståhl - Assignment1
 */
public class SymbolGraph {
    private ST<String, Integer> st;  // string -> index
    private String[] keys;           // index  -> string
    private Graph graph;             // the underlying graph

    /**
     * Initializes a graph from a file using the delimiter.
     * Each line in the file contains the name of a vertex, followed by a list of the names
     * of the vertices adjacent to that vertex, separated by the delimiter.
     */
    public SymbolGraph(String filename, String delimiter) {
        st = new ST<String, Integer>();

        In in = new In(filename); //First pass builds the index by reading strings to associate distinct strings with an index
        while (!in.isEmpty()) {
            String[] a = in.readLine().split(delimiter);
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i]))
                    st.put(a[i], st.size());
            }
        }

        // inverted index to get string keys in an array
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        // second pass builds the graph by connecting first vertex on each line to all others
        graph = new Graph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
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
    @Deprecated
    public int index(String s) {
        return st.get(s);
    }

    // Returns the integer associated with the vertex named s.
    public int indexOf(String s) {
        return st.get(s);
    }

    // Returns the name of the vertex associated with the integer v.
    @Deprecated
    public String name(int v) {
        validateVertex(v);
        return keys[v];
    }

    // Returns the name of the vertex associated with the integer v.
    public String nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    // Returns the graph associated with the symbol graph. It is the client's responsibility not to mutate the graph.
    @Deprecated
    public Graph G() {
        return graph;
    }

    // Returns the graph associated with the symbol graph. It is the client's responsibility not to mutate the graph.
    public Graph graph() {
        return graph;
    }

    // throw an IllegalArgumentException unless 0 <= v < V
    private void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public static void main(String[] args) {
        String filename  = "C:\\Users\\mr_fe\\Desktop\\Algoritmer\\krysstal4\\fråga1\\src\\database.txt";
        String delimiter = " ";
        SymbolGraph sg = new SymbolGraph(filename, delimiter); // creates a symbolgraph that 'converts' the string verices
        Graph graph = sg.graph();   // to integers vertices

        int source = sg.indexOf("FL");  // beginner node
        int destination = sg.indexOf("AL"); // destination node
        DepthFirstSearch dfs = new DepthFirstSearch(graph, source); // finds a path from source to destination
        if (dfs.hasPathTo(destination)) {
            for (int x : dfs.pathTo(destination)){
                if (x == source)
                    System.out.println(" " + sg.nameOf(x));
                else
                    System.out.println(" " + sg.nameOf(x));
            }
            System.out.println();
        } else{
            System.out.println(sg.nameOf(source) + " to " + sg.nameOf(destination) + "not connected\n");
        }
    }
}