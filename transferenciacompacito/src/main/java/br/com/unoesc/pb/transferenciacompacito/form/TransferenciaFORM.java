package br.com.unoesc.pb.transferenciacompacito.form;

public class TransferenciaFORM {
    private Long id_remetente;
    private Long id_destinatario;
    private Integer valor;

    public Long getId_remetente() {
        return id_remetente;
    }

    public void setId_remetente(Long id_remetente) {
        this.id_remetente = id_remetente;
    }

    public Long getId_destinatario() {
        return id_destinatario;
    }

    public void setId_destinatario(Long id_destinatario) {
        this.id_destinatario = id_destinatario;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
}
