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
    private String username;
    private int quantity;

    public Cart(String usern,int qte) {
        books = new ArrayList<>();
        this.username=usern;
        this.quantity=qte;
    }

    public void addBook(Book book,String user,int qte) {
        books.add(book);
        this.username=user;
        this.quantity=qte;
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> getBooks() {
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
    
    
}
