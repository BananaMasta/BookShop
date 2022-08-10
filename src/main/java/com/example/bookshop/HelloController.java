package com.example.bookshop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController {

    public static int id = 0;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label authorizField;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passWordField;

    @FXML
    private Button regButton;

    @FXML
    private Button signButton;

    public void switchToRegistration(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("registartion-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToBookStore(String str) throws IOException {//функция для открытия страницы
        signButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(str));
        fxmlLoader.load();
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    void initialize() {
//        if (loginTextField.getText().isEmpty() || passWordField.getText().isEmpty()) {
//            loginTextField.setPromptText("Enter Login");
//            passWordField.setPromptText("Enter Password");
//            signButton.setDisable(true);
//        } else {
//            signButton.setDisable(false);
            signButton.setOnAction(x -> {
                try {
                    if (DataBaseConnection.checkUserInfo(loginTextField.getText(), passWordField.getText(), "Admin")) {
                        try {
                            switchToBookStore("admin-panel-view.fxml");
                            id = DataBaseConnection.getId(loginTextField.getText(), passWordField.getText());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (DataBaseConnection.checkUserInfo(loginTextField.getText(), passWordField.getText(), "User")) {
                        switchToBookStore("client-page-view.fxml");
                        id = DataBaseConnection.getId(loginTextField.getText(), passWordField.getText());
                    }
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            });
//        }
    }
}


