import java.util.*;
import java.net.*;
import java.io.*;
import controller.Session;

public class ConcHangman{
  public static void main(String[] args) throws IOException{
    try{
      ServerSocket openForBusiness = new ServerSocket((args.length < 1 ? 8888 : Integer.parseInt(args[0])));
      int sessionID = 0;
      Scanner s = new Scanner(new File("C:\\Users\\mr_fe\\Desktop\\Natverksprogrammering\\ID1212\\task1\\hangman\\server\\logic\\words.txt"));
      ArrayList<String> wordList = new ArrayList<String>();
      while (s.hasNext()){
          wordList.add(s.next());
      }
      s.close();

      while(true){
        Socket socket = openForBusiness.accept();
        new Thread(new Session(socket, wordList, sessionID)).start();
        sessionID++;
      }
    }
    catch(Exception e){  System.out.println("Exception catched in main inside ConcHangman.java");  }
  }
}
