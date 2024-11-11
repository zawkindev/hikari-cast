module com.example.asuna {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.bytedeco.javacv;
    requires javafx.swing;


    opens com.example.asuna to javafx.fxml;
    exports com.example.asuna;
}