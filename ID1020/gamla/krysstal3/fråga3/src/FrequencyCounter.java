import edu.princeton.cs.algs4.StdIn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *                      README - Felix Ståhl - Assignment3
 * SymbolTable in nanoseconds = 1213457268184114 - This is faster than BinarySearchTree(300 words)
 */
public class FrequencyCounter{
    public static void main(String[] args)throws FileNotFoundException {
        File file = new File("C:\\Users\\mr_fe\\Desktop\\Algoritmer\\krysstal3\\fråga3\\src\\editedversion.txt");
        Scanner scanner = new Scanner(file);
        int size = 50000;
        int minlen = 1;                                      // key-length cutoff
        SymbolTable<String, Integer> st = new SymbolTable<>(size);
        int i = 0;
        while (i < size){                                    // Build symbol table and count frequencies.
            String word = scanner.next();
            word = word.toLowerCase();
            if (word.length() < minlen) continue;            // Ignore short keys.
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
            i++;
        }            // Find a key with the highest frequency count.
        String max = "";
        st.put(max, 0);
        for (String word : st.keys())
            if (st.get(word) > st.get(max))
                max = word;
        System.out.println(max + " " + st.get(max));

        // assignment 3
        System.out.println("Enter n:");
        int n = StdIn.readInt();
        System.out.println("Enter x:");
        int x = StdIn.readInt();
        st.freqFromNToX(st, n, x);
    }
}