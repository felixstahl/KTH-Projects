package receiver;

import java.net.*;
import java.io.*;
import java.util.*;

public class Receiver implements Runnable{
  Socket socket;
  DataInputStream inFromServer;

  public Receiver(Socket socket){
    try{
      this.inFromServer = new DataInputStream(socket.getInputStream());
      this.socket = socket;
    }catch(Exception e){  System.out.println("Exception catched in constructor inside Receiver.java");  }
  }

  public void run(){
    try{
      if(inFromServer.readInt() == 0){
        System.out.println("Welcome, start guessing!");
        System.out.println(inFromServer.readUTF());
      }
      while(!socket.isClosed()){
        switch(inFromServer.readInt()){
          case 10:{                 // game lost
            System.out.println("You have had too many incorrect attempts, you lost. ");
            System.out.println("The word was: " + inFromServer.readUTF());
            System.out.println("Your score right now is: " + inFromServer.readInt());
            System.out.println("Do you want to play again?, keep guessing or quit. here is the word: ");
            System.out.println(inFromServer.readUTF());
            break;

          }
          case 11:{                 //  game won
            System.out.println("You Won!");
            System.out.println("Your score right now is: " + inFromServer.readInt());
            System.out.println("Do you want to play again?, keep guessing or quit. here is the word: ");
            System.out.println(inFromServer.readUTF());
            break;
          }
          case 12:{                 //  right guess
            System.out.println("The guess was correct. Keep this up, and guess again! ");
            System.out.println("Tries left is: " + inFromServer.readInt());
            System.out.println(inFromServer.readUTF());
            break;
          }
          case 13:{                 //  wrong guess
            System.out.println("The guess was incorrect.");
            System.out.println("You have " + inFromServer.readInt() + " attempts left, try again.. ");
            System.out.println(inFromServer.readUTF());
            break;
          }
        }
      }
    }catch(Exception e){  System.out.println("Exception catched in run() inside Receiver.java"); }
  }
}
