package br.com.unoesc.pb.transferenciacompacito.form;

import br.com.unoesc.pb.transferenciacompacito.exception.UsuarioNaoEncontradoException;
import br.com.unoesc.pb.transferenciacompacito.models.Transferencia;
import br.com.unoesc.pb.transferenciacompacito.models.Usuario;
import br.com.unoesc.pb.transferenciacompacito.repositorys.UsuarioRepository;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

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

    public Transferencia converter(UsuarioRepository repository) {
        // TODO: aqui deve ser o usuário autenticado
        Optional<Usuario> remetente = repository.findById(this.getIdRemetente());

        Optional<Usuario> destinatario = repository.findById(this.getIdDestinatario());

        if (!remetente.isPresent() || !destinatario.isPresent()) {
            throw new UsuarioNaoEncontradoException("Um dos usuários envolvidos na transação não foi encontrado");
        }

        Usuario usuarioOrigem = remetente.get();
        Usuario usuarioDestino = destinatario.get();

        if (usuarioDestino.getId() == usuarioOrigem.getId()) {
            // trocar exception
            throw new UsuarioNaoEncontradoException("Um dos usuários envolvidos na transação não foi encontrado");
        }

        return new Transferencia(usuarioOrigem, usuarioDestino, this.getValor());
    }
}
