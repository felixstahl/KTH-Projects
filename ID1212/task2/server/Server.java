import java.util.*;
import java.net.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import logic.Game;

public class Server{
  public static Selector selector;
  public static ServerSocketChannel ssChannel;

  public static void main(String[] args) throws Exception{
    try{
      selector = selector.open();
      ssChannel = ServerSocketChannel.open();
      ssChannel.configureBlocking(false);
      ssChannel.socket().bind(new InetSocketAddress("127.0.0.1", 8888));
      int ops = ssChannel.validOps();
      ssChannel.register(selector, ops, SelectionKey.OP_ACCEPT);

      Game game = new Game();
      
      while(true){
        selector.select();
        Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

        while(keys.hasNext()){
          SelectionKey key = keys.next();
          keys.remove();
          if(key.isAcceptable()){
            acceptClient(key);
          }
          else if(key.isReadable()){
            readClient(key);
          }
        }
      }
    }catch(Exception e){
      System.out.println("Exception catched in main inside Server.java");
      e.printStackTrace();
    }
  }

  private static void acceptClient(SelectionKey key) throws IOException{
    System.out.println("Connection Accepted...");
    SocketChannel client = ssChannel.accept();
    client.configureBlocking(false);
    client.register(selector, SelectionKey.OP_READ);
  }

  private static void readClient(SelectionKey key) throws IOException{
    SocketChannel readChannel = (SocketChannel)key.channel();
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    buffer.clear();
    readChannel.read(buffer);

    buffer.flip();
    long dataLength = buffer.getLong();

    byte[] args = new byte[(int)dataLength];
    buffer.get(args, 0, (int)dataLength);

    String message = new String(args);
    System.out.println(message);

    int guessResult = game.guess(message);

    buffer.clear();
    buffer.putLong(message.length());
    buffer.put(message.getBytes());
    buffer.flip();
    readChannel.write(buffer);
  }
}
