package com.example.bookshop;

public class Book {
    private int id;
    private String author;
    private String name;
    private String gener;
    private int price;
    private int number;
    private String site;

    public Book(String author, String name, String gener, int price, int number, String site) {
        this.author = author;
        this.name = name;
        this.gener = gener;
        this.price = price;
        this.number = number;
        this.site = site;
    }

    public Book(int id, String author, String name, String gener, int price, int number) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.gener = gener;
        this.price = price;
        this.number = number;
    }

    public Book() {

    }

//    public Book(int id, String author, String name, String gener, int price, int number, String site) {
//        this.id = id;
//        this.author = author;
//        this.name = name;
//        this.gener = gener;
//        this.price = price;
//        this.number = number;
//        this.site = site;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGener() {
        return gener;
    }

    public void setGener(String gener) {
        this.gener = gener;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String toString() {
        return getId() + " " + getAuthor() + " " + getName() + " " + getGener() + " " + getPrice() + " " + getNumber() + " " + getSite();
    }
}
