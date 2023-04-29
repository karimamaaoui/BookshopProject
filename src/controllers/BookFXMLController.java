package controllers;

import connectivity.ConnectionClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

public class BookFXMLController implements Initializable{

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
    private TableView <Book> bookTable;

    @FXML
    private TableColumn<Book, String> categoryColumn;

    @FXML
    private ImageView imgInput;
    
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


    @FXML
    private TextField priceInput;

    @FXML
    private TableColumn <Book, String>  refColumn;

    @FXML
    private TableColumn <Book, String>  statusColumn;

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
    private TextField refInput;
    public int myIndex;
    
    
    private Image image;
    private Image imageVide;

    private File file;
    private FileInputStream fis ;
     
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
          PreparedStatement pst = connection.prepareStatement("SELECT ref,title,description,Pages,category,status,Language, Year, price, author FROM livre");  
           ResultSet rs = pst.executeQuery();
           {
            while (rs.next())
            {
            int i=1;

                Book livre = new Book();
                livre.setRef(rs.getString("ref"));   
                livre.setTitle(rs.getString("title"));
                livre.setDescription(rs.getString("description"));
                livre.setPages(rs.getInt("Pages"));
                livre.setCategory(rs.getString("category"));
                livre.setStatus(rs.getString("status"));
                livre.setLanguage(rs.getString("Language"));
                livre.setYear(rs.getInt("Year"));
                livre.setPrice(rs.getFloat("price"));
                livre.setAuthor(rs.getString("author"));

                livres.add(livre);
       }
    } 

                bookTable.setItems(livres);
                refColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("ref"));
        
                titleColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
                descriptionColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("description"));
                pagesColumn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("pages"));
                categoryColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("category"));

                statusColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("status"));
                
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
		 
		           refColumn.setText(bookTable.getItems().get(myIndex).getRef());

                           titleColumn.setText(bookTable.getItems().get(myIndex).getTitle());
		           descriptionColumn.setText(bookTable.getItems().get(myIndex).getDescription());
                           statusColumn.setText(bookTable.getItems().get(myIndex).getStatus());
                           
		        }
		     });
		        return myRow;
                   });
    
    
      }
   
    @FXML
    void addImage(MouseEvent event) {
        FileChooser fc=new FileChooser();
        Stage stage=new Stage();
        file=fc.showOpenDialog(stage);
        textArea.setText(file.getAbsolutePath());
        String selectedFile=file.getAbsolutePath();
        //System.out.println("selectedFile"+selectedFile);
        image =new Image(file.toURI().toString(),100,150,true,true);
        imgInput.setImage(image);
     }
    
  
       @FXML
    public void addClick(MouseEvent event) throws FileNotFoundException {
            String titleBook;
            titleBook = titleinput.getText();
         
            try 
        {  
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            
            
     
            PreparedStatement pst = connection.prepareStatement("INSERT INTO `livre`( `ref`,`title`, `description`, `status`, `category`, `Pages`, `Language`, `Year`, `price`, `author`,`image`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            
            pst.setString(1, refInput.getText());
            
            pst.setString(2, titleBook);
            pst.setString(3, Descriptioninput.getText());
            pst.setString(4,StatusInput.getValue().toString());
            pst.setObject(5, categoryInput.getValue());
            pst.setString(6, pagesInput.getText());
            pst.setString(7, LanguageInput.getText());
            pst.setString(8, yearInput.getText());
            pst.setString(9, priceInput.getText());
            pst.setString(10, authorInput.getText());
            fis=new FileInputStream(file);
            pst.setBinaryStream(11, fis,file.length());

            
            pst.executeUpdate();
            
            	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Book Registation");

		
		alert.setHeaderText("Book Registation");
		alert.setContentText("Record Addedddd!");

		alert.showAndWait();

            table();
           
           refInput.setText("");
            titleinput.setText("");
            Descriptioninput.setText("");
            priceInput.setText("");
            pagesInput.setText("");
            LanguageInput.setText("");
            yearInput.setText("");
            authorInput.setText("");
            imgInput.setImage(imageVide);
            textArea.setText("");
            categoryInput.getSelectionModel().clearSelection();
            StatusInput.getSelectionModel().clearSelection();
        } 
        catch (SQLException ex)
        {
            
            System.out.println(ex.toString());
        }
   
    }
 

    @FXML
    void deleteClick(MouseEvent event) {
     myIndex = bookTable.getSelectionModel().getSelectedIndex();
	String ref;	 
        ref = bookTable.getItems().get(myIndex).getRef();   
         
        try 
        {
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
            
        } 
        catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
  
    }

    @FXML
    void updateClick(MouseEvent event) {
    String title,desc,cat,sta,auth,lan,image,ref;
    int page,year;
    float price;
        
         myIndex = bookTable.getSelectionModel().getSelectedIndex();
         System.out.println("myindex "+myIndex);
            ref = bookTable.getItems().get(myIndex).getRef();   
            System.out.println("ref "+ref);
            title = titleinput.getText();

            desc = Descriptioninput.getText();
            cat = categoryInput.getSelectionModel().toString();
            sta=StatusInput.getSelectionModel().toString();
            auth=authorInput.getText();
            lan=LanguageInput.getText();
          //  page=(int)pagesInput.getText();
           // year=yearInput.getText();
            
        try 
        {
            
                ConnectionClass connectionClass = new ConnectionClass();
            Connection con = connectionClass.getConnection();
                  System.out.println("title "+title);

          PreparedStatement  pst = con.prepareStatement("update livre set title = ?,description = ?  where ref = ? ");
            pst.setString(1, ref);
            pst.setString(2, title);
            pst.setString(3, desc);
           // pst.setInt(4, page);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("User Registationn");

		
		alert.setHeaderText("User Registation");
		alert.setContentText("Updateddd!");

		alert.showAndWait();
                table();
            refInput.setText("");
            titleinput.setText("");
            Descriptioninput.setText("");
            priceInput.setText("");
            pagesInput.setText("");
            LanguageInput.setText("");
            yearInput.setText("");
            authorInput.setText("");
            imgInput.setImage(imageVide);
            textArea.setText("");
            categoryInput.getSelectionModel().clearSelection();
            StatusInput.getSelectionModel().clearSelection();
       
        } 
        catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
    }    
   

}
