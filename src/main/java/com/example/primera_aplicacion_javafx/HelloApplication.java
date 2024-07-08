package com.example.primera_aplicacion_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 460, 600);
        stage.setTitle("Mi primera aplicación JavaFX y Hello!");
        stage.setScene(scene);
        stage.show();
    }

//    otro ejemplo
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        //Cargamos la escena (el formulario/ventana)
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        //Establecemos el título de la ventana
//        primaryStage.setTitle("ProyectoA - Mi primera aplicación JavaFX");
//        //Icono para el formulario
//        //primaryStage.getIcons().add(new Image("proyectoa_logotipo_corto_letra.png"));
//        //Establecemos el ancho y el alto
//        primaryStage.setScene(new Scene(root, 400, 200));
//        //Mostramos la ventana
//        primaryStage.show();
//    }

    public static void main(String[] args) {
        launch();
    }
}