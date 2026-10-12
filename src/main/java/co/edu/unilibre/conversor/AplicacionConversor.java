package co.edu.unilibre.conversor;

import co.edu.unilibre.conversor.controlador.ConversorControlador;
import co.edu.unilibre.conversor.vista.ConversorVista;

import javafx.application.Application;
import javafx.stage.Stage;

public class AplicacionConversor extends Application {

    @Override
    public void start(Stage escenario) {
        ConversorVista vista = new ConversorVista();
        new ConversorControlador(vista);

        escenario.setScene(vista.getEscena());
        escenario.setTitle("Conversor Numerico");
        escenario.setMinWidth(440);
        escenario.setMinHeight(480);
        escenario.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
