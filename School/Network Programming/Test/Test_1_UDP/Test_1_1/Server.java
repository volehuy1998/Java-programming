package Test_1_1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {
    private static DatagramSocket datagramSocket;
    private static DatagramPacket datagramPacket;
    private static InetAddress inetAddress;
    private static byte inputFromClient[];
    private static byte outputToClient[];
    
    public static void main(String[] args) {
        try {
            datagramSocket = new DatagramSocket(9999);
            inputFromClient = new byte[1024];
            outputToClient = new byte[1024];
            int inputLen = inputFromClient.length;
            
            
            System.out.println("Server ready");
            
            /* Receive */
            datagramPacket = new DatagramPacket(inputFromClient, inputLen);
            datagramSocket.receive(datagramPacket);
            String received  = new String(datagramPacket.getData()).toUpperCase();
            System.out.println("Result send client: " + received);
            
            /* Send */
            inetAddress = datagramPacket.getAddress();
            int port = datagramPacket.getPort();
            outputToClient = received.getBytes();
            int outputLen = outputToClient.length;
            datagramPacket = new DatagramPacket(outputToClient, outputLen, inetAddress, port);
            datagramSocket.send(datagramPacket);
            
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
