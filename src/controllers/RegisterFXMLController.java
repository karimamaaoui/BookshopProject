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
import org.controlsfx.control.*;
import RKinfotech.MysqlMd5;
import java.security.NoSuchAlgorithmException;
import javafx.event.ActionEvent;


/**
 * FXML Controller class
 *
 * @author user
 */
public class RegisterFXMLController implements Initializable {

     @FXML
    private TextField email;

    @FXML
    private ImageView imageLogo;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phone;

    @FXML
    private TextField username;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void Register(MouseEvent event) throws IOException, SQLException, NoSuchAlgorithmException{

        if(!(username.getText().equals("")) && !(password.getText().equals(""))){
           try {

            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            Statement statement = connection.createStatement();

            PreparedStatement stmt = connection.prepareStatement("INSERT INTO user (`username`, `email`, `password`, `phone`) VALUES (?,?,?,?);");
            String passwordCrypter;
            passwordCrypter=MysqlMd5.getRKmd5(this.password.getText());
                   
            stmt.setString(1, this.username.getText());  
            
            stmt.setString(3,  passwordCrypter);
            stmt.setString(2,  this.email.getText());
            stmt.setString(4,  this.phone.getText());

           int status = stmt.executeUpdate();
            if (status > 0) {
           Stage stage=new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("../views/homeFXML.fxml"));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root,1000,600));
                stage.show();
                
   
                
                ((Node)event.getSource()).getScene().getWindow().hide();
               check();
            
            } else {
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Invalid ");
                alert.show();
            }
      
                } catch (SQLException e) {
            e.printStackTrace();
        }
 
               }else {
                       ErrorAlert();
                        }
                  
        }
    
    @FXML    
    public void error(){
             Notifications notification = Notifications.create();
             notification.title("Error");
             notification.text("Error username or password is incorrect");
             notification.hideAfter(Duration.seconds(5));
             notification.position(Pos.BASELINE_RIGHT);
             notification.show();
             
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
    public void goToLogin(MouseEvent mouseEvent){
        
      try {
            Parent parent = FXMLLoader.load(getClass().getResource("../views/loginFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
