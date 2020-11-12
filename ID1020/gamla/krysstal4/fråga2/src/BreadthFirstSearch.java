import edu.princeton.cs.algs4.*;

/**
 *              README - Felix Ståhl - Assignment2
 *  finns i main filen
 */
public class BreadthFirstSearch {
    private ST<String, String>  prev = new ST<String, String>();    // prev[v] = previous vertex on shortest path from s to v
    private ST<String, Integer> dist = new ST<String, Integer>();   // dist[v] = length of shortest path from s to v

    // run BFS in graph G from given source vertex s
    public BreadthFirstSearch(Graph G, String s) {

        // put source on the queue
        Queue<String> queue = new Queue<String>();
        queue.enqueue(s);
        dist.put(s, 0);

        // repeated remove next vertex v from queue and insert
        // all its neighbors, provided they haven't yet been visited
        while (!queue.isEmpty()) {
            String v = queue.dequeue();
            for (String w : G.adjacentTo(v)) {
                if (!dist.contains(w)) {
                    queue.enqueue(w);
                    dist.put(w, 1 + dist.get(v));
                    prev.put(w, v);
                }
            }
        }
    }

    // check if v is reachable from the source
    public boolean hasPathTo(String v) {
        return dist.contains(v);
    }

    // the length of the shortest path from v to s
    public int distanceTo(String v) {
        if (!hasPathTo(v)) return Integer.MAX_VALUE;
        return dist.get(v);
    }

    // the shortest path from v to s as an Iterable
    public Iterable<String> pathTo(String v) {
        Stack<String> path = new Stack<String>();
        while (v != null && dist.contains(v)) {
            path.push(v);
            v = prev.get(v);
        }
        return path;
    }

    // prints the path
    public void print(String t){
        for(String v : pathTo(t)) {
            StdOut.println("   " + v);
        }
        StdOut.println("distance " + distanceTo(t));
    }
}
