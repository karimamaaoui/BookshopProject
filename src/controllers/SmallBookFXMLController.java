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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Book;
import models.UserSession;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SmallBookFXMLController implements Initializable {

    @FXML
    private Label authorLabel;
    @FXML
    private VBox box;

    @FXML
    private Label titleLabel;
    @FXML
    private ImageView bookImage;

 
    public void setData(Book book) {
        Image image = book.getImage();
        titleLabel.setText(book.getTitle());
        authorLabel.setText(book.getAuthor());

        bookImage.setImage(image);
      
      }
    
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
  

    @Override
    public void initialize(URL location, ResourceBundle resources) {
  String currentUser = UserSession.getCurrentUsername();
    System.out.println("current user from cart "+currentUser);        
 
    }

}
