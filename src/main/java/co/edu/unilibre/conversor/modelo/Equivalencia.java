package co.edu.unilibre.conversor.modelo;

public class Equivalencia {

    private final String binario;
    private final String octal;
    private final String decimal;
    private final String hexadecimal;

    public Equivalencia(String binario, String octal, String decimal, String hexadecimal) {
        this.binario = binario;
        this.octal = octal;
        this.decimal = decimal;
        this.hexadecimal = hexadecimal;
    }

    public String getBinario() {
        return binario;
    }

    public String getOctal() {
        return octal;
    }

    public String getDecimal() {
        return decimal;
    }

    public String getHexadecimal() {
        return hexadecimal;
    }
}
