package br.com.unoesc.pb.transferenciacompacito.form;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TransferenciaForm {
//    @NotNull    @NotEmpty
    private Long idRemetente;

//    @NotNull    @NotEmpty
    private Long idDestinatario;

//    @NotNull    @DecimalMin("0.0")
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
