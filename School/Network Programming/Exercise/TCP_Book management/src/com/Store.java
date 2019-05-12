package com;

import java.util.*;

public class Store {
    ArrayList<Book> booksArrayList;
    Map<Integer, Book> booksMap;
    private int total;
    private int borrow;

    public Store(ArrayList<Book> books) {
        this.booksArrayList = books;
        this.total = this.booksArrayList.size();
        this.booksMap = new HashMap<Integer, Book>();
        for (Book book : this.booksArrayList) {
            this.booksMap.put(Integer.parseInt(book.getId()), book);
        }
    }

    /*public void listBooks() {
        System.out.println("------ " + this.total + " ------");
        this.booksMap.entrySet().stream().forEach(book -> System.out.println(book.toString()));
    }

    public ArrayList<Book> getArrayListBooks() {
        return this.booksArrayList;
    }*/

    public Map<Integer, Book> getBooks() {
        Map<Integer, Book> books = new HashMap<Integer, Book>();
        for (Map.Entry<Integer, Book> entry : this.booksMap.entrySet()) {
            Book book = entry.getValue();
            if (book.isBorrow() == false) {
                books.put(Integer.parseInt(book.getId()), book);
            }
        }
        return books;
    }

    public Book borrowBook(int id) {
        if (this.booksMap.containsKey(id)) {
            this.total--;
            this.borrow++;
            this.booksMap.get(id).setBorrow(true);
            return this.booksMap.get(id);
        }
        return null;
    }

    public void payBook(int id) {
        if (this.booksMap.containsKey(id)) {
            this.total++;
            this.borrow--;
            this.booksMap.get(id).setBorrow(false);
        }
    }
}
