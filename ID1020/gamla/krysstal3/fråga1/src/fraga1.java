import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 *                    README - Felix Ståhl - Assignment1
 * This program cleans the .txt file from everything that is not a letter, space, or new line.
*/
public class fraga1 {
    public static void filter() throws FileNotFoundException {
        File file = new File("C:\\Users\\mr_fe\\Desktop\\Algoritmer\\krysstal3\\fråga1\\src\\franNatet.txt");
        PrintWriter writer = new PrintWriter("C:\\Users\\mr_fe\\Desktop\\Algoritmer" +
                                                "\\krysstal3\\fråga1\\src\\editedversion.txt");
        Scanner scanner = new Scanner(file);    // a new scanner, used to read the .txt, line by line
        String line;
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            for(int i = 0; i < line.length(); i++){
                char c = line.charAt(i);        // read each char in the string, replace with space if not a
                if((c!=' ') && (c!='\n') && !(Character.isLetter(c))){
                    char[] lineChars = line.toCharArray();
                    lineChars[i] = ' ';
                    line = String.valueOf(lineChars);
                }
            }
            writer.println(line);
        }
        writer.close();
    }
    public static void main(String[] args){
        try {
            filter();
        }
        catch (FileNotFoundException ex) {
            System.out.println("file not found");
        }
    }
}
