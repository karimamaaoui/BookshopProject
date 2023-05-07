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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Book;
import models.Category;

public class CategoryFXMLController implements Initializable {

    public int myIndex;
    @FXML
    private TextField idinput;
    @FXML
    private Button addBtn;

    @FXML
    private TableView<Category> catTable;

    @FXML
    private TableColumn<Category, String> labelColumn;

    @FXML
    private TextField labelinput;

    @FXML
    private TableColumn<Category, String> idColumn;

    @FXML
    public void addClick(MouseEvent event) {
        String labelcat;
        labelcat = labelinput.getText();

        try {
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
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }

    @FXML
    void deleteClick(MouseEvent event) {
        myIndex = catTable.getSelectionModel().getSelectedIndex();
        String lab;
        lab = catTable.getItems().get(myIndex).getLabel();

        try {
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

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }

    @FXML
    void updateClick(MouseEvent event) throws SQLException {
        int id;

        myIndex = catTable.getSelectionModel().getSelectedIndex();

        id = catTable.getItems().get(myIndex).getId();

        System.out.println("id " + id);

        String lab = labelinput.getText();
        // System.out.println("label "+label);
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection con = connectionClass.getConnection();
            PreparedStatement pstCat = con.prepareStatement("SELECT id FROM category where label=?");
            pstCat.setString(1, lab);
            // System.out.println("label from 140 "+label);
            ResultSet rs = pstCat.executeQuery();

            if (rs.next()) {

                  Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Category Registration");
            alert.setHeaderText("Category Registration");
            alert.setContentText("A category with the label " + lab + " already exists!");

            alert.showAndWait();
            }
            else {
                PreparedStatement pst = con.prepareStatement("UPDATE `category` SET label=? WHERE id=? ");
                System.out.println("id from rs.get" + pst);

                pst.setString(1, lab);
                pst.setInt(2, id);
            
                pst.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Category Registationn");

                alert.setHeaderText("Category Registation");
                alert.setContentText("Updateddd!");

                alert.showAndWait();
            
            }
            table();

            labelinput.setText("");
            idinput.setText("");

        } catch (SQLException ex) {
            System.out.println("catch update");
            System.out.println(ex.toString());
        }

    }

    public void table() {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ObservableList<Category> categories = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM category");
            ResultSet rs = pst.executeQuery();

            {
                while (rs.next()) {
                    Category cat = new Category();
                    cat.setId(rs.getInt("id"));

                    cat.setLabel(rs.getString("label"));

                    categories.add(cat);
                }
            }
            catTable.setItems(categories);
            idColumn.setCellValueFactory(new PropertyValueFactory<Category, String>("id"));

            labelColumn.setCellValueFactory(f -> f.getValue().labelProperty());

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        catTable.setRowFactory(tv -> {
            TableRow<Category> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = catTable.getSelectionModel().getSelectedIndex();

                    labelinput.setText(catTable.getItems().get(myIndex).getLabel());
                    //  idColumn.setText(catTable.getItems().get(myIndex).getId());

                    String selectedId = String.valueOf(catTable.getItems().get(myIndex).getId());
                    idinput.setText(selectedId);

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
