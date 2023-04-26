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
    
    
    private static int ref=0;
    private StringProperty title,description,Language,author;
    public Status satuts;
    private int pages,year;
    private float price;
    private Category category;
    
    
    public Book(){
        this.ref++;
        this.satuts=Status.disponbile;
        title = new SimpleStringProperty(this,"title");
        description = new SimpleStringProperty(this,"description");
        Language = new SimpleStringProperty(this,"Language");
        author = new SimpleStringProperty(this,"author");

    }
    public StringProperty titleProperty() { 
         return title; 
    }

        public StringProperty descriptionProperty() { 
         return description; 
    }
        
        public StringProperty LanguageProperty() { 
         return Language; 
    }
        
        public StringProperty authorProperty() { 
         return author; 
    }

    
    public static int getRef() {
        return ref;
    }

    public static void setRef(int ref) {
        Book.ref = ref;
    }

    public String getTitle() {
        return this.title.get();

    }

    public void setTitle(String newtitle) {
        title.set(newtitle);
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

    public Status getSatuts() {
        return satuts;
    }

    public void setSatuts(Status satuts) {
        this.satuts = satuts;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    
    
    
}
