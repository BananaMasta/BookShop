package com.example.bookshop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientBookStoreController {

    public ObservableList<Book> data = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButtonClient;

    @FXML
    private Button shoppingCart;

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
    private TableView<Book> bookStoreTable;

    private void addButtonToTable() {//функция добавления кнопки в таблицу
        TableColumn<Book, Void> colBtn = new TableColumn("Buy book");
        ObservableList<Book> newBookList = FXCollections.observableArrayList();
        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<TableColumn<Book, Void>, TableCell<Book, Void>>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                final TableCell<Book, Void> cell = new TableCell<Book, Void>() {
                    private final Button btn = new Button("Buy");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Book book = getTableView().getItems().get(getIndex());
                            try {
                                DataBaseConnection.addingInToTheShoppingCart(HelloController.id, book.getId());
                                DataBaseConnection.decrimentNumberOfBooks(book.getId());
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

    private void addButtonToTable2() {//функция добавления кнопки в таблицу
        TableColumn<Book, Void> colBtn = new TableColumn("Book site");
        ObservableList<Book> newBookList = FXCollections.observableArrayList();
        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                final TableCell<Book, Void> cell = new TableCell<>() {
                    private final Button siteBtn = new Button("To Site");

                    {
                        siteBtn.setOnAction(x -> {
                            Book book = getTableView().getItems().get(getIndex());
                            try {
                                DataBaseConnection.goToSite(book.getId());
                            } catch (SQLException | IOException | URISyntaxException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(siteBtn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        bookStoreTable.getColumns().add(colBtn);
    }

    public void buttonClient(String str) throws IOException {
        backButtonClient.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(str));
        fxmlLoader.load();
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void initialize() throws SQLException {
        backButtonClient.setOnAction(x -> {
            try {
                buttonClient("client-page-view.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        shoppingCart.setOnAction(x -> {
            try {
                buttonClient("shopping-cart-view.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        addingInToTheTable();
    }

    public void addingInToTheTable() throws SQLException {
        data.clear();
        data.addAll(DataBaseConnection.bookInToTheTable());
        bookAuthorTable.setCellValueFactory(new PropertyValueFactory<>("author"));
        bookNameTable.setCellValueFactory(new PropertyValueFactory<>("name"));
        bookGenerTable.setCellValueFactory(new PropertyValueFactory<>("gener"));
        bookPriceTable.setCellValueFactory(new PropertyValueFactory<>("price"));
        bookNuberStoreTable.setCellValueFactory(new PropertyValueFactory<>("number"));
        bookStoreTable.setItems(data);
        addButtonToTable();
        addButtonToTable2();
    }
}

