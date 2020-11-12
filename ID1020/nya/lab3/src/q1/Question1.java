package q1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 *              README
 *       Author: Felix Ståhl
 *       Lab3 - Question 1
 *
 * A filter that cleans the inputFile.txt from any non-alphabetical characters.
 * It copies any letter and space to a file called editedVersion.txt.
 * This editedVersion.txt is also used as text in the coming questions.
 *
 */

public class Question1 {
    public static void filter() throws FileNotFoundException {
        File file = new File("C:\\Users\\mr_fe\\Desktop\\Algo\\Algo2\\nya\\lab3\\src\\inputFile.txt");
        PrintWriter writer = new PrintWriter("C:\\Users\\mr_fe\\Desktop\\Algo\\Algo2\\nya\\lab3\\src\\editedVersion.txt");
        Scanner scanner = new Scanner(file);    // a new scanner, used to read the .txt, line by line

        String line;
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            for(int i = 0; i < line.length(); i++){     // read each char in the string, replace with space if not a
                char c = line.charAt(i);
                if((c != ' ') && (c != '\n') && !(Character.isLetter(c))){  // if a line contains a non-alphabetical...
                    char[] lineChars = line.toCharArray();                  // add the line to the array and...
                    lineChars[i] = ' ';                                     // switch the character to a space.
                    line = String.valueOf(lineChars);                       // The line variable takes the char array...
                }
            }
            writer.println(line);                                 // ...and prints to the editedVersion file.
        }
        writer.close();         // close the writer.
    }

    public static void main(String[] args){
        try {
            filter();
            System.out.println("Done.");        // switches all non-alphabetical characters from the file with
        }                                       // a space and writes the content to editedVersion.txt
        catch (FileNotFoundException ex) {
            System.out.println("file not found");
        }
    }
}
