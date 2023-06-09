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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Book;
import models.UserSession;

/**
 * FXML Controller class
 *
 * @author user
 */
public class HomeFXMLController implements Initializable {

    @FXML
    private Label getCurrentUser;
    private String currentUser;

    @FXML
    private GridPane bookContainer;

    @FXML
    private HBox cardLayout;

    private List<Book> recentlyList;
    private List<Book> recommended;

    @FXML
    private TextField searchInput;

    /**
     * Initializes the controller class.
     */
    public void setUsername(String username) {
        //getCurrentUser.setText(username);
        this.currentUser = username;
        currentUser = UserSession.getCurrentUsername();
    }
 @FXML
    void goToCard(ActionEvent event) throws IOException {
   FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/PanierListFXML.fxml"));

        // Parent parent = FXMLLoader(getClass().getResource("../views/homeFXML.fxml"));
        Parent root = loader.load();

        ListPanierFXMLController controller = loader.getController();
        controller.setUsername(currentUser);
              Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
       
  

    }
   
    @FXML
    void cartPage(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/cardFxml.fxml"));

        // Parent parent = FXMLLoader(getClass().getResource("../views/homeFXML.fxml"));
        Parent root = loader.load();

        // AdminDashboardController controller = loader.getController();
        //   controller.setUsername(currentUser);
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    public List<Book> showBooks() {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ObservableList<Book> livres = FXCollections.observableArrayList();
        List<Book> books = new ArrayList<Book>();

        ImageView imageVie = null;

        try {
            PreparedStatement pst = connection.prepareStatement("SELECT ref,title,description,Pages,category,status,language, Year, price, author,image FROM livre ");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int i = 1;
                Blob blob = rs.getBlob("image");
                InputStream inputStream = blob.getBinaryStream();
                Image img = new Image(inputStream);

                imageVie = new ImageView(img);
                imageVie.setFitWidth(40);
                imageVie.setFitHeight(40);

                Book livre = new Book();
                livre.setRef(rs.getString("ref"));
                livre.setTitle(rs.getString("title"));
                livre.setDescription(rs.getString("description"));
                livre.setPages(rs.getInt("Pages"));
                livre.setCategory(rs.getString("category"));
                livre.setStatus(rs.getString("status"));
                livre.setLanguage(rs.getString("language"));
                livre.setYear(rs.getInt("Year"));
                livre.setPrice(rs.getFloat("price"));
                livre.setAuthor(rs.getString("author"));

                livre.setImage(img);

                livres.add(livre);
                books.add(livre);
                //     titleLabel.setText(rs.getString("title"));

                //  authorLabel.setText(rs.getString("author"));
                //bookImage.setImage(img);
            }
            //  }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return books;

    }


    public List<Book> Books() {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ObservableList<Book> livres = FXCollections.observableArrayList();
        List<Book> books = new ArrayList<Book>();

        ImageView imageVie = null;

        try {
            PreparedStatement pst = connection.prepareStatement("SELECT ref,title,description,Pages,category,status,language, Year, price, author,image FROM livre");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int i = 1;
                Blob blob = rs.getBlob("image");
                InputStream inputStream = blob.getBinaryStream();
                Image img = new Image(inputStream);

                imageVie = new ImageView(img);
                imageVie.setFitWidth(40);
                imageVie.setFitHeight(40);

                Book livre = new Book();
                livre.setRef(rs.getString("ref"));
                livre.setTitle(rs.getString("title"));
                livre.setDescription(rs.getString("description"));
                livre.setPages(rs.getInt("Pages"));
                livre.setCategory(rs.getString("category"));
                livre.setStatus(rs.getString("status"));
                livre.setLanguage(rs.getString("language"));
                livre.setYear(rs.getInt("Year"));
                livre.setPrice(rs.getFloat("price"));
                livre.setAuthor(rs.getString("author"));

                livre.setImage(img);

                livres.add(livre);
                books.add(livre);
                //     titleLabel.setText(rs.getString("title"));

                //  authorLabel.setText(rs.getString("author"));
                //bookImage.setImage(img);
            }
            //  }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return books;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        String currentUser = UserSession.getCurrentUsername();

        recentlyList = new ArrayList<>(showBooks());
        /*   recommended=new ArrayList<>(Books());
        int column=0;
        int row=1;
         */
        try {
            for (int i = 0; i < recentlyList.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../views/cardFxml.fxml"));

                // Parent parent = FXMLLoader(getClass().getResource("../views/homeFXML.fxml"));
                HBox cardBox = loader.load();
                CardFxmlController controller = loader.getController();
                controller.setUsername(currentUser);
                //System.out.println("current user from home" + currentUser);
                controller.setData(recentlyList.get(i));
                cardLayout.getChildren().add(cardBox);

            }
            /* for (Book book: recommended){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/smallBookFxml.fxml"));

                // Parent parent = FXMLLoader(getClass().getResource("../views/homeFXML.fxml"));
                
                VBox vBox = loader.load();
                SmallBookFXMLController smallcontroller = loader.getController();
              //  controller.setUsername(currentUser);
                smallcontroller.setData(book);
               // cardLayout.getChildren().add(vBox);
               if (column==5){
                   column=0;
                   ++row;
               }
                   System.out.println("Column"+column);
                   System.out.println("row"+row);

               bookContainer.add(vBox,column++,row);
               GridPane.setMargin(vBox, new Insets(10));*/

            //}
        } catch (IOException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
