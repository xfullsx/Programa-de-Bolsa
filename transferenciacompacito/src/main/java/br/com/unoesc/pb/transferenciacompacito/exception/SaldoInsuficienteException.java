package br.com.unoesc.pb.transferenciacompacito.exception;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException(String msg) {
        super(msg);
    }
}
