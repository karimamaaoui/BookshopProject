/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import models.Book;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BookDetailsController implements Initializable {



    @FXML
    private Label bookAuthor;

    @FXML
    private Label bookDescription;

    
    


    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookTitle;
    
    private Book selectedBook;
     public void setSelectedBook(Book book) {
        selectedBook = book;
        showBookDetails();
    }

    private void showBookDetails() {
        // Show the book details in the UI
        bookImage.setImage(selectedBook.getImage());
        bookTitle.setText(selectedBook.getTitle());
        bookAuthor.setText(selectedBook.getAuthor());
        bookDescription.setText(selectedBook.getDescription());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
