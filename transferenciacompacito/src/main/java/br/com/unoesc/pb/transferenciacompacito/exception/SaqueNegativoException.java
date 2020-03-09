package br.com.unoesc.pb.transferenciacompacito.exception;

public class SaqueNegativoException extends RuntimeException {
    public SaqueNegativoException() {
    }

    public SaqueNegativoException(String message) {
        super(message);
    }
}
