package com.example.primera_aplicacion_javafx;

import com.example.primera_aplicacion_javafx.excepcion.ErrorEnResultado;
import com.example.primera_aplicacion_javafx.modelo.CalculadoraDeCambio;
import com.example.primera_aplicacion_javafx.modelo.Exchange;
import com.example.primera_aplicacion_javafx.modelo.Resultado;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
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
    private TextField txtMontoOrigen;
//    @FXML
//    private TextField txtMontoDestino;
    @FXML
    private Label montoDestino;
    @FXML
    private ChoiceBox<String> cbCodOrigen;
    @FXML
    private ChoiceBox<String> cbCodDestino;
    @FXML
    private Label txtMsgValidacion;

    private List<String> resultados = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbCodOrigen.getItems().addAll("ARS","BRL","CLP","PYG","UYU","USD");
        cbCodDestino.getItems().addAll("ARS","BRL","CLP","PYG","UYU","USD");
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
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

    @FXML
    public void btConvertirClick(ActionEvent actionEvent) {
        String base = cbCodOrigen.getSelectionModel().getSelectedItem();
        System.out.println(base);
        String cambio = cbCodDestino.getSelectionModel().getSelectedItem();
        System.out.println(cambio);

        String montoBaseString = txtMontoOrigen.getText();

        int cantidadResultados = 0;
        txtMsgValidacion.setText("");

        Alert alert;

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String direccion = "https://v6.exchangerate-api.com/v6/ca50e60632206d99d58846b2/pair/"+
                base.toUpperCase()+"/"+cambio.toUpperCase();
        System.out.println(direccion);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            //System.out.println("Json CRUDO: "+json);


            if (json.contains("<html>")||(json.contains("error"))) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error validación...");
                alert.setHeaderText("Datos ingresados incorrectos");
                alert.setContentText("Debe introducir el origen y destino de monedas");
                alert.showAndWait();
                cbCodOrigen.requestFocus();
            }

            Exchange intercambio = gson.fromJson(json, Exchange.class);
            //System.out.println(intercambio);

            double montoBase = Double.parseDouble(montoBaseString);

            Resultado paraCalcular = new Resultado(intercambio);
            CalculadoraDeCambio calcular = new CalculadoraDeCambio();
            calcular.calculaIntercambio(montoBase, paraCalcular);

            var montoIntercambio = String.format("%.2f", calcular.getMontoIntercambio());
            System.out.println(montoIntercambio);

            montoDestino.setText(montoIntercambio);
            //TextField txtMontoDestino = new TextField(montoIntercambio);

            Resultado miresultado = new Resultado(intercambio);

            cantidadResultados++;

//            System.out.println("-------------------------------------------");
//            System.out.println("Tasa de Conversión: 1 " + miresultado + " " + intercambio.conversion_rate());
//            System.out.println("-------------------------------------------");
//            System.out.println("Intercambio Nro: " + cantidadResultados);
//            System.out.println("-------------------------------------------");

            String registro = cantidadResultados + ". " + montoBase + " " + miresultado + " " + String.format("%.2f", calcular.getMontoIntercambio());
            System.out.println(registro);
            //System.out.println("-------------------------------------------");

            resultados.add(registro);

        }catch (ErrorEnResultado e){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error...");
            alert.setHeaderText("Mensage de error ...");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            //System.out.println(e.getMessage());
        }catch (NumberFormatException e){alert = new Alert(Alert.AlertType.WARNING);
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error...");
            alert.setHeaderText("Mensage de error ...");
            alert.setContentText("No ingresó un monto numérico válido");
            alert.showAndWait();
            txtMsgValidacion.setText("<-- Ingrese números");
            //System.out.println("_-= No ingresó un monto numérico válido =-_");
            //System.out.println(e.getMessage());
        }catch (ConnectException e){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error...");
            alert.setHeaderText("Mensage de error ...");
            alert.setContentText("Error de conexion");
            alert.showAndWait();
            //System.out.println("_-= Error de conexion =-_");
        } catch (IOException | InterruptedException e) {
            //throw new RuntimeException(e);
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error...");
            alert.setHeaderText("Mensage de error ...");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void btListadoConversionesClick(ActionEvent actionEvent) {
        Alert alert;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Lista de Conversiones...");
        alert.setHeaderText("Lista de conversiones");
        alert.setContentText(resultados.toString());
        alert.showAndWait();
    }

}