import edu.princeton.cs.algs4.*;

/**
 *              README - Felix Ståhl - Asignment6
 * this program can find if there are any directed cycles in graph.
 * hittade ingen cycel så la till extra hörn i filen
 * AL SKA
 * SKA DRA
 * DRA AL
 * detta skrivs också ut.
 */
public class SymbolDigraph {
    private ST<String, Integer> st;
    private String[] keys;
    private Digraph graph;

    // kontruktor för symboldigraph
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

    //returnerar namnet på hörn v
    public String nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    // returnerar digraph
    public Digraph digraph() {
        return graph;
    }

    private void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    public static void main(String[] args) {
        String filename  = "C:\\Users\\mr_fe\\Desktop\\Algoritmer\\krysstal4\\fråga6\\src\\database.txt";
        String delimiter = " ";

        SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
        Digraph graph = sg.digraph();
        DirectedCycle dc = new DirectedCycle(graph);

        if (dc.hasCycle()) {
            System.out.print("Directed cycle: ");
            for (int v : dc.cycle()) {
                System.out.print(sg.nameOf(v) + " ");
            }
            System.out.println();
        } else {
            System.out.println("No directed cycle");
        }
    }
}