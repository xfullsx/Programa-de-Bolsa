package br.com.unoesc.pb.transferenciacompacito.controllers;

import br.com.unoesc.pb.transferenciacompacito.form.TransferenciaFORM;
import br.com.unoesc.pb.transferenciacompacito.models.*;
import br.com.unoesc.pb.transferenciacompacito.repositorys.StatusRequerimentoRepository;
import br.com.unoesc.pb.transferenciacompacito.repositorys.TransferenciaRepository;
import br.com.unoesc.pb.transferenciacompacito.repositorys.UsuarioRepository;
import br.com.unoesc.pb.transferenciacompacito.service.EmailService;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private StatusRequerimentoRepository statusRequerimentoRepository;

    @PostMapping("/transferencia")
    @Transactional
    public ResponseEntity<String> transeferirCoins(@Valid @RequestBody TransferenciaFORM body) throws EmailException {

        Optional<Usuario> remetente = usuarioRepository.findById(body.getIdRemetente());
        Optional<Usuario> destinatario = usuarioRepository.findById(body.getIdDestinatario());

        if (!remetente.isPresent())
            return ResponseEntity.badRequest().body("Usuario remetente não encontrado!");

        if (!destinatario.isPresent())
            return ResponseEntity.badRequest().body("Usuario destinatário não encontrado!");

        Transferencia transferencia = new Transferencia(body);
        StatusRequerimento statusRequerimento = new StatusRequerimento(transferencia.getId(), StatusRequerimentosEnum.APROVADO.ordinal());

        remetente.get().transferir(body.getValor(), destinatario.get());
        transferenciaRepository.save(transferencia);
        statusRequerimentoRepository.save(statusRequerimento);

        new EmailService().emailTransferencia(remetente.get(), destinatario.get(), body.getValor());
        return ResponseEntity.ok().body("Transferência efetuada com sucesso! Um e-mail foi enviado para os envolvidos.");
    }
}


