import java.util.*;
import java.net.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class Client{
  public static void main(String[] args){
    try{
      System.out.println("Starting client...");
      SocketChannel channel = SocketChannel.open();
      Selector selector = Selector.open();

      InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
      SocketAddress inetAddress = new InetSocketAddress(serverAddress, 8888);
      channel.configureBlocking(false);
      channel.register(selector, SelectionKey.OP_CONNECT);
      channel.connect(inetAddress);

      Thread connection = new Thread(new Connection(channel, selector));
      connection.start();

      System.out.println("Client is up and running...");

      BufferedReader inFromClient = new BufferedReader(new InputStreamReader(System.in));
  		String input;
      ByteBuffer buffer = ByteBuffer.allocate(1024);
      while(true){
        input = inFromClient.readLine();
        buffer.clear();
        buffer.putLong(input.length());
        buffer.put(input.getBytes());
        buffer.flip();
        channel.write(buffer);
      }
    } catch(Exception e){  System.out.println("Exception catched in main inside client.java"); }
  }
}
