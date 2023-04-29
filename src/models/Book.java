/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import models.Status;

/**
 *
 * @author user
 */

public class Book {
    
    
    private StringProperty title,description,Language,author;
    public StringProperty status;
    private int pages,year;
    private float price;
    private StringProperty category,ref,image;
    
    public Book(){
        ref = new SimpleStringProperty(this,"ref");
        title = new SimpleStringProperty(this,"title");
        description = new SimpleStringProperty(this,"description");
        Language = new SimpleStringProperty(this,"Language");
        author = new SimpleStringProperty(this,"author");
        category=new SimpleStringProperty(this,"category");
        status= new SimpleStringProperty(this,"status");
        image= new SimpleStringProperty(this,"image");


    }
    public StringProperty titleProperty() { 
         return title; 
    }
    
    public StringProperty imageProperty() { 
         return image; 
    }
    
    public StringProperty categoryProperty() { 
         return category; 
    }

        public StringProperty descriptionProperty() { 
         return description; 
    }
        public StringProperty refProperty() { 
         return ref; 
    }
        
        public StringProperty LanguageProperty() { 
         return Language; 
    }
        
        public StringProperty authorProperty() { 
         return author; 
    }

    public StringProperty statusProperty() { 
         return status; 
    }

    

    public String getTitle() {
        return this.title.get();

    }

    public void setTitle(String newtitle) {
        title.set(newtitle);
    }
  
      public String getImage() {
        return this.image.get();

    }

    public void setImage(String newImage) {
        image.set(newImage);
    }
  
      public String getRef() {
        return this.ref.get();

    }

    public void setRef(String newRef) {
        ref.set(newRef);
    }
  
    
    public String getCategory() {
        return this.category.get();

    }

    public void setCategory(String newCategory) {
        category.set(newCategory);
    }

     public String getStatus() {
        return this.status.get();

    }

    public void setStatus(String newStat) {
        status.set(newStat);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getLanguage() {
        return Language.get();
    }

    public void setLanguage(String Language) {
        this.Language.set(Language);
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

  
    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    
    
    
}
