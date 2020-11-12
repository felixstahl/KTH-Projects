import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *                      README - Felix Ståhl - Assignment2
 * BinarySearchTree in nanoseconds = 1213574906277587 - This one is a little slower than SymbolTable(300 words)
 * SymbolTable in nanoseconds = 1213457268184114 - This is faster than BinarySearchTree(300 words)
 * time in seconds = 0.133 binarysearchtree (300 words) just change the size for more words from the text
 * time in seconds = 0.137 symboltable
 */
public class FrequencyCounter{
    public static void main(String[] args)throws FileNotFoundException {

        File file = new File("C:\\Users\\mr_fe\\Desktop\\Algoritmer\\krysstal3\\fråga2\\src\\franNatet.txt");
        Scanner scanner = new Scanner(file);
        int size = 300;
        int minlen = 1;                                      // key-length cutoff
        SymbolTable<String, Integer> st = new SymbolTable<>(size);
        int i = 0;
        while (i < size){                               // Build symbol table and count frequencies.
            String word = scanner.next();
            if (word.length() < minlen) continue;            // Ignore short keys.
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
            i++;
        }
        Stopwatch stopwatch = new Stopwatch();          // Find a key with the highest frequency count.
        String max = "";
        st.put(max, 0);
        for (String word : st.keys())
            if (st.get(word) > st.get(max))
                max = word;
        System.out.println(max + " " + st.get(max));
        System.out.print("time in seconds = ");
        System.out.println(stopwatch.elapsedTime());
    }
}