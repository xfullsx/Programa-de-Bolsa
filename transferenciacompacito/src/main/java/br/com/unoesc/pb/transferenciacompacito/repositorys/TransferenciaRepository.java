package br.com.unoesc.pb.transferenciacompacito.repositorys;

import br.com.unoesc.pb.transferenciacompacito.models.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
}
