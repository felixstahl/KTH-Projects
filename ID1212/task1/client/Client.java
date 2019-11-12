import java.util.*;
import java.net.*;
import java.io.*;
import receiver.Receiver;

public class Client{
  public static void main(String[] args){
    try{
      Socket s = new Socket();
      InetAddress server_ip = InetAddress.getByName("127.0.0.1");
      SocketAddress socketAddress = new InetSocketAddress(server_ip, 8888);

      s.connect(socketAddress);

      Scanner clientInput = new Scanner(System.in);

      Thread receiver = new Thread(new Receiver(s));
      receiver.start();

      if(s.isConnected()){
        String input;
        DataOutputStream outToServer = new DataOutputStream(s.getOutputStream());

        while(!s.isClosed()){
          input = clientInput.nextLine().toUpperCase();
          if(input.equals("!")){
            System.out.println("You have quit the game");
            s.close();
          }
          else{ outToServer.writeUTF(input);  }
        }
      }
      else{  s.close();  }
    }catch(Exception e){ System.out.println("Exception catched in main inside Client.java");}
  }
}
