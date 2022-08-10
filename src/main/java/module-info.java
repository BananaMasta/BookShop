module com.example.bookshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.example.bookshop to javafx.fxml;
    exports com.example.bookshop;
}