package co.edu.unilibre.conversor.controlador;

import co.edu.unilibre.conversor.dominio.ServicioConversion;
import co.edu.unilibre.conversor.excepcion.EntradaInvalidaException;
import co.edu.unilibre.conversor.modelo.Equivalencia;
import co.edu.unilibre.conversor.modelo.SistemaNumerico;
import co.edu.unilibre.conversor.vista.ConversorVista;

import java.util.function.UnaryOperator;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;

public class ConversorControlador {

    private final ServicioConversion servicio = new ServicioConversion();
    private final ConversorVista vista;

    public ConversorControlador(ConversorVista vista) {
        this.vista = vista;
        for (SistemaNumerico sistema : SistemaNumerico.values()) {
            conectarCampo(sistema);
        }
        vista.getBotonLimpiar().setOnAction(evento -> limpiarCampos());
    }

    private void conectarCampo(SistemaNumerico sistema) {
        TextField campo = vista.getCampo(sistema);
        campo.setTextFormatter(new TextFormatter<>(filtroPara(sistema)));
        campo.setOnKeyPressed(evento -> {
            if (evento.getCode() == KeyCode.ENTER) {
                convertirDesde(sistema);
            }
        });
    }

    private UnaryOperator<TextFormatter.Change> filtroPara(SistemaNumerico sistema) {
        return cambio -> {
            String textoNuevo = cambio.getControlNewText();
            for (int i = 0; i < textoNuevo.length(); i++) {
                if (!sistema.esCaracterValido(textoNuevo.charAt(i))) {
                    return null;
                }
            }
            return cambio;
        };
    }

    private void convertirDesde(SistemaNumerico origen) {
        String texto = vista.getCampo(origen).getText();
        try {
            Equivalencia equivalencia = servicio.calcularEquivalencia(texto, origen);
            vista.getCampo(SistemaNumerico.BINARIO).setText(equivalencia.getBinario());
            vista.getCampo(SistemaNumerico.OCTAL).setText(equivalencia.getOctal());
            vista.getCampo(SistemaNumerico.DECIMAL).setText(equivalencia.getDecimal());
            vista.getCampo(SistemaNumerico.HEXADECIMAL).setText(equivalencia.getHexadecimal());
            vista.limpiarMensaje();
        } catch (EntradaInvalidaException e) {
            vista.mostrarMensaje(e.getMessage());
        }
    }

    private void limpiarCampos() {
        for (SistemaNumerico sistema : SistemaNumerico.values()) {
            vista.getCampo(sistema).setText("");
        }
        vista.limpiarMensaje();
    }
}
