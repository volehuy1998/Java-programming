package com;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {
    private static int port = 6789;
    private static ServerSocket listener;
    private static Socket socketOfServer;
    private static ObjectInputStream objectInputStream;
    private static ObjectOutputStream objectOutputStream;
    private static boolean quit = false;
    private static Store store;
    private static MySQL database;

    public static void main(String[] args) {
        try {
            listener = new ServerSocket(port);
            System.out.println("Server listening");
            socketOfServer = listener.accept();
            System.out.println("Client connect");
            objectOutputStream = new ObjectOutputStream(socketOfServer.getOutputStream());
            objectOutputStream.flush();
            objectInputStream = new ObjectInputStream(socketOfServer.getInputStream());
            database = new MySQL();
            store = new Store(database.getBooks());

            while (!quit) {
                int pick = objectInputStream.readInt();
                switch (pick) {
                    case 1:
                    {
                        showBooks();
                        break;
                    }
                    case 2:
                    {
                        int id = objectInputStream.readInt();
                        if (id == -1) {
                            break;
                        } else {
                            objectOutputStream.writeObject(store.borrowBook(id));
                            objectOutputStream.reset();
                        }
                        break;
                    }
                    case 3:
                    {
                        int id = objectInputStream.readInt();
                        store.payBook(id);
                        break;
                    }
                    case 4:
                    {
                        quit = true;
                        break;
                    }
                    default: break;
                }
            }
            System.out.println("Server down");
            socketOfServer.close();
            listener.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showBooks() {
        try {
            objectOutputStream.writeObject(store.getBooks());
            objectOutputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
