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

/**
 * FXML Controller class
 *
 * @author user
 */
public class HomeFXMLController implements Initializable {

     @FXML
    private Label getCurrentUser;
    /**
     * Initializes the controller class.
     */
     public void setUsername(String username) {
        getCurrentUser.setText(username);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
