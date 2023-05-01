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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Book;

/**
 * FXML Controller class
 *
 * @author user
 */
public class OrderFxmlController implements Initializable {

 @FXML
    private TextField author;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TextField description;

    @FXML
    private TableColumn<Book, String> descriptionColumn;

    @FXML
    private TextField ref;

    @FXML
    private TableColumn<Book, String> refColumn;

    @FXML
    private TableView<Book> tableViewUsers;

    @FXML
    private TextField title;

    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    public int myIndex;
  

    @FXML
    private TextField language;

    @FXML
    private TableColumn<Book, String> languageColumn;

    @FXML
    private TextField page;

    @FXML
    private TableColumn<Book, String> pageColumn;

    @FXML
    private TextField price;

    @FXML
    private TableColumn<Book, Float> priceColumn;


   
    @FXML
    private TextField year;

    @FXML
    private TableColumn<Book, Integer> yearColumn;

    
    
    
    
     @FXML
    void addBtn(MouseEvent event) {
  String re,tit,descri,auth,lan;
  int pag,yr;
  float pri;
  
            re = ref.getText();
            tit = title.getText();
            descri = description.getText();
            auth = author.getText();
            lan=language.getText();
            pag=Integer.parseInt(page.getText());
            yr=Integer.parseInt(year.getText());
            pri=Float.parseFloat(price.getText());
            
            try 
        {  
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
      
            PreparedStatement pst = connection.prepareStatement("INSERT INTO book (`ref`, `title`, `description`, `author`,`page`, `price`, `year`, `language`) VALUES (?,?,?,?,?,?,?,?);");
            
         
            pst.setString(1, re);
            pst.setString(2, tit);
            pst.setString(3, descri);
            pst.setString(4, auth);

            pst.setInt(5, pag);
            pst.setFloat(6,pri);
            pst.setInt(7,yr);
            pst.setString(8,lan);

            pst.executeUpdate();
          
            	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("User Registation");
		alert.setHeaderText("User Registation");
		alert.setContentText("Record Addedddd!");

		alert.showAndWait();

            table();
            
            ref.setText("");
            title.setText("");
            description.setText("");
            author.setText("");
            page.setText("");
            year.setText("");
            language.setText("");
            price.setText("");
        } 
        catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
    
    }

    @FXML
    void deleteBtn(MouseEvent event) {

    }

    @FXML
    void updateBtn(MouseEvent event) {
 String re,tit,descri,auth,lan;
  String pag,yr;
  String pri;
         
         myIndex = tableViewUsers.getSelectionModel().getSelectedIndex();
         
        re = tableViewUsers.getItems().get(myIndex).getRef();   
        System.out.println("affucher " +tableViewUsers.getItems().get(myIndex).getLanguage());
            tit = title.getText();
            descri = description.getText();
            auth = author.getText();
            lan=language.getText();

            System.out.println("lang*********************** "+language.getText());

            pag=page.getText();
            yr=year.getText();
            pri=price.getText();
            
        
        try 
        {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection con = connectionClass.getConnection();
      
            PreparedStatement  pst = con.prepareStatement("update book set title = ?,description = ? ,author = ?, page=?, price=?, year=?,  language =? where ref = ? ");
            pst.setString(1, tit);
            pst.setString(2, descri);
            pst.setString(3, auth);
          
            pst.setString(4, pag);
            pst.setString(5, pri);
            pst.setString(6, yr);
            pst.setString(7, lan);
            pst.setString(8, re);
         
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("User Registationn");

		
		alert.setHeaderText("User Registation");
		alert.setContentText("Updateddd!");

		alert.showAndWait();
                table();
            ref.setText("");
            title.setText("");
            description.setText("");
            author.setText("");
            language.setText("");
            page.setText("");
            price.setText("");
            year.setText("");
        
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
          ObservableList <Book> books = FXCollections.observableArrayList();
       try 
       {
          PreparedStatement pst = connection.prepareStatement("SELECT ref,title, description, author , page, price, year, language FROM book");  
           ResultSet rs = pst.executeQuery();
          
           System.out.println("pst"+pst);

           {
        while (rs.next())
        {
            Book us = new Book();
            us.setRef(rs.getString("ref"));

            us.setTitle(rs.getString("title"));
            us.setAuthor(rs.getString("author"));
            us.setDescription(rs.getString("description"));
            us.setPages(rs.getInt("page"));
            us.setPrice(rs.getFloat("price"));
            us.setYear(rs.getInt("year"));
            us.setLanguage(rs.getString("language"));



            books.add(us);
       }
    } 
                tableViewUsers.setItems(books);
                
                refColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("ref"));
        
                titleColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
                descriptionColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("description"));
                pageColumn.setCellValueFactory(new PropertyValueFactory<>("page"));

                
                languageColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("language"));

                yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
                priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                authorColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
              

       }
       
       catch (SQLException ex) 
       {
           System.out.println(ex.toString());
          }

                tableViewUsers.setRowFactory( tv -> {
		    TableRow<Book> myRow = new TableRow<>();
		     myRow.setOnMouseClicked (event -> 
		     {
		        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
		        {
		            myIndex =  tableViewUsers.getSelectionModel().getSelectedIndex();
                           ref.setText(tableViewUsers.getItems().get(myIndex).getRef());
		 
		           title.setText(tableViewUsers.getItems().get(myIndex).getTitle());
		           description.setText(tableViewUsers.getItems().get(myIndex).getDescription());
		           author.setText(tableViewUsers.getItems().get(myIndex).getAuthor());
                           language.setText(tableViewUsers.getItems().get(myIndex).getLanguage());
                           page.setText(Integer.toString(tableViewUsers.getItems().get(myIndex).getPages()));
                           price.setText(Float.toString(tableViewUsers.getItems().get(myIndex).getPrice()));
                           year.setText(Integer.toString(tableViewUsers.getItems().get(myIndex).getYear()));
                           
                           
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
