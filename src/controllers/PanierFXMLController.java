/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import connectivity.ConnectionClass;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import models.Book;
import models.UserSession;

/**
 * FXML Controller class
 *
 * @author user
 */
public class PanierFXMLController implements Initializable {

    @FXML
    public TextField qteInput;

    @FXML
    public void addBook(Book book, String userId,int qte) {
        try {
            userId = UserSession.getCurrentUsername();
           
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO `panier`(`book_id`, `user_Id`,`quantity`) VALUES (?,?,?)");
                System.out.println("book.getRef()" + book.getRef());

                statement.setString(1, book.getRef());
            statement.setString(2, userId);
            statement.setInt(3, qte);
                
                statement.executeUpdate();
                System.out.println("Book added to cart!");
            
        } catch (SQLException e) {
            System.out.println("Error adding book to cart: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String currentUser = UserSession.getCurrentUsername();
        System.out.println("current user from panier " + currentUser);
    }

}
