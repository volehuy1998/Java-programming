package Test_6_1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ExceptCal extends Remote {
	
	public int except (int a, int b) throws RemoteException;

}
