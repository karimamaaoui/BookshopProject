/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import RKinfotech.MysqlMd5;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Category;

public class CategoryFXMLController implements Initializable {

        public int myIndex;

    @FXML
    private Button addBtn;

    @FXML
    private TableView<Category> catTable;

    @FXML
    private TableColumn<Category, String> labelColumn;

    @FXML
    private TextField labelinput;

    @FXML
    public void addClick(MouseEvent event) {
        String labelcat;
            labelcat = labelinput.getText();
         
            try 
        {  
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
      
            PreparedStatement pst = connection.prepareStatement("INSERT INTO category (`label`) VALUES (?);");
            
            pst.setString(1, labelcat);
         
            pst.executeUpdate();
          
            	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Category Registation");

		
		alert.setHeaderText("Category Registation");
		alert.setContentText("Record Addedddd!");

		alert.showAndWait();

            table();
            
            labelinput.setText("");
            } 
        catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
   
    }
    
      @FXML
    void deleteClick(MouseEvent event) {
    myIndex = catTable.getSelectionModel().getSelectedIndex();
	String lab;	 
        lab = catTable.getItems().get(myIndex).getLabel();   

        try 
        {
             ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
      
           PreparedStatement pst = connection.prepareStatement("delete from category where label = ? ");
            pst.setString(1, lab);
            pst.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cztegory Registationn");

		
		alert.setHeaderText("Category Registation");
		alert.setContentText("Deletedd!");

		alert.showAndWait();
                  table();
            labelinput.setText("");
          
        } 
        catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
   
    }

    @FXML
    void updateClick(MouseEvent event) {
     String lab;
        
         myIndex = catTable.getSelectionModel().getSelectedIndex();
         
        lab = catTable.getItems().get(myIndex).getLabel();   
           
        try 
        {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection con = connectionClass.getConnection();
      
            PreparedStatement  pst = con.prepareStatement("update cateogry set label = ? ");
            pst.setString(1, lab);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Category Registationn");

		
		alert.setHeaderText("Category Registation");
		alert.setContentText("Updateddd!");

		alert.showAndWait();
                table();
                   labelinput.setText("");
        
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
        ObservableList<Category> categories = FXCollections.observableArrayList();
       try 
       {
          PreparedStatement pst = connection.prepareStatement("SELECT label FROM category");  
           ResultSet rs = pst.executeQuery();
          

           {
        while (rs.next())
        {
            Category cat = new Category();
            cat.setLabel(rs.getString("label"));
         
            categories.add(cat);
       }
    } 
                catTable.setItems(categories);
                labelColumn.setCellValueFactory(f -> f.getValue().labelProperty());
          
       }
       
       catch (SQLException ex) 
       {
           System.out.println(ex.toString());
          }

                catTable.setRowFactory( tv -> {
		    TableRow<Category> myRow = new TableRow<>();
		     myRow.setOnMouseClicked (event -> 
		     {
		        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
		        {
		            myIndex =  catTable.getSelectionModel().getSelectedIndex();
		 
		           labelColumn.setText(catTable.getItems().get(myIndex).getLabel());
		           
                         
                           
		        }
		     });
		        return myRow;
                   });
    
    
      }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
    }    
    
}
