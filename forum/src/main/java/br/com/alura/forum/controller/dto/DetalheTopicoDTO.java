package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.Resposta;
import br.com.alura.forum.model.StatusTopico;
import br.com.alura.forum.model.Topico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DetalheTopicoDTO {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCricao;
    private String nomeAutor;
    private StatusTopico status;
    private List<RespostaDTO> respostas;

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCricao() {
        return dataCricao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public List<RespostaDTO> getRespostas() {
        return respostas;
    }

    public DetalheTopicoDTO(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCricao = topico.getDataCriacao();
        this.nomeAutor = topico.getAutor().getNome();
        this.status = topico.getStatus();
        this.respostas = new ArrayList<>();
        this.respostas.addAll(topico.getRespostas().stream().map(RespostaDTO::new).collect(Collectors.toList()));
    }
}
