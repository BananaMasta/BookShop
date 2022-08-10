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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookStoreController {

    public ObservableList<Book> data = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Book, String> bookAuthorTable;

    @FXML
    private TableColumn<Book, String> bookGenerTable;

    @FXML
    private TableColumn<Book, String> bookNameTable;

    @FXML
    private TableColumn<Book, Integer> bookNuberStoreTable;

    @FXML
    private TableColumn<Book, Integer> bookPriceTable;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Book> bookStoreTable;

    public void buttonToBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin-panel-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void addButtonToTable() {//функция добавления кнопки в таблицу
        TableColumn<Book, Void> colBtn = new TableColumn("Delete book");
        ObservableList<Book> newBookList = FXCollections.observableArrayList();
        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<TableColumn<Book, Void>, TableCell<Book, Void>>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                final TableCell<Book, Void> cell = new TableCell<Book, Void>() {
                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Book book = getTableView().getItems().get(getIndex());
                            try {
                                DataBaseConnection.deleteBookParent(book.getId());
                                DataBaseConnection.deleteBook(book.getId());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            System.out.println(book);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        bookStoreTable.getColumns().add(colBtn);
    }


    public void initialize() throws SQLException {
        data.clear();
        data.addAll(DataBaseConnection.bookInToTheTable());
        bookAuthorTable.setCellValueFactory(new PropertyValueFactory<>("author"));
        bookNameTable.setCellValueFactory(new PropertyValueFactory<>("name"));
        bookGenerTable.setCellValueFactory(new PropertyValueFactory<>("gener"));
        bookPriceTable.setCellValueFactory(new PropertyValueFactory<>("price"));
        bookNuberStoreTable.setCellValueFactory(new PropertyValueFactory<>("number"));
        bookStoreTable.setItems(data);
        addButtonToTable();
    }
}
