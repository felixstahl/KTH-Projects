package q4;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import q3.SymbolTable;
/**
 *              README
 *       Author: Felix Ståhl
 *       Lab3 - Question 4
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 *
 * This program asks the user for a word to search the file for. It then prints how many times the word is used
 * and where in the file it is positioned, with characters from the start.
 *
 */

public class FrequencyCounter {
    public static void main(String[] args)throws FileNotFoundException {
        File file = new File("C:\\Users\\mr_fe\\Desktop\\Algo\\Algo2\\nya\\lab3\\src\\editedVersion.txt");
        Scanner scanner = new Scanner(file);
        int size = 50000;   // how many words so scan (is needed because that's how my ST is implemented)

        int charCounter = 0;
        SymbolTable<String, List<Integer>> listST = new SymbolTable<>(size);    // build symbol table
        int i = 0;
        while (i < size){
            String word = scanner.next();

            if (!listST.contains(word)) {               // if(ST does not contain word)
                listST.put(word, new ArrayList<>());    // add the word and create a new ArraryList to the ST
                listST.get(word).add(charCounter);      // also add the position of the word in the ArrayList.
            }
            else {
                listST.get(word).add(charCounter);      // if(ST contains word) add it to ST and
            }                                           // the position to the list
            charCounter += word.length() + 1;           // increase the charcounter with each words length + 1.
            i++;
        }
        scanner = new Scanner(System.in);
        System.out.println("Enter word to get its position in the text: "); // ask user for a word
        String inputWord = scanner.nextLine();

        if(!listST.contains(inputWord)){                        // if ST does not contain the word, say so and
            System.out.println("Word does not exist in file");  // end execution of program.
        }
        else{                                                   // if ST contain, print how many times and positions.
            System.out.println("The word appears " + listST.get(inputWord).size() + " times in the text.\nPosition: "
                    + listST.get(inputWord).toString());
        }
    }
}