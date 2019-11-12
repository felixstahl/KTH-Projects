package controller;

import java.util.*;
import java.net.*;
import java.io.*;
import logic.Game;

public class Session implements Runnable{
  Socket socket;
  int sessionID;
  Game game;
  ArrayList<String> wordList;
  DataInputStream inFromClient;
  DataOutputStream outToClient;
  int score = 0;

  public Session(Socket s, ArrayList<String> wordList, int sessID){
		socket = s;
    sessID = sessionID;
    this.wordList = wordList;
  }

  public void run(){
    try{
      this.inFromClient = new DataInputStream(socket.getInputStream());
      this.outToClient = new DataOutputStream((socket.getOutputStream()));
      System.out.println("Server and session up");

      this.game = new Game(wordList);
      outToClient.writeInt(0);
      outToClient.writeUTF(this.game.getWord());

      System.out.println("The game is started, the word is " + this.game.lostWord());

      while(!socket.isClosed() && socket.isConnected()){
        int resultGuess = this.game.guess(inFromClient.readUTF());
    
        System.out.println("The user guess 'code' " + resultGuess);
        switch(resultGuess){
          case 0:{ // lost game
            score--;
            outToClient.writeInt(10);
            outToClient.writeUTF(this.game.lostWord());
            outToClient.writeInt(score);
            this.game = new Game(wordList);    // play again or quit
            outToClient.writeUTF(this.game.getWord());
            System.out.println("The game is started, the word is " + this.game.lostWord());
            break;
          }
          case 1:{ // won game
            score++;
            outToClient.writeInt(11);
            outToClient.writeInt(score);
            this.game = new Game(wordList);    // play again or quit
            outToClient.writeUTF(this.game.getWord());
            System.out.println("The game is started, the word is " + this.game.lostWord());
            break;
          }
          case 2:{ // guessed right letter
            outToClient.writeInt(12);
            outToClient.writeInt(this.game.getTries());
            outToClient.writeUTF(this.game.getWord());
            break;
          }
          case 3:{ // guessed wrong letter
            outToClient.writeInt(13);
            outToClient.writeInt(this.game.getTries());
            outToClient.writeUTF(this.game.getWord());
            break;
          }
        }
      }
      inFromClient.close();
      outToClient.close();
      socket.close();
    }catch(Exception e){
      try{
        System.out.println("Exception catched in run() inside Session.java");
        this.socket.close();
      }catch (Exception socketException){ System.out.println("Can't close socket, something is wrong in Session.java"); }

    }
  }
}
