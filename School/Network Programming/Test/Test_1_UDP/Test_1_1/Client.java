package Test_1_1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static DatagramSocket datagramSocket;
    private static InetAddress inetAddress;
    private static DatagramPacket datagramPacket;
    private static byte inputFromServer[];
    private static byte outputToServer[];
    private static Scanner scanner;
    
    public static void main(String[] args) {
        try {
            datagramSocket = new DatagramSocket();
            inetAddress = InetAddress.getByName("localhost");
            scanner = new Scanner(System.in);
            
            /* Send */
            outputToServer = new byte[1024];
            System.out.print("Character: ");
            String chr = scanner.nextLine();
            outputToServer = chr.getBytes();
            int outputLen = outputToServer.length;
            datagramPacket = new DatagramPacket(outputToServer, outputLen, inetAddress, 9999);
            datagramSocket.send(datagramPacket);
            
            /* Receive */
            inputFromServer = new byte[1024];
            datagramPacket = new DatagramPacket(inputFromServer, outputLen);
            datagramSocket.receive(datagramPacket);
            String received = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
            System.out.println("Result from server: " + received);
            
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
