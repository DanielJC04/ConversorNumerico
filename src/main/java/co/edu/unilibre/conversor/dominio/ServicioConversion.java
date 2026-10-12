package co.edu.unilibre.conversor.dominio;

import co.edu.unilibre.conversor.excepcion.EntradaInvalidaException;
import co.edu.unilibre.conversor.modelo.Equivalencia;
import co.edu.unilibre.conversor.modelo.SistemaNumerico;

public class ServicioConversion {

    private final ValidadorNumerico validador = new ValidadorNumerico();
    private final ConversorNumerico conversor = new ConversorNumerico();

    public Equivalencia calcularEquivalencia(String texto, SistemaNumerico origen) throws EntradaInvalidaException {
        if (texto == null || texto.isEmpty()) {
            throw new EntradaInvalidaException("Debe ingresar una cantidad en alguno de los sistemas numericos.");
        }
        if (!validador.esValido(texto, origen)) {
            throw new EntradaInvalidaException("El valor \"" + texto + "\" no es valido en sistema " + origen + ".");
        }

        if (!conversor.cabeEnLong(texto, origen)) {
            throw new EntradaInvalidaException("El valor ingresado es demasiado grande. El maximo soportado es 9223372036854775807 en decimal.");
        }

        long valorDecimal = conversor.aDecimal(texto, origen);

        return new Equivalencia(
                conversor.desdeDecimal(valorDecimal, SistemaNumerico.BINARIO),
                conversor.desdeDecimal(valorDecimal, SistemaNumerico.OCTAL),
                conversor.desdeDecimal(valorDecimal, SistemaNumerico.DECIMAL),
                conversor.desdeDecimal(valorDecimal, SistemaNumerico.HEXADECIMAL)
        );
    }
}
