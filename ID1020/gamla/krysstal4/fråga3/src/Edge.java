import edu.princeton.cs.algs4.*;
import java.util.NoSuchElementException;

/**
 *              README - Felix Ståhl - Assignment3
 *
 */
public class Edge {
    int weight;
    String s;
    String t;

    public Edge(String v, String w, int weight){
        s = v;
        t = w;
        this.weight = weight;
    }

    public Edge(String v, String w){
        s = v;
        t = w;
    }

    public String getS(){
        return s;
    }

    public String getT(){
        return t;
    }

    public boolean equals(Edge e){  // kollar om e är lika med någon av nodera i edge
        if((s.equals(e.getS()) && t.equals(e.getT())) || (s.equals(e.getT()) && t.equals(e.getS()))){
            return true;
        }
        return false;
    }

    public int getWeight(){     // returnerar vikten
        return weight;
    }
}
