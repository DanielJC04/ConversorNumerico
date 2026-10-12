package co.edu.unilibre.conversor.dominio;

import co.edu.unilibre.conversor.modelo.SistemaNumerico;

public class ConversorNumerico {

    public long aDecimal(String texto, SistemaNumerico sistema) {
        long acumulado = 0;
        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            int valorDigito = sistema.valorDeCaracter(caracter);
            acumulado = acumulado * sistema.getBase() + valorDigito;
        }
        return acumulado;
    }

    public boolean cabeEnLong(String texto, SistemaNumerico sistema) {
        long acumulado = 0;
        for (int i = 0; i < texto.length(); i++) {
            int valorDigito = sistema.valorDeCaracter(texto.charAt(i));
            if (acumulado > (Long.MAX_VALUE - valorDigito) / sistema.getBase()) {
                return false;
            }
            acumulado = acumulado * sistema.getBase() + valorDigito;
        }
        return true;
    }

    public String desdeDecimal(long valor, SistemaNumerico sistema) {
        if (valor == 0) {
            return String.valueOf(sistema.caracterDeValor(0));
        }

        StringBuilder resultado = new StringBuilder();
        long restante = valor;
        while (restante > 0) {
            int residuo = (int) (restante % sistema.getBase());
            resultado.insert(0, sistema.caracterDeValor(residuo));
            restante = restante / sistema.getBase();
        }
        return resultado.toString();
    }

    public String convertir(String texto, SistemaNumerico origen, SistemaNumerico destino) {
        long valorDecimal = aDecimal(texto, origen);
        return desdeDecimal(valorDecimal, destino);
    }
}
