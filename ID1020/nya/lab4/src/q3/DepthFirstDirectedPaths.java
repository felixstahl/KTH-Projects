package q3;
import princeton.*;
/**
 *              README
 *       Author: Felix Ståhl
 *       Lab4 - Question 3
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 * Implements a Depth-first Search
 *
 */

public class DepthFirstDirectedPaths {
    private boolean[] marked;    // marked[v] = is there a path between s & v ?
    private int[] edgeTo;        // edgeTo[v] = last edge on path between s & v
    private final int source;    // source vertex

    // Computes a path between s and every other vertex in graph G. (constructor)
    public DepthFirstDirectedPaths(Digraph graph, int s) {
        this.source = s;
        edgeTo = new int[graph.V()];
        marked = new boolean[graph.V()];
        validateVertex(s);
        dfs(graph, s);
    }

    // depth first search from v.   (recursive)
    private void dfs(Digraph graph, int v) {
        marked[v] = true;               // mark v, its visited.
        for (int w : graph.adj(v)) {    // for every adjacent node...
            if (!marked[w]) {           // check if(not marked),..
                dfs(graph, w);          // recursive call to dfs from w as source this time...
                edgeTo[w] = v;        // set edge to v because we got to w(next node) from v(this iterations source)...
            }
        }
    }

    // Is there a path between the source vertex s and vertex v?
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    // Returns a path between the source vertex s and vertex v
    // only used in SymbolGraph when printing path
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<>();   // use stack to keep track and have it in reverse order.
        for (int x = v; x != source; x = edgeTo[x])   // walk through edgeTo array and keep pushing onto the stack...
            path.push(x);                             //... (this is done backwards from destination to source)
        path.push(source);  // end with pushing itself onto stack
        return path;        // return stack
    }

    // is the vertex actually in the graph? (throw an IllegalArgumentException unless 0 <= v < V)
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
}