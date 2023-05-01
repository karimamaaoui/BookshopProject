/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;

/**
 *
 * @author user
 */
public class Order {
    
    private User user;
    private List<Book> books;

    public Order(User user, List<Book> books) {
        this.user = user;
        this.books = books;
    }

    public User getUser() {
        return user;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
    
    
    
    
}
