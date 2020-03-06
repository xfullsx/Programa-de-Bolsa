package br.com.unoesc.pb.transferenciacompacito.controllers;

import br.com.unoesc.pb.transferenciacompacito.exception.SaldoInsuficienteException;
import br.com.unoesc.pb.transferenciacompacito.form.TransferenciaFORM;
import br.com.unoesc.pb.transferenciacompacito.models.Transferencia;
import br.com.unoesc.pb.transferenciacompacito.models.Usuario;
import br.com.unoesc.pb.transferenciacompacito.repositorys.TransferenciaRepository;
import br.com.unoesc.pb.transferenciacompacito.repositorys.UsuarioRepository;
import br.com.unoesc.pb.transferenciacompacito.service.EmailService;
import org.apache.commons.mail.EmailException;
import org.hibernate.jdbc.Expectation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class TransferenciaController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @PostMapping("/transferenciacoins")
    @Transactional
    public ResponseEntity<String> transeferirCoins(@Valid @RequestBody TransferenciaFORM body) {

        Optional<Usuario> remetente = usuarioRepository.findById(body.getId_remetente());
        Optional<Usuario> destinatario = usuarioRepository.findById(body.getId_destinatario());

        if (!remetente.isPresent())
            return ResponseEntity.badRequest().body("Usuario remetente não encontrado!");

        else if (!destinatario.isPresent())
            return ResponseEntity.badRequest().body("Usuario destinatário não encontrado!");

        // Controle de envio das transferencias
        try {
            remetente.get().transferir(body.getValor(), destinatario.get());
            Transferencia transferencia = new Transferencia(body);
            transferenciaRepository.save(transferencia);
        } catch (SaldoInsuficienteException e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        // Controle de envio dos e-mails
        try {
            new EmailService().emailTransferencia(remetente.get(), destinatario.get(), body.getValor());
            return ResponseEntity.ok().body("Transferência efetuada com sucesso! Um e-mail foi enviado para os envolvidos.");
        } catch (Exception e) {
            return ResponseEntity.ok().body("Erro ao enviar email, mas transação efetuada com sucesso! ");
        }
    }

}
