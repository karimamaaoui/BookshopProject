/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class Cart {
    private List<Book> books;
    private Book book;
    private String username;
    private int quantity;
    private float total;

    public Cart(String usern,int qte,float t) {
        books = new ArrayList<>();
        this.username=usern;
        this.quantity=qte;
        this.total=t;
    }

     public Cart(String username, int quantity, float total, Book book) {
        this.username = username;
        this.quantity = quantity;
        this.total = total;
        this.book = book;
    }
    public void addBook(Book book,String user,int qte,float t) {
        books.add(book);
        this.username=user;
        this.quantity=qte;
        this.total=t;

    }

     public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

public List<Book> getBooks() {
    if (books == null) {
        books = new ArrayList<>();
    }
    return books;
}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQunatity(int qte) {
        this.quantity = qte;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    
}
