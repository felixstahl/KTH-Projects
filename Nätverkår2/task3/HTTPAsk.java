import java.net.*;
import java.io.*;
import tcpclient.TCPClient;

public class HTTPAsk {
    public static void main( String[] args) {
      try{
        ServerSocket wSocket = new ServerSocket(args.length < 1 ? 8889 : Integer.parseInt(args[0]));
        while(true){
          Socket cSocket = wSocket.accept();
          BufferedReader inFromClient = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
          DataOutputStream  outToClient = new DataOutputStream(cSocket.getOutputStream());

          String stringFromClient = inFromClient.readLine();
          String[] splitFromClient = stringFromClient.split("[ ?=&]");
          String output ="";
          String hostname ="";
          int port = 8888;
          String statement = null;

          if(splitFromClient[1].equals("/ask")){
            for(int i = 0; i < splitFromClient.length; i++){
              if(splitFromClient[i].equals("hostname")){
                hostname = splitFromClient[i+1];
                i++;
              }
              else if(splitFromClient[i].equals("port")){
                port = Integer.parseInt(splitFromClient[i+1]);
                i++;
              }
              else if(splitFromClient[i].equals("string")){
                statement = splitFromClient[i+1];
                i++;
              }
            }
            try{
              output = TCPClient.askServer(hostname, port, statement);
              outToClient.writeBytes("HTTP/1.1 200 OK\r\n\r\n" + output);
            }catch(Exception e){
              outToClient.writeBytes("HTTP/1.1 404 Page not found\r\n\r\n" + "Page not found");
            }
          }
          else{
            outToClient.writeBytes("HTTP/1.1 400 Bad Request\r\n\r\n" + "That was a bad request");
          }
          cSocket.close();
        }
      }
      catch(Exception e){}
    }
}
