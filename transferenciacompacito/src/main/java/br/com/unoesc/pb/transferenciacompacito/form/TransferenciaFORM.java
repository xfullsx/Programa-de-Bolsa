package br.com.unoesc.pb.transferenciacompacito.form;

import javax.validation.constraints.NotNull;

public class TransferenciaFORM {
    @NotNull
    private Long idRemetente;
    @NotNull
    private Long idDestinatario;
    @NotNull
    private Integer valor;

    public Long getIdRemetente() {
        return idRemetente;
    }

    public void setIdRemetente(Long idRemetente) {
        this.idRemetente = idRemetente;
    }

    public Long getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(Long idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
}
