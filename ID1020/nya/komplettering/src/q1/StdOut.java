package q1;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 *              README
 *       Author: Felix Ståhl
 *       Lab4 - Question 1
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 *  This class provides methods for writing output
 *
 */
public final class StdOut {
    private static final String CHARSET_NAME = "UTF-8"; // force Unicode UTF-8 encoding; otherwise it's system dependent
    private static PrintWriter out; // send output here

    static {    // this is called before invoking any methods
        try {
            out = new PrintWriter(new OutputStreamWriter(System.out, CHARSET_NAME), true);
        }
        catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
    }
    // don't instantiate
    private StdOut() { }

    // Prints an object to this output stream and then terminates the line.
    public static void println(Object x) {
        out.println(x);
    }
}