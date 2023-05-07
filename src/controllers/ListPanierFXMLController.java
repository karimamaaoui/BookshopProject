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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Cart;
import models.Category;

public class ListPanierFXMLController implements Initializable {

    
      @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<Cart> panierTable;

    @FXML
    private TableColumn<Cart,Integer> qteColumn;

    @FXML
    private TextField qteInput;

    @FXML
    private TableColumn<Cart, String> refColumn;

    @FXML
    private TextField refInput;

    @FXML
    private Button updateBtn;

    @FXML
    private TableColumn<Cart, String> usernameColumn;

    @FXML
    private TextField usernameinput;
    
        public void table() {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ObservableList<Cart> carts = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM panier");
            ResultSet rs = pst.executeQuery();

            {
                while (rs.next()) {
                    
                    Cart cat = new Cart(rs.getString("username"),rs.getInt("quantity"));
                    


                    carts.add(cat);
                }
            }
            panierTable.setItems(carts);
            refColumn.setCellValueFactory(new PropertyValueFactory<Cart,String>("book_id"));

            usernameColumn.setCellValueFactory(new PropertyValueFactory<Cart,String>("user_id"));
            qteColumn.setCellValueFactory(new PropertyValueFactory<Cart,Integer>("quantity"));

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
    }    
    
}
