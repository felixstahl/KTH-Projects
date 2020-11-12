import java.io.FileNotFoundException;

/**
 *              README - Felix Ståhl - Assignment3
 *  Denna var väldigt krånglig, gillar inte att ha massa beräkningar i main, brukar inte vara så,
 *  men var tvungen för att komma vidare.
 *
 *  Ett program som tilldelar edges en vikt och hittar kortaste pathen och beräknar totala vikten av den.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new Graph("C:\\Users\\mr_fe\\Desktop\\Algoritmer\\krysstal4\\fråga3\\src\\database.txt", " ");   // create graph
        System.out.println(graph);
        BreadthFirstSearch breadth = new BreadthFirstSearch(graph, "AL");

        int totalWeight = 0;
        String vPrev = "AL";
        for(String v : breadth.pathTo("UT")) {
            if(!vPrev.equals(v)) {
                for(Edge e : graph.getEdges()) {
                    if(e.equals(new Edge(vPrev, v))) {
                        System.out.print(e.getWeight() + " ");
                        totalWeight += e.getWeight();
                        break;
                    }
                }
            }
            System.out.print(v + " ");
            vPrev = v;
        }
        System.out.println("\nTotal Weight: " + totalWeight);
    }
}