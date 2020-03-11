package br.com.unoesc.pb.transferenciacompacito.controllers.dto;

import br.com.unoesc.pb.transferenciacompacito.models.Transferencia;

public class TransferenciaDto {
    private String origem;

    private String destino;

    private Integer valor;

    public TransferenciaDto(Transferencia transferencia) {
        this.origem = transferencia.getUsuarioOrigem().getNome();
        this.destino = transferencia.getUsuarioDestino().getNome();
        this.valor = transferencia.getValor();
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public Integer getValor() {
        return valor;
    }
}
