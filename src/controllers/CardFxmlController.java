/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import connectivity.ConnectionClass;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Book;
import models.UserSession;

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

    @FXML
    private HBox box;
 
    private String currentUser;

    @FXML
    void handleBookSelection(MouseEvent event) {

    }

      public void setUsername(String username) {
        //getCurrentUser.setText(username);
         currentUser = username;
         currentUser = UserSession.getCurrentUsername();

    }


   // private String[] colors = {"45b6fe", "3792CB", "296d98", "1c466"};
    
    
    private void showBookDetails(Book book) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/bookDetailsFXML.fxml"));
            Parent root = loader.load();
            BookDetailsController controller = loader.getController();
            controller.setSelectedBook(book);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    public void setData(Book book) {
        Image image = book.getImage();
        titleLabel.setText(book.getTitle());
        authorLabel.setText(book.getAuthor());

        bookImage.setImage(image);
        box.setStyle("-fx-background-color : #ffff" + ";"
                + "-fx-background-radius: 20;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0),10,0,0,10)"
        );

        box.setOnMouseClicked(event -> {
            showBookDetails(book);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    String currentUser = UserSession.getCurrentUsername();
    System.out.println("current user from cart "+currentUser);        
 
    }
}
