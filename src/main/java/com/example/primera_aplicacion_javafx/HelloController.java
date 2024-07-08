package com.example.primera_aplicacion_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelloController implements Initializable{
    @FXML
    private Label welcomeText;
    @FXML
    private TextField txtNombres; //Campo "Nombre" del formulario
    @FXML
    private TextField txtApellidos; //Campo "Apellidos" del formulario
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    private  TextField txtMontoOrigen;
    @FXML
    private ChoiceBox<String> cbCodOrigen;
    @FXML
    private ChoiceBox<String> cbCodDestino;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbCodOrigen.getItems().addAll("ARS","BRL","CLP","PYG","UYU","USD");
        cbCodDestino.getItems().addAll("ARS","BRL","CLP","PYG","UYU","USD");
    }

    //Evento click en botón "Enviar"
    //Obtenemos los datos de los Textfield y los mostramos en un mensaje
    public void btEnviarClick(ActionEvent actionEvent) {
        //Obtenemos el nombre y apellidos introducidos por
        //el usuario en los campos TextField de la ventana gráfica
        String nombre = txtNombres.getText();
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
            txtNombres.requestFocus();
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Datos introducidos en el formulario...");
            alert.setHeaderText("Ha introducido correctamente los " +
                    "datos en el formulario. Se muestran a continuación.");
            alert.setContentText("Nombres: " + nombre +
                    System.lineSeparator() + "Apellidos: " + apellidos);
        }
        alert.showAndWait();
    }

    //Evento click en botón "Limpiar"
    //Vaciaremos los valores introducidos en los TextField
    public void btLimpiarClick(ActionEvent actionEvent) {
        txtApellidos.setText("");
        txtNombres.setText("");
        txtNombres.requestFocus();
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
            txtNombres.requestFocus();
        }
    }

    public void btSalir2Click(ActionEvent actionEvent) {
        //Mostramos mensaje de confirmación para cerrar la aplicación
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar aplicación...");
        alert.setHeaderText(null);
        alert.setContentText("           Gracias por utilizar nuestro" + "\n" +
                             "           CONVERSOR DE MONEDAS" + "\n" +
                             "    _by_______________________________AAF_");
        Optional<ButtonType> result = alert.showAndWait();
        //Si se ha pulsado el botón "Aceptar"
        if (result.get() == ButtonType.OK){
            System.exit(0);
        } else { //Si se ha pulsado el botón "Cancelar" enfocamos en el TextField Nombre
            txtMontoOrigen.requestFocus();
        }
    }

    public void btConvertirClick(ActionEvent actionEvent) {
        String origen = cbCodOrigen.getSelectionModel().getSelectedItem();
        System.out.println(origen);
        String destino = cbCodDestino.getSelectionModel().getSelectedItem();
        System.out.println(destino);

    }

}