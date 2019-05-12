package com;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String producer;
    private boolean borrow;

    public boolean isBorrow() {
        return borrow;
    }

    public void setBorrow(boolean borrow) {
        this.borrow = borrow;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                '}';
    }

    public Book(String id, String name, String producer) {
        this.id = id;
        this.name = name;
        this.producer = producer;
    }

    public String getId() {
        return id;
    }
}
