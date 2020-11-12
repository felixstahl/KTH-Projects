import edu.princeton.cs.algs4.*;

/**
 *              README - Felix Ståhl - Assignment1
 *  Denna uppgift var krånglig och jobbig minst sagt. eftersom man spenderade flera dagar på
 *  att få ut den kortaste vägen. skulle vilja ha en main fil som bara gör anrop.
 *  EDIT: Tror alla lösningar jag gjorde denna labb blev liknande denna, huller om buller mellan filer,
 *  så blir det med en allt för tight tidspress...
 *
 *  Ett program som kan hitta en path till en nod.
 */
public class DepthFirstSearch {
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int[] edgeTo;        // edgeTo[v] = last edge on s-v path
    private final int s;         // source vertex

    // Computes a path between s and every other vertex in graph G.
    public DepthFirstSearch(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }

    // depth first search from v
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    // Is there a path between the source vertex s and vertex v?
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    // Returns a path between the source vertex s and vertex v
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    // throw an IllegalArgumentException unless 0 <= v < V
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}