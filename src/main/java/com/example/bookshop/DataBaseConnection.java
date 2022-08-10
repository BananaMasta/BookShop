package com.example.bookshop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class DataBaseConnection {

    public static void main(String[] args) throws SQLException {
        System.out.println(bookInToTheTable());
    }

    public static Connection connect() throws SQLException {
        final String url = "jdbc:mysql://localhost:3306/bookstore";
        final String login = "root";
        final String password = "1234";
        return DriverManager.getConnection(url, login, password);
    }

    public static void userCreated(User user) throws SQLException {
        PreparedStatement newUserCreate = connect().prepareStatement("INSERT INTO user (Login, Mail, UserName, Country, Password, role) values(?,?,?,?,?,?)");
        newUserCreate.setString(1, user.getLogin());
        newUserCreate.setString(2, user.getMail());
        newUserCreate.setString(3, user.getUserName());
        newUserCreate.setString(4, user.getCountry());
        newUserCreate.setString(5, user.getPassword());
        newUserCreate.setString(6, "User");
        newUserCreate.executeUpdate();
    }

    public static boolean checkUserInfo(String login, String password, String role) throws SQLException {
        PreparedStatement loginPasswordCheck = connect().prepareStatement("select * from user where Login = ? and Password = ? and role = ?");
        loginPasswordCheck.setString(1, login);
        loginPasswordCheck.setString(2, password);
        loginPasswordCheck.setString(3, role);
        ResultSet rs = loginPasswordCheck.executeQuery();
        while (rs.next()) {
            return true;
        }
        return false;
    }

    public static void bookAdding(Book book) throws SQLException {
        PreparedStatement newBookCreate = connect().prepareStatement("INSERT INTO book (bookauthor, bookname, bookgener, bookprice, numberofbook, bookSite) values(?,?,?,?,?,?)");
        newBookCreate.setString(1, book.getAuthor());
        newBookCreate.setString(2, book.getName());
        newBookCreate.setString(3, book.getGener());
        newBookCreate.setInt(4, book.getPrice());
        newBookCreate.setInt(5, book.getNumber());
        newBookCreate.setString(6, book.getSite());
        newBookCreate.executeUpdate();
    }

    public static ObservableList<Book> bookInToTheTable() throws SQLException {
        PreparedStatement bookTable = connect().prepareStatement("select * from book");
        ResultSet rs = bookTable.executeQuery();
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        while (rs.next()) {
            bookList.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getInt(5), rs.getInt(6)));
        }
        return bookList;
    }

    //    public static ObservableList<User> userInfo(int userId) throws SQLException {
//        PreparedStatement userInformation = connect().prepareStatement("select * from user where id = ?");
//        userInformation.setInt(1, userId);
//        ResultSet rs = userInformation.executeQuery();
//        ObservableList<User> users = FXCollections.observableArrayList();
//        while (rs.next()) {
//            users.add(new User(rs.getString(1), rs.getString(2), rs.getString(3),
//                    rs.getString(4), rs.getString(5), rs.getString(6)));
//        }
//        return users;
//    }

    public static ObservableList<String> userInfoName(int userId) throws SQLException {
        PreparedStatement userInformation = connect().prepareStatement("select * from user where id = ?");
        userInformation.setInt(1, userId);
        ResultSet rs = userInformation.executeQuery();
        ObservableList<String> name = FXCollections.observableArrayList();
        while (rs.next()) {
            name.add(rs.getString(5));
        }
        return name;
    }

    public static ObservableList<String> userInfoMail(int userId) throws SQLException {
        PreparedStatement userInformation = connect().prepareStatement("select * from user where id = ?");
        userInformation.setInt(1, userId);
        ResultSet rs = userInformation.executeQuery();
        ObservableList<String> name = FXCollections.observableArrayList();
        while (rs.next()) {
            name.add(rs.getString(2));
        }
        return name;
    }

    public static ObservableList<String> userInfoCountry(int userId) throws SQLException {
        PreparedStatement userInformation = connect().prepareStatement("select * from user where id = ?");
        userInformation.setInt(1, userId);
        ResultSet rs = userInformation.executeQuery();
        ObservableList<String> books = FXCollections.observableArrayList();
        while (rs.next()) {
            books.add(rs.getString(4));
        }
        return books;
    }

    public static ObservableList<String> userInfoRole(int userId) throws SQLException {
        PreparedStatement userInformation = connect().prepareStatement("select * from user where id = ?");
        userInformation.setInt(1, userId);
        ResultSet rs = userInformation.executeQuery();
        ObservableList<String> books = FXCollections.observableArrayList();
        while (rs.next()) {
            books.add(rs.getString(7));
        }
        return books;
    }

    public static void addingInToTheShoppingCart(int userId, int bookId) throws SQLException {
        if (checkShoppingCart(userId, bookId)) {
            PreparedStatement bookUpdate = connect().prepareStatement("update usersbooks set count = count + 1 where userId = ? and bookId = ?");
            bookUpdate.setInt(1, userId);
            bookUpdate.setInt(2, bookId);
            bookUpdate.executeUpdate();
        } else {
            PreparedStatement shoppingList = connect().prepareStatement("insert into usersbooks (userId, bookId, count) values(?,?,?)");

            shoppingList.setInt(1, userId);
            shoppingList.setInt(2, bookId);
            shoppingList.setInt(3, 1);
            shoppingList.executeUpdate();
        }
    }

    public static int getId(String login, String password) throws SQLException {
        PreparedStatement loginPasswordCheck = connect().prepareStatement("select id from user where Login = ? and Password = ? ");
        loginPasswordCheck.setString(1, login);
        loginPasswordCheck.setString(2, password);
        ResultSet rs = loginPasswordCheck.executeQuery();
        while (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public static boolean checkShoppingCart(int userId, int bookId) throws SQLException {
        PreparedStatement checkBook = connect().prepareStatement("select * from usersbooks where userId = ? and bookId = ?");
        checkBook.setInt(1, userId);
        checkBook.setInt(2, bookId);
        ResultSet rs = checkBook.executeQuery();
        while (rs.next()) {
            return true;
        }
        return false;
    }

    public static void decrimentNumberOfBooks(int bookId) throws SQLException {
        PreparedStatement decrNumberBooks = connect().prepareStatement("update book set numberofbook = numberofbook - 1 where id = ?");
        decrNumberBooks.setInt(1, bookId);
        decrNumberBooks.executeUpdate();
    }

    public static ObservableList<String> purchaseHistory(int id) throws SQLException {
        PreparedStatement purchHistory = connect().prepareStatement("Select * from book, usersbooks where book.id = usersbooks.bookId and usersbooks.userId = ?");
        purchHistory.setInt(1, id);
        ResultSet rs = purchHistory.executeQuery();
        ObservableList<String> books = FXCollections.observableArrayList();
        while (rs.next()) {
            books.add(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
        }
        return books;
    }

    public static void goToSite(int bookId) throws SQLException, IOException, URISyntaxException {
        PreparedStatement bookSite = connect().prepareStatement("select bookSite from book where id = ?");
        bookSite.setInt(1, bookId);
        ResultSet rs = bookSite.executeQuery();
        ObservableList<String> site = FXCollections.observableArrayList();
        while (rs.next()) {
            site.add(rs.getString(1));
        }
        Desktop.getDesktop().browse(new URI(site.get(0)));
    }

    public static void deleteBookParent(int booksId) throws SQLException {
        PreparedStatement bookDelete = connect().prepareStatement("delete from usersbooks where bookId = ?");
        bookDelete.setInt(1, booksId);
        bookDelete.executeUpdate();
    }

    public static void deleteBook(int bookId) throws SQLException {
        PreparedStatement bookDelete = connect().prepareStatement("delete from book where id = ?");
        bookDelete.setInt(1, bookId);
        bookDelete.executeUpdate();
    }
}
