package br.com.unoesc.pb.transferenciacompacito.models;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "status_requerimentos")
public class StatusRequerimento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idTransferencia;
    private Integer idStatus;
    private Date dataStatus;

    public StatusRequerimento(){}

    public StatusRequerimento(Long idTransferencia, Integer idStatus){
        this.idTransferencia = idTransferencia;
        this.idStatus = idStatus;
    }

    public Long getId() {
        return id;
    }

    public Long getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(Long idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public Date getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Date dateStatus) {
        this.dataStatus = dateStatus;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }
}
