import edu.princeton.cs.algs4.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *                      README - Felix Ståhl - Assignment4
 * BinarySearchTree in nanoseconds = 1213574906277587 - This one is a little slower than SymbolTable(300 words)
 * SymbolTable in nanoseconds = 1213457268184114 - This is faster than BinarySearchTree(300 words)
 * time in seconds = 0.063 RBST
 * time in seconds = 0.085 BST
 */
public class FrequencyCounter{
    public static void main(String[] args)throws FileNotFoundException {
        File file = new File("C:\\Users\\mr_fe\\Desktop\\Algoritmer\\krysstal3\\fråga4\\src\\editedversion.txt");
        Scanner scanner = new Scanner(file);
        int size = 100000;
        int minlen = 1;                                      // key-length cutoff
        RedBlackBST<String, Integer> st = new RedBlackBST<>();
        int i = 0;
        while (i < size){                                    // Build symbol table and count frequencies.
            String word = scanner.next();
            if (word.length() < minlen) continue;            // Ignore short keys.
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
            i++;
        }            // Find a key with the highest frequency count.
        Stopwatch time = new Stopwatch();
        String max = "";
        st.put(max, 0);
        for (String word : st.keys())
            if (st.get(word) > st.get(max))
                max = word;
        System.out.println(max + " " + st.get(max));
        System.out.print("time in seconds = ");
        System.out.println(time.elapsedTime());
    }
}