import java.net.*;
import java.io.*;
import tcpclient.TCPClient;

public class ConcHTTPAsk{

  public static void main(String[] args) throws IOException{
    try{
      ServerSocket welcome = new ServerSocket((args.length < 1 ? 8888 : Integer.parseInt(args[0])));

      while(true){
        Socket cSocket = welcome.accept();
        new Thread(new MyRunnable(cSocket)).start();
      }
    }catch(Exception e){  System.out.println("Error");  }
  }
}

class MyRunnable implements Runnable{
  Socket cSocket;

	public MyRunnable(Socket c){
		cSocket = c;
  }

  public void run(){
    try{
      BufferedReader inFromClient = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
      DataOutputStream  outToClient = new DataOutputStream(cSocket.getOutputStream());

      String stringFromClient = inFromClient.readLine();
      String[] splitFromClient = stringFromClient.split("[ ?=&]");
      String output = "";
      String hostname = "";
      int port = 8888;
      String statement = null;

      if(splitFromClient[0].equals("GET")){
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
      }
      else{ outToClient.writeBytes("HTTP/1.1 400 Bad Request\r\n\r\n" + "That was a bad request");  }
      cSocket.close();
      inFromClient.close();
      outToClient.close();
    }catch(Exception e){  System.out.println("Error");  }
  }
}
