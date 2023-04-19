
package connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionClass {
    
    public Connection connection;
    
    
    public Connection getConnection()
    {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookproject","root","");
            System.out.println("connection from connection class:"+connection);

        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to database", e);
        }
        catch(ClassNotFoundException e) {
            System.out.println("null");
        }
        return connection;
    }
}

