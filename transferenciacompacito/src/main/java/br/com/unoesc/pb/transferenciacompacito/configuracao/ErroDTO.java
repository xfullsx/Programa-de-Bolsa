package br.com.unoesc.pb.transferenciacompacito.configuracao;

public class ErroDTO {

    private String campo;
    private String erro;
    private Integer code;

    public ErroDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }


    public ErroDTO(Integer code, String erro) {
        this.code = code;
        this.erro = erro;
    }

    public Integer getCode() {
        return code;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }

}
