import edu.princeton.cs.algs4.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *                      README - Felix Ståhl - Assignment5
 * BinarySearchTree in nanoseconds = 1213574906277587 - This one is a little slower than SymbolTable(300 words)
 * SymbolTable in nanoseconds = 1213457268184114 - This is faster than BinarySearchTree(300 words)
 * time in seconds = 0.063
 * time in seconds = 0.085
 */
public class FrequencyCounter{
    public static void main(String[] args)throws FileNotFoundException {
        File file = new File("C:\\Users\\mr_fe\\Desktop\\Algo\\Algo2\\gamla\\krysstal3\\fråga5\\src\\editedversion.txt");
        Scanner scanner = new Scanner(file);
        int size = 100000;
        int minlen = 1;                       // key-length cutoff
        int uniqueHashes = 0;
        int uniqueWords = 0;
        SymbolTable<String, Integer> st = new SymbolTable<>(size);
        SymbolTable<Integer, Integer> sthashes = new SymbolTable<>(size);
        int i = 0;
        while (i < size){                                    // Build symbol table and count frequencies.
            String word = scanner.next();
            int key = word.hashCode();
            if (word.length() < minlen) continue;            // Ignore short keys.
            // amount of words
            if (!st.contains(word)) {
                st.put(word, 1);
                uniqueWords++;
            }
            else {
                st.put(word, st.get(word) + 1);
            }
            // all the hashes
            if (!sthashes.contains(key)) {
                sthashes.put(key, 1);
                uniqueHashes++;
            }
            else {
                sthashes.put(key, sthashes.get(key) + 1);
            }
            i++;
        }
        System.out.println("Hash ratio: " + ((double)uniqueWords/uniqueHashes));
    }
}