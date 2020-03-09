package br.com.unoesc.pb.transferenciacompacito.models;

import br.com.unoesc.pb.transferenciacompacito.exception.UsuarioNaoEncontradoException;
import br.com.unoesc.pb.transferenciacompacito.form.TransferenciaForm;
import br.com.unoesc.pb.transferenciacompacito.repositorys.UsuarioRepository;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import java.util.Optional;

@Entity(name = "transferencia")
public class Transferencia {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuarioOrigem;

    @ManyToOne
    private Usuario usuarioDestino;

    private Integer valor;

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

    public Transferencia(){ }

    public Transferencia(TransferenciaForm form, UsuarioRepository repository){
        Optional<Usuario> remetente = repository.findById(form.getIdRemetente());
        Optional<Usuario> destinatario = repository.findById(form.getIdDestinatario());

        if (!remetente.isPresent() || !destinatario.isPresent()) {
            throw new UsuarioNaoEncontradoException();
        }

        this.setUsuarioOrigem(remetente.get());
        this.setUsuarioDestino(destinatario.get());
        this.setValor(form.getValor());
    }
}
