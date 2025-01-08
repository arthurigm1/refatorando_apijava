package br.com.alura.adopet.api.Exception;

public class ValidacaoException extends RuntimeException {
    public ValidacaoException(String menssagem) {
        super(menssagem);
    }
}
