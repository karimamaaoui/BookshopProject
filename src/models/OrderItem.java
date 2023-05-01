/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author user
 */
public class OrderItem {
    private Book book;
    private int qunatity;

    public OrderItem(Book book, int qunatity) {
        this.book = book;
        this.qunatity = qunatity;
    }

    public Book getBook() {
        return book;
    }

    public int getQunatity() {
        return qunatity;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setQunatity(int qunatity) {
        this.qunatity = qunatity;
    }
    
    
    
}
