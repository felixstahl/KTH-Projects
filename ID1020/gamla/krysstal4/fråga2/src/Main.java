import java.io.FileNotFoundException;

/**
 *              README - Felix Ståhl - Assignment2
 *      Using breadth first search to find the shortest path from A to B
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new Graph("C:\\Users\\mr_fe\\Desktop\\Algoritmer\\krysstal4\\fråga2\\src\\database.txt", " ");
        System.out.println(graph);
        BreadthFirstSearch breadth = new BreadthFirstSearch(graph, "FL");
        breadth.print("AL");
    }
}