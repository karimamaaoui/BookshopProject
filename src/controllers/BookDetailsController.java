/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import models.Book;
import models.Cart;
import models.UserSession;

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

    private Cart cart;
    private PanierFXMLController panier;

    private String currentUser;
    @FXML
    private ImageView bookImage;
    @FXML
    private Label getCurrentUser;
    
    @FXML
    private Label bookPage;

    @FXML
    private Label bookStatus;

    @FXML
    private Label bookTitle;

    @FXML
    private Label languageLabel;


    private Book selectedBook;
    @FXML
    private Label bookLanguage;


    @FXML
    private Label priceLabel;
    @FXML
    public TextField qteInput;

    public void setSelectedBook(Book book) {
        selectedBook = book;
        showBookDetails();
    }

    @FXML
    public void addToCart() throws IOException {
        //cart.addBook(selectedBook);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/panierFXML.fxml"));

        // Parent parent = FXMLLoader(getClass().getResource("../views/homeFXML.fxml"));
        Parent root = loader.load();

        PanierFXMLController controller = loader.getController();
        //   controller.setUsername(currentUser);
        if (qteInput != null) {
            System.out.println("QTE " + qteInput.getText());

            int qte = Integer.parseInt(qteInput.getText());
            controller.addBook(selectedBook, currentUser, qte);
        }

        //String username = getCurrentUsername(); 
        // panier.addBook(selectedBook, username);
    }

    private void showBookDetails() {
        // Show the book details in the UI
        bookImage.setImage(selectedBook.getImage());
        bookTitle.setText(selectedBook.getTitle());
        bookAuthor.setText(selectedBook.getAuthor());
        bookDescription.setText(selectedBook.getDescription());
        bookPage.setText(Integer.toString(selectedBook.getPages()));
        bookStatus.setText(selectedBook.getStatus());
        priceLabel.setText(Float.toString(selectedBook.getPrice()));
        bookLanguage.setText(selectedBook.getLanguage());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentUser = UserSession.getCurrentUsername();
        System.out.println("current user from book details " + currentUser);
    }

}
