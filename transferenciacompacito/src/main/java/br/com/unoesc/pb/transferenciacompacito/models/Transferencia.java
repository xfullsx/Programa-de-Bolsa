package br.com.unoesc.pb.transferenciacompacito.models;

import br.com.unoesc.pb.transferenciacompacito.form.TransferenciaFORM;

import javax.persistence.*;

@Entity(name = "trasferencias")
public class Transferencia {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long usuario_origem;
    private Long usuario_destino;
    private Integer valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuario_origem() {
        return usuario_origem;
    }

    public void setUsuario_origem(Long id_remetente) {
        this.usuario_origem = id_remetente;
    }

    public Long getUsuario_destino() {
        return usuario_destino;
    }

    public void setUsuario_destino(Long id_destinatario) {
        this.usuario_destino = id_destinatario;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Transferencia(){ }

    public Transferencia(TransferenciaFORM form){
        this.usuario_origem = form.getId_remetente();
        this.usuario_destino = form.getId_destinatario();
        this.valor = form.getValor();
    }
}
