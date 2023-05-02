package controllers;

import connectivity.ConnectionClass;
import java.awt.datatransfer.StringSelection;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Book;
import models.Category;
import models.Status;

public class BookFXMLController implements Initializable {

    @FXML
    private TextField Descriptioninput;

    @FXML
    private TextArea textArea;
    @FXML
    private TextField LanguageInput;

    @FXML
    private ComboBox<Status> StatusInput;

    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TextField authorInput;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> categoryColumn;

    @FXML
    private ImageView imgInput;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<Book, String> descriptionColumn;

    @FXML
    private TableColumn<Book, String> languageColumn;

    @FXML
    private TableColumn<Book, String> pagesColumn;

    @FXML
    private TextField pagesInput;

    @FXML
    private TableColumn<Book, Float> priceColumn;

    @FXML
    private TextField priceInput;

    @FXML
    private TableColumn<Book, String> refColumn;

    @FXML
    private TableColumn<Book, String> statusColumn;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TextField titleinput;

    @FXML
    private ComboBox<String> categoryInput;
    @FXML
    private TableColumn<Book, String> yearColumn;

     @FXML
    private TableColumn<Book,ImageView> imgColumn;

    @FXML
    private TextField yearInput;

    @FXML
    private TextField refInput;
    public int myIndex;

    private Image image;
    private Image imageVide;

    private File file;
    private FileInputStream fis;
    @FXML
    private TextField price;

    @FXML
    void showStatus(MouseEvent event) {

        ObservableList data = FXCollections.observableArrayList();

        try {
            System.out.println("data" + data.add(Status.disponbile));
            System.out.println("data" + data.add(Status.OutOfStock));

            StatusInput.setItems(data);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    void showCategory(MouseEvent event) {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        try {

            PreparedStatement pst = connection.prepareStatement("SELECT * FROM category");
            ResultSet rs = pst.executeQuery();
            ObservableList data = FXCollections.observableArrayList();

            {
                while (rs.next()) {
                    data.add(new String(rs.getString("label")));

                }
                categoryInput.setItems(data);
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }

    @FXML
    void addImage(MouseEvent event) {
        FileChooser fc = new FileChooser();
        Stage stage = new Stage();
        file = fc.showOpenDialog(stage);
        textArea.setText(file.getAbsolutePath());
        String selectedFile = file.getAbsolutePath();
        //System.out.println("selectedFile"+selectedFile);
        image = new Image(file.toURI().toString(), 100, 150, true, true);
        imgInput.setImage(image);
    }

    @FXML
    public void addClick(MouseEvent event) throws FileNotFoundException {
        String re, tit, descri, auth, lan;
        int pag, yr;
        String pri;

        re = refInput.getText();
        tit = titleinput.getText();
        descri = Descriptioninput.getText();
        auth = authorInput.getText();
        lan = LanguageInput.getText();
        pag = Integer.parseInt(pagesInput.getText());
        yr = Integer.parseInt(yearInput.getText());
        pri = price.getText();
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            PreparedStatement pst = connection.prepareStatement("INSERT INTO `livre`( `ref`,`title`, `description`, `status`, `category`, `Pages`, `Language`, `Year`, `price`, `author`,`image`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");

            pst.setString(1, re);
            pst.setString(2, tit);
            pst.setString(3, descri);
            pst.setString(4, StatusInput.getValue().toString());
            pst.setObject(5, categoryInput.getValue());
            pst.setInt(6, pag);
            pst.setString(7, lan);
            pst.setInt(8, yr);
            pst.setString(9, pri);
            pst.setString(10, auth);
            fis = new FileInputStream(file);
            pst.setBinaryStream(11, fis, file.length());

            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Book Registation");

            alert.setHeaderText("Book Registation");
            alert.setContentText("Record Addedddd!");

            alert.showAndWait();

            refInput.setText("");
            titleinput.setText("");
            Descriptioninput.setText("");
            authorInput.setText("");

            price.setText("");
            pagesInput.setText("");
            LanguageInput.setText("");
            yearInput.setText("");
            imgInput.setImage(imageVide);
            textArea.setText("");
            categoryInput.getSelectionModel().clearSelection();
            StatusInput.getSelectionModel().clearSelection();
            table();

        } catch (SQLException ex) {

            System.out.println(ex.toString());
        }

    }

    @FXML
    void deleteClick(MouseEvent event) {
        myIndex = bookTable.getSelectionModel().getSelectedIndex();
        String ref;
        ref = bookTable.getItems().get(myIndex).getRef();

        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            PreparedStatement pst = connection.prepareStatement("delete from livre where ref = ? ");
            pst.setString(1, ref);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Book Registationn");

            alert.setHeaderText("Book Registation");
            alert.setContentText("Deletedd!");

            alert.showAndWait();
            table();
               refInput.setText("");
            titleinput.setText("");
            Descriptioninput.setText("");
            price.setText("");
            pagesInput.setText("");
            LanguageInput.setText("");
            yearInput.setText("");
            authorInput.setText("");
            imgInput.setImage(imageVide);
            textArea.setText("");
            categoryInput.getSelectionModel().clearSelection();
            StatusInput.getSelectionModel().clearSelection();


        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }

    @FXML
    void updateBtn(MouseEvent event) {
        String re, tit, descri, auth, lan;
        String pag, yr;
        String pri, sat, cat;
        
        myIndex = bookTable.getSelectionModel().getSelectedIndex();

        re = bookTable.getItems().get(myIndex).getRef();
        tit = titleinput.getText();
        descri = Descriptioninput.getText();
        auth = authorInput.getText();
        lan = LanguageInput.getText();
        pag = pagesInput.getText();
        yr = yearInput.getText();
        pri = price.getText();
        sat = StatusInput.getValue().toString();
        cat = categoryInput.getValue().toString();
        try {

            ConnectionClass connectionClass = new ConnectionClass();
            Connection con = connectionClass.getConnection();

            PreparedStatement pst = con.prepareStatement("update livre set title = ?,description = ? ,author = ?, Pages=?, price=?, year=?,  Language =?,status=?,category=?  where ref = ? ");

            pst.setString(1, tit);
            pst.setString(2, descri);
            pst.setString(3, auth);
            pst.setString(4, pag);
            pst.setString(5, pri);
            pst.setString(6, yr);
            pst.setString(7, lan);
            pst.setString(8, sat);
            pst.setString(9, cat);

            pst.setString(10, re);

            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Book Registationn");

            alert.setHeaderText("Book Registation");
            alert.setContentText("Updateddd!");

            alert.showAndWait();
            table();
            refInput.setText("");
            titleinput.setText("");
            Descriptioninput.setText("");
            price.setText("");
            pagesInput.setText("");
            LanguageInput.setText("");
            yearInput.setText("");
            authorInput.setText("");
            imgInput.setImage(imageVide);
            textArea.setText("");
            categoryInput.getSelectionModel().clearSelection();
            StatusInput.getSelectionModel().clearSelection();

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void table() {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ObservableList<Book> livres = FXCollections.observableArrayList();
        imgColumn.setPrefWidth(100);
        ImageView imageView = new ImageView();

        try {
            PreparedStatement pst = connection.prepareStatement("SELECT ref,title,description,Pages,category,status,language, Year, price, author,image FROM livre");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next()) {
                    int i = 1;
                    
                    
                       byte[] imageData = rs.getBytes("image");
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageData);

    // Create an Image from the ByteArrayInputStream
    Image image = new Image(bis);

    // Set the Image as the source for an ImageView
    ImageView imageVi = new ImageView(image);
    imgInput.setImage(image);

                 
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
                    livre.setImage(rs.getString("image"));

                    livres.add(livre);
                }
            }

            bookTable.setItems(livres);
            refColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("ref"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

            titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("description"));
            pagesColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("Pages"));
            categoryColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("category"));

            statusColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("status"));

            languageColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("Language"));

            yearColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("Year"));
            authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
            imgColumn.setCellValueFactory(new PropertyValueFactory<Book, ImageView>("image"));
         //   imgColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
            
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        bookTable.setRowFactory(tv -> {
            TableRow<Book> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = bookTable.getSelectionModel().getSelectedIndex();
                    refInput.setText(bookTable.getItems().get(myIndex).getRef());
                    price.setText(Float.toString(bookTable.getItems().get(myIndex).getPrice()));
                    titleinput.setText(bookTable.getItems().get(myIndex).getTitle());
                    Descriptioninput.setText(bookTable.getItems().get(myIndex).getDescription());

                    authorInput.setText(bookTable.getItems().get(myIndex).getAuthor());
                    LanguageInput.setText(bookTable.getItems().get(myIndex).getLanguage());
                    pagesInput.setText(Integer.toString(bookTable.getItems().get(myIndex).getPages()));
                    yearInput.setText(Integer.toString(bookTable.getItems().get(myIndex).getYear()));

                    String staString = bookTable.getItems().get(myIndex).getStatus();
                    Status stau = Status.valueOf(staString);
                    StatusInput.setValue(stau);

                    String catString = bookTable.getItems().get(myIndex).getCategory();
                    categoryInput.setValue(catString);
                    
                }

            });
            return myRow;
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String input = change.getText();
            if (input.matches("[0-9]*")) {
                return change;
            }
            return null;
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        TextFormatter<String> textFormatter1 = new TextFormatter<>(filter);
        TextFormatter<String> textFormatter2 = new TextFormatter<>(filter);

        //   priceInput.setTextFormatter(textFormatter);
        pagesInput.setTextFormatter(textFormatter1);
        yearInput.setTextFormatter(textFormatter2);

    }

}
