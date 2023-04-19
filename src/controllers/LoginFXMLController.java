/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import connectivity.ConnectionClass;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LoginFXMLController implements Initializable {

       @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    
    @FXML
    private ImageView imageLogo;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void login(MouseEvent event) throws IOException, SQLException{
         String uname = username.getText();
         String pwd = password.getText();
        if (uname.equals("") && pwd.equals("")) {
            
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("Wrong username or password!");
            a.show();
        } else {
            try {
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                System.out.println("connection " +connection);

                PreparedStatement pst = connection.prepareStatement("select * from user where username=? and password=?");
                pst.setString(1, uname);
                pst.setString(2, pwd);
                ResultSet resultSet = pst.executeQuery();
                if (resultSet.next()) {
                    Parent parent = FXMLLoader.load(getClass().getResource("../views/homeFXML.fxml"));
                    Scene scene = new Scene(parent);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @FXML
    private void ErrorAlert(){
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Error username or password is incorrect");
        alert.setHeaderText("Error alert");
        alert.showAndWait();
    }
    
    
    @FXML
    public void check(){
             Notifications notification = Notifications.create();
             notification.title("Success");
             notification.text("Welcome");
             notification.hideAfter(Duration.seconds(5));
             notification.position(Pos.BASELINE_RIGHT);
             notification.show();
             
    }
      @FXML
    public void goToRegister(MouseEvent mouseEvent){
        
      try {
            Parent parent = FXMLLoader.load(getClass().getResource("../views/registerFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
