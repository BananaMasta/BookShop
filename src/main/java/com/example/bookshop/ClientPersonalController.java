package com.example.bookshop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientPersonalController {

    ObservableList<String> data = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button newInformationButton;

    @FXML
    private TextField personalCountry;

    @FXML
    private TextField personalMail;

    @FXML
    private TextField personalName;

    @FXML
    private TextField personalRole;

    @FXML
    private ListView<String> pusrshHistory;

    public void buttonClient(String str) throws IOException {
        backButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(str));
        fxmlLoader.load();
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void initialize() throws SQLException {

        backButton.setOnAction(x -> {
            try {
                buttonClient("client-page-view.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        newInformationButton.setOnAction(x -> {
            personalName.setEditable(true);
        });

        pusrshHistory.setItems(DataBaseConnection.purchaseHistory(HelloController.id));
//        data.add(String.valueOf(DataBaseConnection.userInfoName(HelloController.id)));
        personalName.setText(String.valueOf(DataBaseConnection.userInfoName(HelloController.id)));
        personalMail.setText(String.valueOf(DataBaseConnection.userInfoMail(HelloController.id)));
        personalCountry.setText(String.valueOf(DataBaseConnection.userInfoCountry(HelloController.id)));
        personalRole.setText(String.valueOf(DataBaseConnection.userInfoRole(HelloController.id)));
    }
}