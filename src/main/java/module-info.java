module com.example.homm3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;
    requires java.desktop;


    opens com.example.homm3 to javafx.fxml;
    exports com.example.homm3;
}