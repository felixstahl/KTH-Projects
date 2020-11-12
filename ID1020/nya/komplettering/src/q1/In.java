package q1;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 *              README
 *       Author: Felix Ståhl
 *       Lab4 - Question 1
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 *  This class provides methods for reading inputs
 *
 */
public final class In {
    // assume Unicode UTF-8 encoding
    private static final String CHARSET_NAME = "UTF-8";
    // assume language = English, country = US for consistency with System.out.
    private static final Locale LOCALE = Locale.US;
    // initialized in constructors
    private Scanner scanner;

    //Initializes an input stream from a filename
    /*public In(String name) {
        if (name == null) throw new IllegalArgumentException("argument is null");
        try {
            File file = new File(name); // first try to read file from local file system
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), CHARSET_NAME);
                scanner.useLocale(LOCALE);
                return;
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + name, ioe);
        }
    }*/
}
