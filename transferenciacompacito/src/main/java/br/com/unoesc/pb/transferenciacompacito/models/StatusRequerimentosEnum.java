package br.com.unoesc.pb.transferenciacompacito.models;

public enum StatusRequerimentosEnum {
    EM_ANALISE(0),
    APROVADO(1),
    A_CAMINHO(2),
    ENTREGUE(3),
    CANCELADO(4),
    REPROVADO(5)
    ;

    StatusRequerimentosEnum(int i) {
    }
}
