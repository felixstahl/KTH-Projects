package q3;
import java.io.*;
import java.util.Scanner;

/**
 *              README
 *       Author: Felix Ståhl
 *       Extra lab - 3
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 * Implementation of a class that reads a file and gets the k:th or k:th to the k+n:th most common words
 *
 */
public class MostCommonWords {

    private static Map words;               // map for words
    private static String[] sortedArray;    // array with all different words, sorted after frequency

    private static final int BIG_PRIME_NUMBER = 786433;

    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("test")) {
            runTest();
        } else {
            String fileName = "C:\\Users\\mr_fe\\Desktop\\Algo\\Algo2\\nya\\komplettering\\src\\q3\\leipzig1m.txt";
            File file = new File(fileName);
            words = new Map(BIG_PRIME_NUMBER);
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                Scanner scanner = new Scanner(file);
                scanner.useDelimiter(" +"); // + is for multiple spaces after each other
                String line;
                String[] lineWords;
                while ((line = reader.readLine()) != null) {    // read a line, split it into words and remove "."
                    lineWords = line.split(" ");
                    for (String word : lineWords) {
                        words.add(stripOfPunctuation(word));
                    }
                }
                System.out.println("all words have had their punctuation stripped added to map");

                sortedArray = new String[words.size()]; // array of all words, to be sorted after frequency
                int arraySize = 0;

                for (String key : words.keys()) {
                    sortedArray[arraySize++] = key;
                }

                sort(sortedArray);  // quicksort the array

                if (args.length == 1) {
                    int args0 = Integer.valueOf(args[0]);
                    printMostCommonWord(args0);
                } else if (args.length == 2) {
                    int args0 = Integer.valueOf(args[0]);
                    int args1 = Integer.valueOf(args[1]);
                    printMostCommonWords(args0, args1);
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // remove punctuation from word.
    private static String stripOfPunctuation(String word) {
        while (!isLetterOrDigit(word.charAt(word.length() - 1))) {
            word = word.substring(0, word.length() - 1);
        }
        return word;
    }

    // quicksort in ascending order
    private static void sort(String[] list) {   // does not shuffle array as it is already pretty shuffled
        sort(list, 0, list.length - 1);
    }

    // quicksort in ascending order
    private static void sort(String[] list, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(list, lo, hi);    // the element that is in its correct place
        sort(list, lo, j - 1);          // sort low of j
        sort(list, j + 1, hi);          // sort high of j
    }

    // quicksort
    private static int partition(String[] list, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        String v = list[lo];
        while (true) {
            while (words.get(list[++i]) > words.get(v))   // find item in low to swap
                if (i == hi)    // dont go off the right end off array
                    break;

            while (words.get(v) > words.get(list[--j]))    // find item in high to swap
                if (j == lo)    // do go off the left end of array
                    break;

            if (i >= j)  // make sure the pointers doesn't cross
                break;

            swap(list, i, j);
        }

        swap(list, lo, j);

        return j;   // a[lo .. j-1] less / equal a[j] less / equal a[j+1 .. hi]
    }               // basically returns the element that is in its correct place

    // swap places
    private static void swap(String[] list, int i, int j) {
        String x = list[i];
        list[i] = list[j];
        list[j] = x;
    }

    // print most used word and how many occurrences
    public static void printMostCommonWord(int rank) {
        System.out.print("You asked for the " + rank + " which is: '");
        System.out.println(sortedArray[rank - 1] + "', occurring times: " + words.get(sortedArray[rank - 1]));
    }

    public static void printMostCommonWords(int startRank, int endRank) {
        System.out.println("Most Common Words from " + startRank + " to " + endRank + ":");
        for (int i = startRank - 1; i <= endRank - 1; i++) {
            System.out.println(i + 1 + ": " + sortedArray[i] + ", " + words.get(sortedArray[i]) + " occurrences");
        }
    }

    private static void runTest() {
        testSwap();
        testStripOfPunctuation();
    }

    private static void testSwap() {
        String string1 = "string1",
                string2 = "string2";
        String[] arr = {string1, string2};

        swap(arr, 0, 1);

        assertTrue(arr[0].equals(string2), "2nd item should be in the place of the 1st");
        assertTrue(arr[1].equals(string1), "first item should be in the place of the 2nd");

    }

    private static void testStripOfPunctuation() {
        String before = "string!";
        String expected = "string";
        String actual = stripOfPunctuation(before);

        assertTrue(actual.equals(expected), "! should be stripped off the string");

        before = "string'.,'";
        expected = "string";
        actual = stripOfPunctuation(before);

        assertTrue(actual.equals(expected), "multiple punctuation marks should all be stripped off the string");
    }

    // helper methods. no need for own util class
    public static boolean isLetterOrDigit(char character) {
        if(Character.isLetter(character) || Character.isDigit(character)){
            return Character.isLetter(character) || Character.isDigit(character);
        }
        return true;
    }
    public static void assertTrue(boolean isTrue, String message) {
        System.out.println(isTrue ? "Pass" : "Fail: " + message);
    }
}