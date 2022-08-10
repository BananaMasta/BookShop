package com.example.bookshop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backToSign;

    @FXML
    private Button bookStoreButton;

    @FXML
    private Button personalPage;

    public void bookStoreClient(String str) throws IOException {
        bookStoreButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(str));
        fxmlLoader.load();
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void initialize(){
        backToSign.setOnAction(x -> {
            try {
                bookStoreClient("hello-view.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bookStoreButton.setOnAction(x -> {
            try {
                bookStoreClient("client-book-store-view.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        personalPage.setOnAction(x -> {
            try {
                bookStoreClient("client-personal-view.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}