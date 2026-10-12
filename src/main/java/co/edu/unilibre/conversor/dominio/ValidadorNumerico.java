package co.edu.unilibre.conversor.dominio;

import co.edu.unilibre.conversor.modelo.SistemaNumerico;

public class ValidadorNumerico {

    public boolean esValido(String texto, SistemaNumerico sistema) {
        if (texto == null || texto.isEmpty()) {
            return false;
        }
        for (int i = 0; i < texto.length(); i++) {
            if (!sistema.esCaracterValido(texto.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
