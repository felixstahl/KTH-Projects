package q2;
/**
 *              README
 *       Author: Felix Ståhl
 *       Lab4 - Question 2
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 * Using Breadth-first Search to find path from source to destination.
 *
 */

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph("C:\\Users\\mr_fe\\Desktop\\Algo\\Algo2\\nya\\lab4\\src\\inputData.txt", " ");
        BreadthFirstSearch breadth = new BreadthFirstSearch(graph, "FL");   // build a graph from given source
        breadth.print("NV");    // prints the path from source to given destination
    }
}