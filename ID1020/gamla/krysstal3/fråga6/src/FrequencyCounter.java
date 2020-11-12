import edu.princeton.cs.algs4.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *                      README - Felix Ståhl - Assignment6
 *
 */
public class FrequencyCounter{
    public static void main(String[] args)throws FileNotFoundException {
        File file = new File("C:\\Users\\mr_fe\\Desktop\\Algoritmer\\krysstal3\\fråga6\\src\\editedversion.txt");
        Scanner scanner = new Scanner(file);
        int size = 50000;
        int minlen = 1;                       // key-length cutoff

        int charCounter = 0;
        SymbolTable<String, List<Integer>> listST = new SymbolTable<>(size);
        int i = 0;
        while (i < size){                           // Build symbol table and count frequencies.
            String word = scanner.next();

            if (!listST.contains(word)) {
                listST.put(word, new ArrayList<>());
                listST.get(word).add(charCounter);
            }
            else {
                listST.get(word).add(charCounter);
            }
            charCounter += word.length() + 1;
            i++;
        }
        scanner = new Scanner(System.in);
        System.out.println("Enter word to get its position in the text: ");
        String inputWord = scanner.nextLine();

        if(!listST.contains(inputWord)){
            System.out.println("Word does not exist in file");
        }
        else{
            System.out.println("The word appears " + listST.get(inputWord).size() + " times in the text. Position: "
                    + listST.get(inputWord).toString());
        }
    }
}