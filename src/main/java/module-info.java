module com.example.primera_aplicacion_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    opens com.example.primera_aplicacion_javafx to javafx.fxml;
    exports com.example.primera_aplicacion_javafx;
    exports com.example.primera_aplicacion_javafx.modelo;
    opens com.example.primera_aplicacion_javafx.modelo to javafx.fxml;
}