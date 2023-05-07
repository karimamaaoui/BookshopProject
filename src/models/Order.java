package models;

import java.time.LocalDateTime;
import models.Book;
import models.LoginModel;

public class Order {

    private int quantity;
    private String book_id, user_id;
    private LocalDateTime dateTime;

    public Order(int quantity, String book_id, String user_id, LocalDateTime dateTime) {
        this.quantity = quantity;
        this.book_id = book_id;
        this.user_id = user_id;
        this.dateTime = dateTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
