/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_6_1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ExceptCalImpl extends UnicastRemoteObject implements ExceptCal {

    protected ExceptCalImpl() throws RemoteException {

    }

    public int except(int a, int b) throws RemoteException {
        // TODO Auto-generated method stub
        return a - b;
    }

}
