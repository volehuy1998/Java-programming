package com;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Client {
    private static int port = 6789;
    private static Socket socketOfClient;
    private static ObjectInputStream objectInputStream;
    private static BufferedReader fromKeyboardClient;
    private static ObjectOutputStream objectOutputStream;
    private static boolean quit = false;
    private static Map<Integer, Book> booksBorrow;

    public static void main(String[] args) throws IOException {
        socketOfClient = new Socket("localhost", port);
        objectOutputStream = new ObjectOutputStream(socketOfClient.getOutputStream());
        objectOutputStream.flush();
        objectInputStream = new ObjectInputStream(socketOfClient.getInputStream());
        fromKeyboardClient = new BufferedReader(new InputStreamReader(System.in));
        booksBorrow = new HashMap<Integer, Book>();

        while (!quit) {
            System.out.println("1. List books");
            System.out.println("2. Borrow");
            System.out.println("3. Pay");
            System.out.println("4. Exit");
            System.out.print("Client >> ");
            int pick = Integer.parseInt(fromKeyboardClient.readLine());
            System.out.println("*************************************");
            switch (pick) {
                case 1: listBooks(); break;
                case 2: borrow(); break;
                case 3: pay(); break;
                case 4:
                {
                    objectOutputStream.writeInt(4);
                    objectOutputStream.reset();
                    quit = true;
                    break;
                }
                default:
                    System.err.println("[1-4]");
                    break;
            }
        }
        System.out.println("Client down");
        socketOfClient.close();
    }

    public static void listBooks() {
        System.out.println("List: ");
        System.out.println("1. From server");
        System.out.println("2. Storing");
        System.out.print(">> ");
        try {
            int choose = Integer.parseInt(fromKeyboardClient.readLine());
            switch (choose) {
                case 1: listBooksServer(); break;
                case 2: listBooksStoring(); break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void listBooksServer() {
        try {
            objectOutputStream.writeInt(1);
            objectOutputStream.flush();
            Map<Integer, Book> books = (Map<Integer, Book>)objectInputStream.readObject();
            listBook(books);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void listBooksStoring() {
        listBook(booksBorrow);
    }

    public static void listBook(Map<Integer, Book> books) {
        if (books.isEmpty()) {
            System.err.println("Books empty to list");
        } else {
            books.entrySet().stream().forEach(book -> System.out.println(book.toString()));
        }
    }

    public static void borrow() {
        try {
            objectOutputStream.writeInt(2);
            System.out.print("Client borrow book[id] >> ");
            int id = Integer.parseInt(fromKeyboardClient.readLine());
            if (booksBorrow.containsKey(id) == false) {
                objectOutputStream.writeInt(id);
                objectOutputStream.reset();
                Book book = (Book)objectInputStream.readObject();
                if (book == null) {
                    System.err.println("Book[" + id + "] not exist");
                } else {
                    booksBorrow.put(Integer.parseInt(book.getId()), book);
                }
            } else {
                objectOutputStream.writeInt(-1);
                objectOutputStream.reset();
                System.err.println("Client stored book[" + id + "]");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void pay() {
        try {
            objectOutputStream.writeInt(3);
            System.out.print("Client pay book[id] >> ");
            int id = Integer.parseInt(fromKeyboardClient.readLine());
            if (booksBorrow.containsKey(id)) {
                objectOutputStream.writeInt(id);
                objectOutputStream.reset();
                booksBorrow.remove(id);
            } else {
                objectOutputStream.writeInt(-1);
                objectOutputStream.reset();
                System.err.println("Client don't borrow book[" + id + "]");
            }
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
