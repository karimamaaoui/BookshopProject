/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class User {
    
    private final StringProperty username;
    private final StringProperty phone;
    private final StringProperty password;
    private final StringProperty  email;
     
    public User()
    {
        username = new SimpleStringProperty(this,"username");
        email = new SimpleStringProperty(this, "email");
        phone = new SimpleStringProperty(this, "phone");
        password = new SimpleStringProperty(this, "password");
    }

    public StringProperty usernameProperty() { 
         return username; 
    }
    public String getUsername() { 
        return username.get();
    }
    public void setUsername(String newname) { username.set(newname); }

    public StringProperty emailProperty() { 
        return email; 
    }
    
    public String getEmail() { 
        return email.get();
    }
    
    public void setEmail(String newEmail) { 
        email.set(newEmail);
    }

    public StringProperty phoneProperty() {
        return phone; 
    }
    
    public String getPhone() { 
        return phone.get(); 
    }
    
    public void setPhone(String newPhone) {
        phone.set(newPhone);
    }
    
    public StringProperty passwordProperty() { 
        return password; 
    }
    
    
    public String getPassword() { 
        return password.get();
    }
    
    public void setPassword(String newPassword) { 
        password.set(newPassword);
    }


}