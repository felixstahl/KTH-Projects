package q2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *              README
 *       Author: Felix Ståhl
 *       Lab3 - Question 2
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 *
 *    Test result for 50 000 words:
 *             Binary Search Tree in nanoseconds = 19280800, 22125600, 22074200, 19826100, 19233900
 * (binary search) Symbol Table  in nano seconds = 21302500, 25559100, 20765700, 23051800, 17570600
 * Average for Binary Search Tree: 20 508 120 = 0.02050812 sec
 *       Average for Symbol Table: 21 649 940 = 0.02164994 sec
 *
 * The above times are just the ones i wrote down for 5 consecutive runs. I have probably run this 50 times during
 * implementation and it seems like these times can differ a lot from time to time. It does not seem to follow any
 * pattern except that the average time in nanoseconds is a LITTLE less for Binary Search Tree...
 *
 *    Test result for 200 words:
 *             Binary Search Tree in nanoseconds = 12532200, 13399300, 12930400, 12725900, 12712500
 * (binary search) Symbol Table  in nano seconds = 12634500, 13758900, 12766000, 13714500, 13112600
 * Average for Binary Search Tree: 12 860 060 = 0.01286006 sec
 *       Average for Symbol Table: 13 197 300 = 0.0131973 sec
 *
 * Furthermore,
 * Time complexity of BST Best case is O(log N) for search, insert and delete since it keeps dividing the number of items
 * it needs to search, in order to find its way, by 2 every time.
 * Worst case is O(n).
 * The binary search Symbol Table is similar, it has O(n) search but instant insertion and deletion (both best and
 * worst case) since it implements the queue data structure.
 *
 * My conclusion,
 * it depends from time to time which ever is fastest for building and finding the largest key/value.
 * It is, however, better to use the ST if you know that it will be a lot of insertion and deletion,
 * and better to use BST if you know you are just gonna do a lot of searching.
 *
 */

public class FrequencyCounter {
    public static void main(String[] args)throws FileNotFoundException {

        File file = new File("C:\\Users\\mr_fe\\Desktop\\Algo\\Algo2\\nya\\lab3\\src\\editedVersion.txt");
        Scanner scanner = new Scanner(file);
        int size = 200;         // (change when needed)
        int minLength = 1;         // key-length cutoff

        SymbolTable<String, Integer> st = new SymbolTable<>(size);          // switch between these two...
        //BinarySearchTree<String, Integer> st = new BinarySearchTree<>();  // ...for different implementation.
        int i = 0;
        while (i < size){                               // Build symbol table and count frequencies.
            String word = scanner.next();
            if (word.length() < minLength) continue;    // Ignore short keys.
            if (!st.contains(word)) st.put(word, 1);    // if(ST does not contain word) add it to ST
            else st.put(word, st.get(word) + 1);        // if(ST contain word) increase number of times it occurred.
            i++;
        }

        long start = System.nanoTime();             // start timer
        String max = "";                            // insert placeholder on first place
        st.put(max, 0);                             // this for loop checks each word if its bigger than the one before
        for (String word : st.keys())               // and return the one used the most.
            if (st.get(word) > st.get(max))
                max = word;
        System.out.println(max + " " + st.get(max));
        long stop = System.nanoTime();              // stop timer

        System.out.println("Elapsed time in nano seconds: " + (stop - start));  // print elapsed time
    }
}