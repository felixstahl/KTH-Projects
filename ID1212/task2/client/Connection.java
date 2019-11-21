import java.util.*;
import java.net.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Connection implements Runnable{
  private SocketChannel channel;
  private Selector selector;
  private ByteBuffer buffer = ByteBuffer.allocate(1024);
  String input = "";

  public Connection(SocketChannel schan, Selector sel){
    channel = schan;
    selector = sel;
  }

  public void run(){
    try{
      while (true){
        this.selector.select();
        Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();

        while (keys.hasNext()){
          SelectionKey key = keys.next();
          keys.remove();

          if(key.isConnectable()){
            connectToServer(key);
          }
          else if(key.isReadable()){
            readServer(key);
          }
        }
      }
    }catch(Exception e){ System.out.println("Exception catched in run inside Connection.java");  }
  }

  private void connectToServer(SelectionKey key){
    try{
      SocketChannel connectChannel = (SocketChannel)key.channel();
      connectChannel.finishConnect();
      connectChannel.register(selector, SelectionKey.OP_READ);
    } catch(Exception e){  System.out.println("Exception catched in connectToServer inside Connection.java"); }
  }



  private void readServer(SelectionKey key){
    try{
      SocketChannel readChannel = (SocketChannel)key.channel();
      buffer.clear();
      readChannel.read(buffer);
      buffer.flip();
      long dataLength = buffer.getLong();

      byte[] args = new byte[(int)dataLength];
      buffer.get(args, 0, (int)dataLength);

      String message = new String(args);

      String[] res = new String(args).split("::");

      switch(Integer.parseInt(res[0])){
        case 0:{  // game lost
          System.out.println("You lost, the word was: " + res[1] + ", score: " + Integer.parseInt(res[2]));
          System.out.println("A new word is ready, keep guessing or quit with '!'");
          break;
        }
        case 1:{  // game won
          System.out.println("You won, the word was: " + res[1] + ", score: " + Integer.parseInt(res[2]));
          System.out.println("A new word is ready, keep guessing or quit with '!'");
          break;
        }
        case 2:{  // guessed right
          System.out.println("correct guess, the word is: " + res[1] + ", tries: " + Integer.parseInt(res[2]));
          System.out.println("A new word is ready, keep guessing or quit with '!'");
          break;
        }
        case 3:{  // guessed wrong
          System.out.println("Wrong guess, the word is: " + res[1] + ", tries: " + Integer.parseInt(res[2]));
          System.out.println("A new word is ready, keep guessing or quit with '!'");
          break;
        }
      }
    } catch(Exception e){ System.out.println("Exception catched in readServer inside Connection.java"); }
  }
}
