package br.com.unoesc.pb.transferenciacompacito.controllers;

import br.com.unoesc.pb.transferenciacompacito.exception.SaldoInsuficienteException;
import br.com.unoesc.pb.transferenciacompacito.exception.SaqueNegativoException;
import br.com.unoesc.pb.transferenciacompacito.exception.UsuarioNaoEncontradoException;
import br.com.unoesc.pb.transferenciacompacito.form.TransferenciaForm;
import br.com.unoesc.pb.transferenciacompacito.models.Transferencia;
import br.com.unoesc.pb.transferenciacompacito.models.Usuario;
import br.com.unoesc.pb.transferenciacompacito.repositorys.TransferenciaRepository;
import br.com.unoesc.pb.transferenciacompacito.repositorys.UsuarioRepository;
import br.com.unoesc.pb.transferenciacompacito.service.EmailService;
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

    // TODO: transferir somente apartir do id do usuário logado
    @PostMapping("/transferir")
    @Transactional
    public ResponseEntity<String> transeferirCoins(@Valid @RequestBody TransferenciaForm body) {

        // Controle de envio das transferencias
        Transferencia t;
        try {
            t = new Transferencia(body, usuarioRepository);
            t.getUsuarioOrigem().transferir(t);
            transferenciaRepository.save(t);
        } catch (SaldoInsuficienteException | UsuarioNaoEncontradoException | SaqueNegativoException e) {
            // TODO: verificar se a msg do erro deve ser retornada
            System.out.println(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        // Controle de envio dos e-mails
        try {
            new EmailService().emailTransferencia(t);
            return ResponseEntity.ok().body("Transferência efetuada com sucesso! Um e-mail foi enviado para os envolvidos.");
        } catch (Exception e) {
            return ResponseEntity.ok().body("Transação efetuada com sucesso, mas erro ao enviar email");
        }
    }

}
