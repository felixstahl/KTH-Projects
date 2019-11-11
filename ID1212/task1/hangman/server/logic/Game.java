package logic;

import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.*;

public class Game{
  ArrayList<String> wordList;
  String correctWord;
  char[] guessedWord;
  int tries;

  public Game(ArrayList<String> wordList){
    this.wordList = wordList;
    correctWord = chooseWord();
    tries = correctWord.length() - 1;
    guessedWord = new char[correctWord.length()];
    Arrays.fill(guessedWord, '_');
  }

  public int guess(String clientInput){
    int state = 3;

    if(clientInput.length() == 1){
      //System.out.println("längd 1 och i if sats");
      for(int i = 0; i < correctWord.length(); i++){
        if(clientInput.charAt(0) == correctWord.charAt(i)){
          state = 2;
          //System.out.println("fucking bajs");
          //System.out.println(state);
          guessedWord[i] = clientInput.charAt(0);
        }
      }
      if(String.copyValueOf(guessedWord).equalsIgnoreCase(correctWord)){
        state = 1;
      //System.out.println(state);
      }
      if(state == 3){
        tries--;
        //System.out.println(state);
        if(tries <= 0){
          state = 0;
//System.out.println(state);
         }
      }
    }
    else{
      if(clientInput.equalsIgnoreCase(correctWord)){ state = 1; }
      else{
        tries--;
        if(tries <= 0){ state = 0; }
      }
    }
    return state;
  }

  public String getWord(){    // if guessed a letter right, this will return the word
    return String.copyValueOf(guessedWord);
  }
  public String lostWord(){   //  if game is lost, this will return the word
    return correctWord;
  }
  public int getTries(){   //  if game is lost, this will return the word
    return tries;
  }
  private String chooseWord(){
    Random randomInt = new Random();
    String strWord = wordList.get(randomInt.nextInt(wordList.size()));
    return strWord.toUpperCase();
  }
}
