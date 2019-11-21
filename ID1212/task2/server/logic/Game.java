package logic;

import java.util.*;
import java.net.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class Game{
  ArrayList<String> wordList;
  String correctWord = "";
  char[] guessedWord;
  int tries = 0;
  int score = 0;

  public Game() throws IOException{
    Scanner s = new Scanner(new File("C:\\Users\\mr_fe\\Desktop\\Natverksprogrammering\\ID1212\\task2\\server\\logic\\words.txt"));
    this.wordList = new ArrayList<String>();
    while (s.hasNext()){
        this.wordList.add(s.next());
    }
    s.close();

    correctWord = chooseWord();
    tries = correctWord.length() - 1;
    guessedWord = new char[correctWord.length()];
    Arrays.fill(guessedWord, '_');
  }

  public int guess(String clientInput){
    int state = 3;

    if(clientInput.length() == 1){
      for(int i = 0; i < correctWord.length(); i++){
        if(clientInput.charAt(0) == correctWord.charAt(i)){
          state = 2;
          guessedWord[i] = clientInput.charAt(0);
        }
      }
      if(String.copyValueOf(guessedWord).equalsIgnoreCase(correctWord)){
        state = 1;
        score = score + 1;
      }
      if(state == 3){
        tries--;
        if(tries <= 0){
          state = 0;
          score = score - 1;
        }
      }
    }
    else{
      if(clientInput.equalsIgnoreCase(correctWord)){
        state = 1;
        score = score + 1;
      }
      else{
        tries--;
        if(tries <= 0){
          state = 0;
          score = score - 1;
        }
      }
    }
    return state;
  }

  public void newWord(){
    correctWord = chooseWord();
    tries = correctWord.length() - 1;
    guessedWord = new char[correctWord.length()];
    Arrays.fill(guessedWord, '_');
  }

  public String getWord(){/* if guessed a letter right or wrong, this will return the word  */
    return String.copyValueOf(guessedWord);
  }
  public String lostWord(){   //  if game is lost, this will return the word
    return correctWord;
  }
  public int getTries(){
    return tries;
  }
  public int getScore(){
    return score;
  }
  private String chooseWord(){
    Random randomInt = new Random();
    String strWord = wordList.get(randomInt.nextInt(wordList.size()));
    return strWord.toLowerCase();
  }
}
