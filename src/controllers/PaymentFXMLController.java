package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.UserSession;

public class PaymentFXMLController implements Initializable {

    @FXML
    private Label getCurrentUser;
    @FXML
    void cancel1(ActionEvent event) {

    }

    @FXML
    void gpay(ActionEvent event) {

    }

    @FXML
    void proceed(ActionEvent event) {

    }
   private String currentUser;

    @FXML
    void handleBookSelection(MouseEvent event) {

    }

     public void setUsername(String username) {
         currentUser = username;
         currentUser = UserSession.getCurrentUsername();

    }
 @Override
    public void initialize(URL url, ResourceBundle rb) {
    String currentUser = UserSession.getCurrentUsername();
    System.out.println("current user from payment "+currentUser);        
 
    }
}
