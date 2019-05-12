package Test_4_1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SquareCal extends Remote {
	
	public int square (int a) throws RemoteException;

}
