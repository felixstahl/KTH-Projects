package q3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
/**
 *              README
 *       Author: Felix Ståhl
 *       Lab3 - Question 3
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 *
 * The hash ratio I get from dividing the distinct hashes with distinct words
 * is 0.9998169671456026. Which means that there is a almost one hash per word.
 *
 * This is pretty good, since 1.0 is perfect distribution.
 * (this is tested on 141 000 scanned words)
 *
 */

public class FrequencyCounter{
    public static void main(String[] args)throws FileNotFoundException {
        File file = new File("C:\\Users\\mr_fe\\Desktop\\Algo\\Algo2\\nya\\lab3\\src\\editedVersion.txt");
        Scanner scanner = new Scanner(file);

        int size = 141000;  // how many words to scan. 141 000 is the largest number that can be scanned.
        int minlen = 1;     // key-length cutoff
        int distinctHash = 0;
        int distinctWords = 0;

        SymbolTable<String, Integer> st = new SymbolTable<>(size);          // <word, number of times occurred>
        SymbolTable<Integer, Integer> sthashes = new SymbolTable<>(size);   // <hashcode, value>

        int i = 0;
        while (i < size){                           // Build symbol table and count frequencies.
            String word = scanner.next();
            int key = word.hashCode();
            if (word.length() < minlen) continue;   // Ignore short keys.

            if (!st.contains(word)) {   // if(does not contain)
                st.put(word, 1);        // add new word to st
                distinctWords++;
            }
            else {
                st.put(word, st.get(word) + 1); // increment old value by 1
            }

            if (!sthashes.contains(key)) {  // if(does not contain)
                sthashes.put(key, 1);       // add new <hashcode, number of times occurred>
                distinctHash++;
            }
            else {
                sthashes.put(key, sthashes.get(key) + 1);   // increment old value by 1
            }
            i++;
        }
        System.out.println("distinct hashes: " + distinctHash);
        System.out.println("distinct words: " + distinctWords + "\n");

        double test = (double)(distinctHash) / distinctWords;   // cast to double for decimals
        System.out.println("Hash ratio: " + test);              // how distributed the hashes are
        System.out.printf("Hash ratio: %.20f", test);           // more decimals

        // distinct hashes: 10925
        // distinct words: 10927
        // outcome is 0.9998169671456026, which means that there is almost one hash per word.
    }
}