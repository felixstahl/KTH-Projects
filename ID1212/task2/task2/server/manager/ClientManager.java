package manager;

import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.net.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import logic.*;

public class ClientManager{
  private HashMap<SocketChannel, Game> allGames = new HashMap<SocketChannel, Game>();
  private Selector selector;
  private ServerSocketChannel ssChannel;

  public ClientManager() throws Exception{
    try{
      ssChannel = ServerSocketChannel.open();
      ssChannel.configureBlocking(false);
      ssChannel.socket().bind(new InetSocketAddress("127.0.0.1", 8888));
      selector = selector.open();

      int ops = ssChannel.validOps();
      ssChannel.register(selector, ops, SelectionKey.OP_ACCEPT);
      running();
    } catch(Exception e){
      System.out.println("Exception catched in constrictor inside ClientManager.java");
      e.printStackTrace();
    }
  }

    private void running(){
      try{
        while(true){
          selector.select();
          Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

          while(keys.hasNext()){
            SelectionKey key = keys.next();
            keys.remove();

            if(!key.isValid()){
              continue;
            }

            if(key.isAcceptable()){
              acceptClient(key);
            }
            else if(key.isReadable()){
              readClient(key);
            }
          }
        }
    } catch(Exception e){
      System.out.println("Exception catched in main inside Server.java");
      e.printStackTrace();
    }
  }

  private void acceptClient(SelectionKey key) throws IOException{
    System.out.println("Connection Accepted...");
    SocketChannel client = ssChannel.accept();
    client.configureBlocking(false);
    client.register(selector, SelectionKey.OP_READ);
    allGames.put(client, new Game());
    Game game = allGames.get(client);
    System.out.println("Game is up and running, the word is: " + game.lostWord());
  }

  private void readClient(SelectionKey key) throws IOException{
    SocketChannel readChannel = (SocketChannel)key.channel();
    Game game = allGames.get(readChannel);
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    buffer.clear();
    int read;
    read = readChannel.read(buffer);

    if (read == -1) {
      System.out.println("Nothing was there to be read, closing connection");
      readChannel.close();
      key.cancel();
      return;
    }

    buffer.flip();
    long dataLength = buffer.getLong();

    byte[] args = new byte[(int)dataLength];
    buffer.get(args, 0, (int)dataLength);

    String message = new String(args);
    System.out.println("Client guessed: " + message);

    int guessResult = game.guess(message);

    switch(guessResult){
      case 0:{  // lost game
        System.out.println("You lost... guessResult code: " + guessResult);
        String returnMessage = "0::" + game.lostWord() + "::" + game.getScore();

        buffer.clear();
        buffer.putLong(returnMessage.length());
        buffer.put(returnMessage.getBytes());
        buffer.flip();
        readChannel.write(buffer);
        game.newWord();
        System.out.println("Game is up and running, the word is: " + game.lostWord());
        break;
      }
      case 1:{  // won game
        System.out.println("You won! guessResult code: " + guessResult);
        String returnMessage = "1::" + game.lostWord() + "::" + game.getScore();

        buffer.clear();
        buffer.putLong(returnMessage.length());
        buffer.put(returnMessage.getBytes());
        buffer.flip();
        readChannel.write(buffer);
        game.newWord();
        System.out.println("Game is up and running, the word is: " + game.lostWord());
        break;
      }
      case 2:{  // guessed right
        System.out.println("Right guess, guessResult code: " + guessResult);
        String returnMessage = "2::" + game.getWord() +"::" + game.getTries();

        buffer.clear();
        buffer.putLong(returnMessage.length());
        buffer.put(returnMessage.getBytes());
        buffer.flip();
        readChannel.write(buffer);
        break;
      }
      case 3:{  // guessed wrong
        System.out.println("Wrong guess, guessResult code: " + guessResult);
        String returnMessage = "3::" + game.getWord() +"::" + game.getTries();

        buffer.clear();
        buffer.putLong(returnMessage.length());
        buffer.put(returnMessage.getBytes());
        buffer.flip();
        readChannel.write(buffer);
        break;
      }
    }
  }
}
