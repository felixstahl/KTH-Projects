package q4;
import java.io.*;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 *              README
 *       Author: Felix Ståhl
 *       Extra lab - 4
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 * Implementation of a utility class to, for example, read from files and test classes

 * consumer is an interface which implements so that the operations will be performed on the consumer that is sent to
 * the function.
 *
 */
public class Util {
    public static <U extends Consumer<String>> void readWordsFromFile(File file, U consumer) throws IOException {
        readFromFile(file, (line) -> {
            String[] words = line.split(" ");
            for (String word : words) {     // do the operation for each word in words
                consumer.accept(word);
            }
        });
    }

    private static void readFromFile(File file, Consumer<String> doSomethingWithLine) {
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(" +"); // multiple whitespaces in a row
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            while ((line = reader.readLine()) != null) {
                doSomethingWithLine.accept(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isLetterOrDigit(char character) {
        return Character.isLetter(character) || Character.isDigit(character);
    }

    public static boolean isLetterOrDigit(String string) {
        return string.length() == 1 && isLetterOrDigit(string.charAt(0));
    }

    public static void assertTrue(boolean isTrue, String message) {
        System.out.println(isTrue ? "Pass" : "Fail: " + message);
    }
}