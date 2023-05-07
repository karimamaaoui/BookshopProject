/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import connectivity.ConnectionClass;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Book;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CardFxmlController implements Initializable {

    @FXML
    private Label authorLabel;

    @FXML
    private Label titleLabel;
    @FXML
    private ImageView bookImage;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("hello from 156");

       // showBooks();
    }

    void setData(Book book) {
        Image image = book.getImage();
        titleLabel.setText(book.getTitle());
        authorLabel.setText(book.getAuthor());

        bookImage.setImage(image);
        

    }

}
