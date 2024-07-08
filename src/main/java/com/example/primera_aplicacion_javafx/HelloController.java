package com.example.primera_aplicacion_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Optional;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField txtNombre; //Campo "Nombre" del formulario
    @FXML
    private TextField txtApellidos; //Campo "Apellidos" del formulario

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    //Evento click en botón "Enviar"
    //Obtenemos los datos de los Textfield y los mostramos en un mensaje
    public void btEnviarClick(ActionEvent actionEvent) {
        //Obtenemos el nombre y apellidos introducidos por
        //el usuario en los campos TextField de la ventana gráfica
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();

        //Mostramos el nombre introducido en la consola (solo para depuración)
        System.out.println("Nombre: [" + nombre + "]");
        //Mostramos los apellidos introducidos en la consola (solo para depuración)
        System.out.println("Apellidos: " + apellidos + "]");

        //Si el usuario ha introducido nombre y apellidos los mostramos en un mensaje
        //Si falta algún dato le mostramos un mensaje indicándolo
        Alert alert;
        if (nombre.isEmpty() || apellidos.isEmpty()) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Faltan datos...");
            alert.setHeaderText("Faltan datos por introducir en el formulario.");
            alert.setContentText("Debe introducir el nombre y los apellidos.");
            txtNombre.requestFocus();
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Datos introducidos en el formulario...");
            alert.setHeaderText("Ha introducido correctamente los " +
                    "datos en el formulario. Se muestran a continuación.");
            alert.setContentText("Nombre: " + nombre +
                    System.lineSeparator() + "Apellidos: " + apellidos);
        }
        alert.showAndWait();
    }

    //Evento click en botón "Limpiar"
    //Vaciaremos los valores introducidos en los TextField
    public void btLimpiarClick(ActionEvent actionEvent) {
        txtApellidos.setText("");
        txtNombre.setText("");
        txtNombre.requestFocus();
    }

    //Evento click en botón "Salir"
    //Saldremos de la aplicación solicitando confirmación
    public void btSalirClick(ActionEvent actionEvent) {
        //Mostramos mensaje de confirmación para cerrar la aplicación
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar aplicación...");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea cerrar la aplicación?");
        Optional<ButtonType> result = alert.showAndWait();
        //Si se ha pulsado el botón "Aceptar"
        if (result.get() == ButtonType.OK){
            System.exit(0);
        } else { //Si se ha pulsado el botón "Cancelar" enfocamos en el TextField Nombre
            txtNombre.requestFocus();
        }
    }

}