import java.net.*;
import java.io.*;

public class HTTPEcho {
    public static void main( String[] args) {
        try{
          ServerSocket welcomeSocket = new ServerSocket(args.length < 1 ? 8888 : Integer.parseInt(args[0]));
          while(true){
            Socket connectionSocket = welcomeSocket.accept();

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream  outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            String clientText = "HTTP/1.1 200 OK\r\n\r\n";
            String line = "";

            while((line = inFromClient.readLine()) != null){
              clientText += line + "\r\n";
              if(line.equals("")){break;}
            }

            outToClient.writeBytes(clientText);
            connectionSocket.close();
            inFromClient.close();
		        outToClient.close();
          }
        }
      catch(Exception e){}
    }
}
