import edu.princeton.cs.algs4.*;

/**
 *              README - Felix Ståhl - Assignment5
 * En directed graph som kan hitta en väg från A till B(om det finns en väg)
 */
public class SymbolDigraph {
    private ST<String, Integer> st;
    private String[] keys;
    private Digraph graph;

    // Kontruktor för Symboldigraph
    public SymbolDigraph(String filename, String delimiter) {
        st = new ST<String, Integer>();
        In in = new In(filename);
        while (!in.isEmpty()) {
            String[] a = in.readLine().split(delimiter);
            String v = a[0];
            String w = a[1];
            if (!st.contains(v))
                st.put(v, st.size());
            if (!st.contains(w))
                st.put(w, st.size());
        }

        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        graph = new Digraph(st.size());
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

    // returnerar indexet på hörn s
    public int indexOf(String s) {
        return st.get(s);
    }

    // returnerar namnet på hörn v
    public String nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    // returnerar digraph
    public Digraph digraph() {
        return graph;
    }

    // validerar hörn v
    private void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    // main funktion som skapar symboldigraph, en digraph och kollar vägen från A till B
    public static void main(String[] args) {
        String filename = "C:\\Users\\mr_fe\\Desktop\\Algoritmer\\krysstal4\\fråga5\\src\\database.txt";
        String delimiter = " ";

        SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
        Digraph graph = sg.digraph();

        int start = sg.indexOf("AL");
        int destination = sg.indexOf("FL");
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
        } else {
            System.out.println(sg.nameOf(start) + " to " + sg.nameOf(destination) + "not connected\n");
        }
    }
}