module com.example.primera_aplicacion_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.primera_aplicacion_javafx to javafx.fxml;
    exports com.example.primera_aplicacion_javafx;
}