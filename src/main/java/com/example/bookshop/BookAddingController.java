package com.example.bookshop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookAddingController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addBookButton;

    @FXML
    private TextField bookAuthorTextField;

    @FXML
    private ComboBox<String> bookGenerComboBox;

    @FXML
    private TextField bookNameTextField;

    @FXML
    private TextField bookPriceTextField;

    @FXML
    private TextField numbersOfBookTextField;

    @FXML
    private TextField bookWebSite;

    @FXML
    private Button backButton;

    public void buttonToBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin-panel-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        ObservableList<String> bookGeners = FXCollections.observableArrayList("horror", "drama", "physic", "fantasy", "novel");
        bookGenerComboBox.setItems(bookGeners);
        addBookButton.setOnAction(x -> {
            Book book = new Book(bookAuthorTextField.getText(), bookNameTextField.getText(), bookGenerComboBox.getValue(),
                    Integer.parseInt(bookPriceTextField.getText()), Integer.parseInt(numbersOfBookTextField.getText()), bookWebSite.getText());
            try {
                DataBaseConnection.bookAdding(book);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }

}
