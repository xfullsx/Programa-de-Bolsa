package br.com.unoesc.pb.transferenciacompacito.models;

import br.com.unoesc.pb.transferenciacompacito.exception.UsuarioNaoEncontradoException;
import br.com.unoesc.pb.transferenciacompacito.form.TransferenciaForm;
import br.com.unoesc.pb.transferenciacompacito.repositorys.UsuarioRepository;

import javax.persistence.*;
import java.util.Optional;

@Entity(name = "transferencia")
public class Transferencia {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuarioOrigem;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuarioDestino;

    private Integer valor;

    public Transferencia(){ }

    public Transferencia(Usuario usuarioOrigem, Usuario usuarioDestino, Integer valor) {
        this.setUsuarioOrigem(usuarioOrigem);
        this.setUsuarioDestino(usuarioDestino);
        this.setValor(valor);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuarioOrigem() {
        return usuarioOrigem;
    }

    public void setUsuarioOrigem(Usuario usuarioOrigem) {
        this.usuarioOrigem = usuarioOrigem;
    }

    public Usuario getUsuarioDestino() {
        return usuarioDestino;
    }

    public void setUsuarioDestino(Usuario usuario) {
        this.usuarioDestino = usuario;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Boolean efetuar() {
        Usuario usuarioOrigem = this.getUsuarioOrigem();
        Usuario usuarioDestino = this.getUsuarioDestino();

        if(usuarioOrigem == null || usuarioDestino == null)
            throw new UsuarioNaoEncontradoException("Um dos usuários envolvidos na transação não foi encontrado");


        Integer valor = this.getValor();
        usuarioOrigem.sacar(valor);
        usuarioDestino.depositar(valor);

        return Boolean.TRUE;
    }
}
