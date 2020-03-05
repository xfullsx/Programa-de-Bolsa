package br.com.unoesc.pb.transferenciacompacito.controllers;

import br.com.unoesc.pb.transferenciacompacito.exception.SaldoInsuficienteException;
import br.com.unoesc.pb.transferenciacompacito.form.TransferenciaFORM;
import br.com.unoesc.pb.transferenciacompacito.models.Transferencia;
import br.com.unoesc.pb.transferenciacompacito.models.Usuario;
import br.com.unoesc.pb.transferenciacompacito.repositorys.TransferenciaRepository;
import br.com.unoesc.pb.transferenciacompacito.repositorys.UsuarioRepository;
import br.com.unoesc.pb.transferenciacompacito.service.EmailService;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
public class TransferenciaController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @PostMapping("/transferenciacoins")
    @Transactional
    public ResponseEntity<?> transeferirCoins(@RequestBody TransferenciaFORM body) {
        if (body.getId_remetente() != null && body.getId_destinatario() != null) {

            Optional<Usuario> remetente = usuarioRepository.findById(body.getId_remetente());
            Optional<Usuario> destinatario = usuarioRepository.findById(body.getId_destinatario());

            if (!remetente.isPresent())
                return ResponseEntity.badRequest().body("Usuario remetente não encontrado!");

            else if (!destinatario.isPresent())
                return ResponseEntity.badRequest().body("Usuario destinatário não encontrado!");


            try {
                remetente.get().transferir(body.getValor(), destinatario.get());
                Transferencia transferencia = new Transferencia(body);
                transferenciaRepository.save(transferencia);

                new EmailService().enviar(remetente.get().getEmail(), "Transferencia Realizada",
                        "Transferencia no valor de " + body.getValor() + " realizada para " +
                                destinatario.get().getNome()
                );
                new EmailService().enviar(destinatario.get().getEmail(), "Transferencia Recebida",
                        "Transferencia no valor de " + body.getValor() + " recebida de " + remetente.get().getNome()
                );
                return ResponseEntity.ok().build();

            } catch (SaldoInsuficienteException e) {
                System.out.println(e);
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        }

        return ResponseEntity.badRequest().body("Está Faltando informações no json");
    }

}
