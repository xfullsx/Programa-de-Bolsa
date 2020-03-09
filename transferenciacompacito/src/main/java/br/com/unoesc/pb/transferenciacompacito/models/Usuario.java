package br.com.unoesc.pb.transferenciacompacito.models;

import br.com.unoesc.pb.transferenciacompacito.exception.SaldoInsuficienteException;
import br.com.unoesc.pb.transferenciacompacito.exception.SaqueNegativoException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "usuario")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private Integer credito;

    @OneToMany(mappedBy = "usuarioOrigem")
    private List<Transferencia> transferenciasRealizadas = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioDestino")
    private List<Transferencia> transferenciasRecebidas = new ArrayList<>();

    public Integer getCredito() {
        return credito;
    }

    public List<Transferencia> getTransferenciasRealizadas() {
        return transferenciasRealizadas;
    }

    public void setTransferenciasRealizadas(List<Transferencia> transferenciasRealizadas) {
        this.transferenciasRealizadas = transferenciasRealizadas;
    }

    public List<Transferencia> getTransferenciasRecebidas() {
        return transferenciasRecebidas;
    }

    public void setTransferenciasRecebidas(List<Transferencia> transferenciasRecebidas) {
        this.transferenciasRecebidas = transferenciasRecebidas;
    }

    public Usuario(){}

    public Usuario(Long id, String nome, String email){
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void depositar(Integer credito) {
        this.credito += credito;
    }

    public void sacar(Integer valorSaque) {
        if(valorSaque <= 0) {
            throw new SaqueNegativoException("Valor negativo informado para o saque!");
        } else if (this.credito < valorSaque) {
            throw new SaldoInsuficienteException("Saldo insuficiente! Saldo: " + this.credito + " Valor: " + valorSaque);
        }
        this.credito -= valorSaque;
    }

    public Boolean transferir(Transferencia transferencia){
        Integer valor = transferencia.getValor();
        this.sacar(valor);
        transferencia.getUsuarioDestino().depositar(valor);
        return Boolean.TRUE;
    }

}
