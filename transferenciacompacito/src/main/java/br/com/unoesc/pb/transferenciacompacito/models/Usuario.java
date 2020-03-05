package br.com.unoesc.pb.transferenciacompacito.models;

import br.com.unoesc.pb.transferenciacompacito.exception.SaldoInsuficienteException;

import javax.persistence.*;

@Entity(name = "usuarios")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private Integer credito;

    public Integer getCredito() {
        return credito;
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

    public Boolean transferir(Integer valor, Usuario usuario){
        if(this.credito >= valor){
            this.credito -= valor;
            usuario.depositar(valor);
            return Boolean.TRUE;
        }
        throw new SaldoInsuficienteException("Saldo insuficiente! Saldo: " + this.credito + " Valor: " + valor);
    }

}
