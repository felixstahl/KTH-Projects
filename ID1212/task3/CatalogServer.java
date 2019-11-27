import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CatalogServer extends Remote{

  void printMsg() throws RemoteException;

}
