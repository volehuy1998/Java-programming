
package Test_2_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static BufferedReader bufferReader;
    private static BufferedWriter bufferWriter;
    
    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(9999);
            System.out.println("Server listening");
            socket = serverSocket.accept();
            System.out.println("Client connect");
            bufferWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            int fromClient = Integer.parseInt(bufferReader.readLine().trim());
            System.out.println("Client send: " + fromClient);
            String result = (1 == (0x1 & fromClient)) ? "Lẻ" : "Chẵn";
            bufferWriter.write(result);
            bufferWriter.newLine();
            bufferWriter.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                socket.close();
                serverSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
