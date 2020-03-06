package br.com.unoesc.pb.transferenciacompacito.service;

import br.com.unoesc.pb.transferenciacompacito.form.TransferenciaFORM;
import br.com.unoesc.pb.transferenciacompacito.models.Usuario;
import org.apache.commons.mail.*;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void enviar(String emailDestinatario, String titulo, String mensagem) {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("joaoterceiro366@gmail.com", "magica123"));
            email.setSSLOnConnect(true);

            email.setFrom("joaoterceiro366@gmail.com");
            email.setSubject(titulo);
            email.setMsg(mensagem);
            email.addTo(emailDestinatario);
            email.send();

        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public void emailTransferencia(Usuario remetente, Usuario destinatario, Integer valor) {
        enviar(remetente.getEmail(), "Transferencia Realizada",
                "Transferencia no valor de " + valor + " realizada para " +
                        destinatario.getNome()
        );

        enviar(destinatario.getEmail(), "Transferencia Recebida",
                "Transferencia no valor de " + valor + " recebida de " + remetente.getNome()
        );
    }


}
