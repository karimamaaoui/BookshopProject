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
public class Category {
    
     private final StringProperty label;
     private int id;
     
    public Category()
    {
        label = new SimpleStringProperty(this,"Category");
     }
public Category(String label) {
        this.label = new SimpleStringProperty(this, "label", label);
    }
    
    public StringProperty labelProperty() { 
         return label; 
    }
    public String getLabel() { 
        return label.get();
    }
    public void setLabel(String newLabel)
    { label.set(newLabel); }

    public static Category valueOf(String lab) {
        return new Category(lab.toString());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
