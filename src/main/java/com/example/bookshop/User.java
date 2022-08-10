package com.example.bookshop;

public class User {
    int id;
    String login;
    String mail;
    String userName;
    String password;
    String country;
    String repassword;
    String role;

    public User(int id, String mail, String login, String userName, String country, String password, String role) {
        this.id = id;
        this.login = login;
        this.mail = mail;
        this.userName = userName;
        this.password = password;
        this.country = country;
        this.role = role;
    }

    public User(String mail, String login, String userName, String country, String password, String repassword) {
        this.login = login;
        this.mail = mail;
        this.userName = userName;
        this.password = password;
        this.country = country;
        this.repassword = repassword;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setName(String login) {
        this.login = login;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toString() {
        return getUserName() + " " + getMail() + " " + getCountry() + " " + getRole();
    }
}
