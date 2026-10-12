package co.edu.unilibre.conversor.vista;

import co.edu.unilibre.conversor.modelo.SistemaNumerico;

import java.util.EnumMap;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ConversorVista {

    private final Map<SistemaNumerico, TextField> campos = new EnumMap<>(SistemaNumerico.class);
    private final Button botonLimpiar = new Button("LIMPIAR");
    private final Label mensaje = new Label(" ");
    private final Scene escena;

    public ConversorVista() {
        VBox tarjeta = new VBox(18, construirEncabezado(), separador(), construirFilas(), construirPie());
        tarjeta.getStyleClass().add("tarjeta");
        tarjeta.setMaxWidth(420);

        StackPane raiz = new StackPane(tarjeta);
        raiz.getStyleClass().add("raiz");
        raiz.setPadding(new Insets(28));

        escena = new Scene(raiz, 480, 500);
        escena.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    }

    public Scene getEscena() {
        return escena;
    }

    public TextField getCampo(SistemaNumerico sistema) {
        return campos.get(sistema);
    }

    public Button getBotonLimpiar() {
        return botonLimpiar;
    }

    public void mostrarMensaje(String texto) {
        mensaje.setText(texto);
    }

    public void limpiarMensaje() {
        mensaje.setText(" ");
    }

    private VBox construirEncabezado() {
        Label titulo = new Label("Conversor Numerico");
        titulo.getStyleClass().add("titulo");

        Label subtitulo = new Label("BINARIO   ·   OCTAL   ·   DECIMAL   ·   HEXADECIMAL");
        subtitulo.getStyleClass().add("subtitulo");

        return new VBox(4, titulo, subtitulo);
    }

    private Region separador() {
        Region linea = new Region();
        linea.getStyleClass().add("separador");
        VBox.setMargin(linea, new Insets(2, 0, 2, 0));
        return linea;
    }

    private VBox construirFilas() {
        VBox filas = new VBox(14);

        for (SistemaNumerico sistema : SistemaNumerico.values()) {
            Region punto = new Region();
            punto.getStyleClass().add("punto-acento");
            punto.setStyle("-fx-background-color: " + colorDe(sistema) + ";");

            Label etiqueta = new Label(capitalizar(sistema.name()));
            etiqueta.getStyleClass().add("etiqueta-campo");
            etiqueta.setMinWidth(92);

            HBox encabezadoFila = new HBox(8, punto, etiqueta);
            encabezadoFila.setAlignment(Pos.CENTER_LEFT);

            TextField campo = new TextField();
            campo.getStyleClass().add("campo-texto");
            campo.setPromptText(marcadorDe(sistema));
            campo.setTooltip(new Tooltip(textoDeAyuda(sistema)));

            campos.put(sistema, campo);

            HBox fila = new HBox(14, encabezadoFila, campo);
            fila.setAlignment(Pos.CENTER_LEFT);
            HBox.setHgrow(campo, Priority.ALWAYS);
            filas.getChildren().add(fila);
        }
        return filas;
    }

    private VBox construirPie() {
        botonLimpiar.getStyleClass().add("boton-limpiar");

        HBox filaBoton = new HBox(botonLimpiar);
        filaBoton.setAlignment(Pos.CENTER_RIGHT);

        mensaje.getStyleClass().add("mensaje-error");
        mensaje.setWrapText(true);

        VBox pie = new VBox(10, filaBoton, mensaje);
        VBox.setMargin(pie, new Insets(4, 0, 0, 0));
        return pie;
    }

    private String textoDeAyuda(SistemaNumerico sistema) {
        switch (sistema) {
            case BINARIO:
                return "Solo se permiten los digitos 0 y 1";
            case OCTAL:
                return "Solo se permiten los digitos 0 a 7";
            case DECIMAL:
                return "Solo se permiten los digitos 0 a 9";
            case HEXADECIMAL:
                return "Se permiten los digitos 0-9 y las letras A-F/a-f";
            default:
                return "";
        }
    }

    private String marcadorDe(SistemaNumerico sistema) {
        switch (sistema) {
            case BINARIO:
                return "Ej: 1100100";
            case OCTAL:
                return "Ej: 144";
            case DECIMAL:
                return "Ej: 100";
            case HEXADECIMAL:
                return "Ej: 64";
            default:
                return "";
        }
    }

    private String colorDe(SistemaNumerico sistema) {
        switch (sistema) {
            case BINARIO:
                return "#1F4E79";
            case OCTAL:
                return "#6C5B7B";
            case DECIMAL:
                return "#2E7D32";
            case HEXADECIMAL:
                return "#B45309";
            default:
                return "#333333";
        }
    }

    private String capitalizar(String texto) {
        return texto.charAt(0) + texto.substring(1).toLowerCase();
    }
}
