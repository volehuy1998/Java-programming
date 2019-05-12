package com;

import java.sql.*;
import java.util.ArrayList;

public class MySQL {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String url = "jdbc:mysql://localhost:3306/";
    private String user = "root", pass = "";
    private String db_table = "book_tb", db = "book_management_db";

    public MySQL() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url + db, user, pass);
        statement = connection.createStatement();
    }

    public ArrayList<Book> getBooks() throws SQLException {
        ArrayList<Book> books = new ArrayList<Book>();
        ResultSet resultSet = statement.executeQuery(
                "select * from " + db_table + " where borrow = false ");
        while (resultSet.next()) {
            books.add(new Book(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)));
        }
        return books;
    }
}
