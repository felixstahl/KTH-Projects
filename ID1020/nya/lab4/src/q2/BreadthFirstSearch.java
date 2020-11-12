package q2;
import princeton.*;
/**
 *              README
 *       Author: Felix Ståhl
 *       Lab4 - Question 2
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 * Implementation of Breadth-first Search using ordered STs and Queue data structures
 *
 */

public class BreadthFirstSearch {
    private ST<String, String>  edgeTo = new ST<>();   // edgeTo[v] = previous vertex on path from s to v
    private ST<String, Integer> distTo = new ST<>();   // distTo[v] = length of path from s to v

    // run BFS in graph G from given source vertex s
    public BreadthFirstSearch(Graph G, String s) {

        // put source on the queue
        Queue<String> queue = new Queue<>();    // create a queue
        queue.enqueue(s);                       // add source to queue
        distTo.put(s, 0);                   // add its distance

        // repeated remove next vertex v from queue and insert
        // all its adjacent, provided they haven't yet been visited
        while (!queue.isEmpty()) {              // as long as the queue is not empty, iterate.
            String v = queue.dequeue();         // remove all visited nodes. (1 per iteration)
            for (String w : G.adjacentTo(v)) {  // for each node adjacent to v...
                if (!distTo.contains(w)) {      // if(does not contain node)
                    queue.enqueue(w);           // add all unvisited nodes to queue
                    distTo.put(w, 1 + distTo.get(v));   // add the distance to node w (last node, which is v, +1).
                    edgeTo.put(w, v);                       // add an edge from current node to the previous one
                }
            }     // side note: This program behaves much like depth first search, because it first finds all the paths
        }         // and those paths are later used when finding the way from source to destination.
    }

    // check if v is reachable from the source
    public boolean hasPathTo(String v) {
        return distTo.contains(v);
    }

    // return the length of the path from v to s as int
    public int distanceTo(String v) {
        if (!hasPathTo(v))
            return Integer.MAX_VALUE;
        return distTo.get(v);
    }

    // return the path from v to s as an Iterable
    public Iterable<String> pathTo(String v) {
        Stack<String> path = new Stack<>();
        while (v != null && distTo.contains(v)) {
            path.push(v);
            v = edgeTo.get(v);
        }
        return path;
    }

    // prints the path and the distance to destination t
    public void print(String t){
        for (String v : pathTo(t)) {
            System.out.print(" " + v);
        }
        System.out.println("\n distance " + distanceTo(t));
    }
}
