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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegistrationController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text regCountryTExt;

    @FXML
    private Text regLoginText;

    @FXML
    private TextField regLoginTextField;

    @FXML
    private Text regMailText;

    @FXML
    private TextField regMailTextField;

    @FXML
    private AnchorPane regPanel;

    @FXML
    private Text regPassText;

    @FXML
    private PasswordField regPassTextField;

    @FXML
    private Text regRePassText;

    @FXML
    private PasswordField regRePassTextField;

    @FXML
    private Button regRegistrButton;

    @FXML
    private Text regText;

    @FXML
    private Text regUserText;

    @FXML
    private TextField regUserTextField;

    @FXML
    private ComboBox<String> regcountryComboBox;

    @FXML
    private Button regReturnToSign;

    public void switchToSignIn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        ObservableList<String> countryList = FXCollections.observableArrayList("America", "Belarus", "Italy", "Germany", "Russia", "Ukraine");
        regcountryComboBox.setItems(countryList);
        regRegistrButton.setOnAction(x -> {
            User user = new User(regMailTextField.getText(), regLoginTextField.getText(), regUserTextField.getText(), regcountryComboBox.getValue(), regPassTextField.getText(), regRePassTextField.getText());
            if (regMailTextField.getText().trim().isEmpty()) {
                regMailTextField.setPromptText("Please enter mail");
            } else if (regLoginTextField.getText().trim().isEmpty()) {
                regLoginTextField.setPromptText("Please enter login");
            } else if (regUserTextField.getText().trim().isEmpty()) {
                regUserTextField.setPromptText("Please enter user name");
            } else if (regPassTextField.getText().trim().isEmpty()) {
                regPassTextField.setPromptText("Please enter password");
            } else if (regRePassTextField.getText().trim().isEmpty()) {
                regRePassTextField.setPromptText("Please enter re-password");
            } else {
                try {
                    DataBaseConnection.userCreated(user);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
