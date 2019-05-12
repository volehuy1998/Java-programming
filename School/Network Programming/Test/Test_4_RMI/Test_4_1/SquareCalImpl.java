/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_4_1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class SquareCalImpl extends UnicastRemoteObject implements SquareCal {

    protected SquareCalImpl() throws RemoteException {

    }

    public int square(int a) throws RemoteException {
        // TODO Auto-generated method stub
        return a * a;
    }

}
