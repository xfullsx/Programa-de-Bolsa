package br.com.unoesc.pb.transferenciacompacito.form;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TransferenciaForm {
    @NotNull    @Min(value = 1)
    private Long idRemetente;

    @NotNull    @Min(value = 1)
    private Long idDestinatario;

    @NotNull    @DecimalMin(value = "0.0", inclusive = false)
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
