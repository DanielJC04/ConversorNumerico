package co.edu.unilibre.conversor.modelo;

public enum SistemaNumerico {

    BINARIO(2, "01"),
    OCTAL(8, "01234567"),
    DECIMAL(10, "0123456789"),
    HEXADECIMAL(16, "0123456789ABCDEF");

    private final int base;
    private final String caracteresValidos;

    SistemaNumerico(int base, String caracteresValidos) {
        this.base = base;
        this.caracteresValidos = caracteresValidos;
    }

    public int getBase() {
        return base;
    }

    public boolean esCaracterValido(char caracter) {
        return caracteresValidos.indexOf(Character.toUpperCase(caracter)) != -1;
    }

    public int valorDeCaracter(char caracter) {
        return caracteresValidos.indexOf(Character.toUpperCase(caracter));
    }

    public char caracterDeValor(int valor) {
        return caracteresValidos.charAt(valor);
    }
}
