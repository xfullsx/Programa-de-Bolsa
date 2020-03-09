package br.com.unoesc.pb.transferenciacompacito.config.validacao;

public class ErroDeFormularioDto {

    private String campo;

    private String mensagem;

    public ErroDeFormularioDto() {
    }

    public ErroDeFormularioDto(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
