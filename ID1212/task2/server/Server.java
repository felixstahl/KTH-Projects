import manager.ClientManager;

public class Server{
  public static void main(String[] args){
    try{
      System.out.println("Starting client manger...");
        ClientManager cm = new ClientManager();
    } catch(Exception e){ e.printStackTrace();  }
  }
}
