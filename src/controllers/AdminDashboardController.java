/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import RKinfotech.MysqlMd5;
import connectivity.ConnectionClass;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import models.User;
import models.UserSession;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AdminDashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField email;
    
    @FXML
    private StackPane contentStack;


    @FXML
    private PasswordField password;
    
    @FXML
    private TextField username;

    @FXML
    private TextField phone;
    
    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> phoneColumn;

    @FXML
    private TableColumn<User, String> passwordColumn;

    @FXML
    private TableView<User> tableViewUsers;

    @FXML
    private TableColumn<User, String> usernameColumn;
    
    @FXML
    private Label getCurrentUser;
    public int myIndex;

    @FXML
    void addBtn(MouseEvent event) throws NoSuchAlgorithmException {
        String usrname,emailuser,passowrdeuser,phoneuser;
            usrname = username.getText();
            emailuser = email.getText();
            passowrdeuser = password.getText();
            phoneuser = phone.getText();

            try 
        {  
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
      
            PreparedStatement pst = connection.prepareStatement("INSERT INTO user (`username`, `email`, `password`, `phone`) VALUES (?,?,?,?);");
            
            String passwordCrypter;
            passwordCrypter=MysqlMd5.getRKmd5(passowrdeuser);

            pst.setString(1, usrname);
            pst.setString(2, emailuser);
            pst.setString(3, passwordCrypter);
            pst.setString(4, phoneuser);

            pst.executeUpdate();
          
            	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("User Registation");
		alert.setHeaderText("User Registation");
		alert.setContentText("Record Addedddd!");

		alert.showAndWait();

            table();
            
            username.setText("");
            email.setText("");
            phone.setText("");
            password.setText("");
        } 
        catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
    }

    @FXML
    void deleteBtn(MouseEvent event) {
       myIndex = tableViewUsers.getSelectionModel().getSelectedIndex();
	String usrname;	 
        usrname = tableViewUsers.getItems().get(myIndex).getUsername();   

        try 
        {
             ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
      
           PreparedStatement pst = connection.prepareStatement("delete from user where username = ? ");
            pst.setString(1, usrname);
            pst.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User Registationn");

		
		alert.setHeaderText("User Registation");
		alert.setContentText("Deletedd!");

		alert.showAndWait();
                  table();
            username.setText("");
            email.setText("");
            phone.setText("");
            password.setText("");
        
        } 
        catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
    }

    @FXML
    void updateBtn(MouseEvent event) {
        
        String usrname,stname,mobile,passwd;
        
         myIndex = tableViewUsers.getSelectionModel().getSelectedIndex();
         
        usrname = tableViewUsers.getItems().get(myIndex).getUsername();   
            stname = email.getText();
           System.out.println("username "+stname);

            mobile = phone.getText();
            passwd = password.getText();
        try 
        {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection con = connectionClass.getConnection();
      
            PreparedStatement  pst = con.prepareStatement("update user set email = ?,password = ? ,phone = ? where username = ? ");
            pst.setString(1, stname);
            pst.setString(2, passwd);
            pst.setString(3, mobile);
            pst.setString(4, usrname);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setTitle("User Registationn");

		
		alert.setHeaderText("User Registation");
		alert.setContentText("Updateddd!");

		alert.showAndWait();
                table();
                   username.setText("");
            email.setText("");
            phone.setText("");
            password.setText("");
        
        } 
        catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
    }
    
     public void table()
      {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
          ObservableList<User> users = FXCollections.observableArrayList();
       try 
       {
          PreparedStatement pst = connection.prepareStatement("SELECT username, email, phone, password FROM user");  
           ResultSet rs = pst.executeQuery();
          
           System.out.println("pst"+pst);

           {
        while (rs.next())
        {
            User us = new User();
            us.setUsername(rs.getString("username"));
            us.setEmail(rs.getString("email"));
            us.setPassword(rs.getString("password"));
            us.setPhone(rs.getString("phone"));

            users.add(us);
       }
    } 
                tableViewUsers.setItems(users);
                usernameColumn.setCellValueFactory(f -> f.getValue().usernameProperty());
                emailColumn.setCellValueFactory(f -> f.getValue().emailProperty());
                passwordColumn.setCellValueFactory(f -> f.getValue().passwordProperty());
                phoneColumn.setCellValueFactory(f -> f.getValue().phoneProperty());
                
               

       }
       
       catch (SQLException ex) 
       {
           System.out.println(ex.toString());
          }

                tableViewUsers.setRowFactory( tv -> {
		    TableRow<User> myRow = new TableRow<>();
		     myRow.setOnMouseClicked (event -> 
		     {
		        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
		        {
		            myIndex =  tableViewUsers.getSelectionModel().getSelectedIndex();
		 
		           username.setText(tableViewUsers.getItems().get(myIndex).getUsername());
		           email.setText(tableViewUsers.getItems().get(myIndex).getEmail());
		           password.setText(tableViewUsers.getItems().get(myIndex).getPassword());
                           phone.setText(tableViewUsers.getItems().get(myIndex).getPhone());
                           
                         
                           
		        }
		     });
		        return myRow;
                   });
    
    
      }
     
    @FXML
     public void home(MouseEvent event )throws IOException{
                Parent fxml = FXMLLoader.load(getClass().getResource("../views/homeFXML.fxml"));
                
                  
                contentStack.getChildren().removeAll();
                contentStack.getChildren().setAll(fxml);
     }

    @FXML
    void categoryPage(MouseEvent event) throws IOException {
             Parent fxml = FXMLLoader.load(getClass().getResource("../views/CategoryFXML.fxml"));
                contentStack.getChildren().removeAll();
                contentStack.getChildren().setAll(fxml);
  
    }

  @FXML
    void bookPage(MouseEvent event) throws IOException {
           Parent fxml = FXMLLoader.load(getClass().getResource("../views/BookFXML.fxml"));
            
                contentStack.getChildren().removeAll();
                contentStack.getChildren().setAll(fxml);
  
    }
    
    
    public void setUsername(String username) {
        getCurrentUser.setText(username);
    }
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
        String currentUser = UserSession.getCurrentUsername();
         System.out.println("current user from admin "+currentUser);        
    }    
    
    
}
