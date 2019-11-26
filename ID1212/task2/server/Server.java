import java.io.*;
import manager.ClientManager;

public class Server{
  public static void main(String[] args) throws IOException {
    try{
      System.out.println("Starting client manager...");
        ClientManager cm = new ClientManager();
    } catch(Exception e){ e.printStackTrace();  }
  }
}
