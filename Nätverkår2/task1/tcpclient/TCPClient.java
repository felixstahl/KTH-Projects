package tcpclient;
import java.net.*;
import java.io.*;

public class TCPClient {

    public static String askServer(String hostname, int port, String ToServer) throws  IOException {
      Socket clientSocket = new Socket(hostname, port);
      String msgFromServer = "";
      String oneLine = "";
      DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
      outToServer.writeBytes(ToServer + '\n');
      BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      clientSocket.setSoTimeout(2000);
      int bufferReads = 0;
      try{
        while((oneLine = inFromServer.readLine()) != null && (bufferReads <= 200)){
          msgFromServer += oneLine +"\r\n";
          bufferReads++;
          }
      }
      catch(Exception e){}

      clientSocket.close();
      return msgFromServer;
    }
//behövs denna ens?, den kallas ju ändå inte?
    public static String askServer(String hostname, int port) throws  IOException {
      Socket clientSocket = new Socket(hostname, port);
      String msgFromServer = "";
      String oneLine = "";
      DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
      BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      clientSocket.setSoTimeout(2000);
      int bufferReads = 0;
      try{
        while((oneLine = inFromServer.readLine()) != null && (bufferReads <= 200)){
          msgFromServer += oneLine;
          bufferReads++;
          }
      }
      catch(Exception e){}

      clientSocket.close();
      return msgFromServer;
    }
}
