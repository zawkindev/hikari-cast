module com.example.asuna {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.asuna to javafx.fxml;
    exports com.example.asuna;
}