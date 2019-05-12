package Test_2_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static Socket socket;
    private static BufferedReader bufferReader;
    private static BufferedWriter bufferWriter;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 9999);
            scanner = new Scanner(System.in);
            bufferWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            System.out.print("Number: ");
            String number = scanner.nextLine();
            bufferWriter.write(number);
            bufferWriter.newLine();
            bufferWriter.flush();
            String result = bufferReader.readLine();
            System.out.println(result);
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
