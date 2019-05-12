/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_6_1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class Server {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(9999);
            System.out.println("RMI Server Running ....");
            ExceptCal calImpl = new ExceptCalImpl();
            Naming.rebind("rmi://localhost:9999/except", calImpl);

        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
