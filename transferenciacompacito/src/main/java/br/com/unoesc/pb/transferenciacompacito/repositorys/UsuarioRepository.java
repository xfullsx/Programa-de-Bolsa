package br.com.unoesc.pb.transferenciacompacito.repositorys;

import br.com.unoesc.pb.transferenciacompacito.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
