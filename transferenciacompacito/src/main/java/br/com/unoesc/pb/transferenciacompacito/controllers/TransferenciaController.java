package br.com.unoesc.pb.transferenciacompacito.controllers;

import br.com.unoesc.pb.transferenciacompacito.controllers.dto.TransferenciaDto;
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
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TransferenciaController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @PostMapping("/transferir")
    @Transactional
    public ResponseEntity<String> transeferirCoins(@Valid @RequestBody TransferenciaForm body) {

        // Controle de envio das transferencias
        Transferencia t;
        try {
            t = body.converter(usuarioRepository); // new Transferencia(body, usuarioRepository);
            t.efetuar();
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

    // TODO: pode ser necessária remoção/refatoração do presente método
    @GetMapping("/transferencias/{id}")
    public ResponseEntity<List<TransferenciaDto>> transferenciasUsuario(@PathVariable Long id){
        Usuario u = usuarioRepository.getOne(id);
        List<Transferencia> transferenciasRealizadas = u.getTransferenciasRealizadas();


        List<TransferenciaDto> transferenciaDtoStream = transferenciasRealizadas.stream().map(TransferenciaDto::new).collect(Collectors.toList());


        return ResponseEntity.ok(transferenciaDtoStream);
    }

}
