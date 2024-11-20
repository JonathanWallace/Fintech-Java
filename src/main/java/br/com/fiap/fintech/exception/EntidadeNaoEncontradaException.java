package br.com.fiap.fintech.exception;

public class EntidadeNaoEncontradaException extends Exception{
    public EntidadeNaoEncontradaException() {
        super();
    }

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }

    public EntidadeNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntidadeNaoEncontradaException(Throwable cause) {
        super(cause);
    }

    protected EntidadeNaoEncontradaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
