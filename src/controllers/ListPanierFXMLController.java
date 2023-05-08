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
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Book;
import models.Cart;
import models.Category;
import models.UserSession;

public class ListPanierFXMLController implements Initializable {

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<Cart> panierTable;

    @FXML
    private Button updateBtn;

    @FXML
    private TableColumn<Cart, Book> bookCol;

    @FXML
    private TableView<Cart> cartTable;

    @FXML
    private TableColumn<Cart, Float> priceCol;

    @FXML
    private TableColumn<Cart, Integer> quantityCol;

    @FXML
    private Label totalLabel;

    @FXML
    private TextField usernameinput;
    private String currentUser;

    public void setUsername(String username) {
        //getCurrentUser.setText(username);
        currentUser = username;
        currentUser = UserSession.getCurrentUsername();
        //  usernameinput.setText(currentUser);

    }

    public void table() {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ObservableList<Cart> carts = FXCollections.observableArrayList();
        try {

            PreparedStatement outerStatement = connection.prepareStatement("SELECT c.*, b.title, b.author, b.price, u.username "
                    + "FROM panier c "
                    + "JOIN livre b ON c.book_id = b.ref "
                    + "JOIN user u ON u.username = c.user_id");
            ResultSet outerResultSet = outerStatement.executeQuery();
            {
                while (outerResultSet.next()) {
                    PreparedStatement pst = connection.prepareStatement("SELECT * from livre where ref=?");
                    pst.setString(1, outerResultSet.getString("book_id"));
                    ResultSet rs1 = pst.executeQuery();
                    System.out.println("book_id " + outerResultSet.getString("book_id"));
                    while (rs1.next()) {

                        //Cart cat = new Cart(outerResultSet.getString("user_id"), outerResultSet.getInt("quantity"), outerResultSet.getFloat("total"));
                        System.out.println("fdsedsqd " + rs1.getString("ref"));
                        Book book = new Book();
                        book.setRef(rs1.getString("ref"));
                        book.setTitle(rs1.getString("title"));
                        book.setAuthor(rs1.getString("author"));

                        // cat.addBook(book);
                        Cart cat = new Cart(outerResultSet.getString("user_id"), outerResultSet.getInt("quantity"), outerResultSet.getFloat("total"), book);

                        carts.add(cat);

                    }
                }
            }
            panierTable.setItems(carts);
            //bookCol.setCellValueFactory(new PropertyValueFactory<Cart, String>("book"));
            priceCol.setCellValueFactory(new PropertyValueFactory<Cart, Float>("total"));
            quantityCol.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("quantity"));

            bookCol.setCellValueFactory(cellData -> {
                List<Book> books = cellData.getValue().getBooks();
                return new SimpleObjectProperty<>(books != null && !books.isEmpty() ? books.get(0) : null);
            });

            bookCol.setCellFactory(column -> new TableCell<Cart, Book>() {
                @Override
                protected void updateItem(Book book, boolean empty) {
                    super.updateItem(book, empty);

                    if (empty || book == null) {

                        setText("null");
                    } else {
                        setText(book.getTitle() + " by " + book.getAuthor());
                    }
                }
            });

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String currentUser = UserSession.getCurrentUsername();
        System.out.println("current user from list panier " + currentUser);

        table();
    }

}
