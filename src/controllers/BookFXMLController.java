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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import models.Book;
import models.Category;
import models.Status;

public class BookFXMLController implements Initializable{

    @FXML
    private TextField Descriptioninput;

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
    private TableView <Book> bookTable;

    @FXML
    private TableColumn<Book, Category> categoryColumn;

    
    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn <Book, String> descriptionColumn;

    @FXML
    private TableColumn <Book, String>  languageColumn;

    @FXML
    private TableColumn <Book, Integer>  pagesColumn;

    @FXML
    private TextField pagesInput;

    @FXML
    private TableColumn <Book, Float>  priceColumn;

    public int myIndex;

    @FXML
    private TextField priceInput;

    @FXML
    private TableColumn <Book, Integer>  refColumn;

    @FXML
    private TableColumn <Book, Status>  statusColumn;

    @FXML
    private TableColumn <Book, String>  titleColumn;

    @FXML
    private TextField titleinput;

    @FXML
    private Button updateBtn;

    @FXML
    private ComboBox<Category> categoryInput;
    @FXML
    private TableColumn <Book, Integer>  yearColumn;

    @FXML
    private TextField yearInput;

     
    
   @FXML
    void showStatus(MouseEvent event) {
        
        ObservableList data=FXCollections.observableArrayList();

        try{
            System.out.println("data"+data.add(Status.disponbile));
            System.out.println("data"+data.add(Status.OutOfStock));
            
            StatusInput.setItems(data);
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }

    @FXML
    void showCategory(MouseEvent event) {

       
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

    try 
       {           
           
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM category");  
            ResultSet rs = pst.executeQuery();
            ObservableList data=FXCollections.observableArrayList();

           {
        while (rs.next())
        {
            data.add(new String(rs.getString("label")));
         
       }
        categoryInput.setItems(data);
           } 
            
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
        ObservableList<Book> livres = FXCollections.observableArrayList();

          try 
       {
          PreparedStatement pst = connection.prepareStatement("SELECT title, description, status, category, Pages, Language, Year, price, author FROM livre");  
           ResultSet rs = pst.executeQuery();
           {
            while (rs.next())
            {
            int i=1;

                Book livre = new Book();

                livre.setTitle(rs.getString("title"));
                livre.setDescription(rs.getString("description"));
                livre.setSatuts((Status)StatusInput.getValue());
                System.out.println("livre.getSatuts() "+livre.getSatuts());
                System.out.println("livre.get cat() "+livre.getCategory());
                livre.setCategory((Category)categoryInput.getValue());

                livre.setPages(rs.getInt("Pages"));
                livre.setLanguage(rs.getString("Language"));
                livre.setYear(rs.getInt("Year"));
                livre.setPrice(rs.getFloat("price"));
                livre.setAuthor(rs.getString("author"));
                livres.add(livre);
       }
    } 
                bookTable.setItems(livres);
        
                titleColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
                descriptionColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("description"));
                statusColumn.setCellValueFactory(new PropertyValueFactory<Book,Status>("status"));
                categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
                
                pagesColumn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("pages"));
                languageColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("Language"));

                yearColumn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("year"));
                priceColumn.setCellValueFactory(new PropertyValueFactory<Book,Float>("price"));
                authorColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
                
       }
       
       catch (SQLException ex) 
       {
           System.out.println(ex.toString());
          }

               bookTable.setRowFactory( tv -> {
		    TableRow<Book> myRow = new TableRow<>();
		     myRow.setOnMouseClicked (event -> 
		     {
		        if (event.getClickCount() == 1 && (!myRow.isEmpty()))
		        {
		            myIndex =  bookTable.getSelectionModel().getSelectedIndex();
		 
		           titleColumn.setText(bookTable.getItems().get(myIndex).getTitle());
		           descriptionColumn.setText(bookTable.getItems().get(myIndex).getDescription());
		           
                         
                           
		        }
		     });
		        return myRow;
                   });
    
    
      }
   
  
       @FXML
    public void addClick(MouseEvent event) {
        String titleBook;
            titleBook = titleinput.getText();
         
            try 
        {  
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            
            PreparedStatement pstCat = connection.prepareStatement("SELECT * FROM category");  
            ResultSet rs = pstCat.executeQuery();
            
            while (rs.next()){

            PreparedStatement pst = connection.prepareStatement("INSERT INTO `livre`( `title`, `description`, `status`, `category`, `Pages`, `Language`, `Year`, `price`, `author`) VALUES (?,?,?,?,?,?,?,?,?)");
            
            
            pst.setString(1, titleBook);
            pst.setString(2, Descriptioninput.getText());
            pst.setString(3,StatusInput.getValue().toString());
            pst.setObject(4, categoryInput.getValue());
            pst.setString(5, pagesInput.getText());
            pst.setString(6, LanguageInput.getText());
            pst.setString(7, yearInput.getText());
            pst.setString(8, priceInput.getText());
            pst.setString(9, authorInput.getText());


            pst.executeUpdate();
            
            	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Book Registation");

		
		alert.setHeaderText("Book Registation");
		alert.setContentText("Record Addedddd!");

		alert.showAndWait();

            table();
            }
            titleinput.setText("");
            Descriptioninput.setText("");
            priceInput.setText("");
            pagesInput.setText("");
            

        } 
        catch (SQLException ex)
        {
            
            System.out.println(ex.toString());
        }
   
    }
 

    @FXML
    void deleteClick(MouseEvent event) {

    }

    @FXML
    void updateClick(MouseEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
    }    
   

}
