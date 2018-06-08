package br.com.fabiotavares.music.domain;

import java.io.Serializable;

import br.com.fabiotavares.music.domain.enums.Estilo;

public class Music implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String duracao;
    private Integer estilo;

    public Music(String nome, String duracao, Estilo estilo) {
        this.nome = nome;
        this.duracao = duracao;
        this.estilo = (estilo == null) ? null : estilo.getCod();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public Integer getEstilo() {
        return estilo;
    }

    public void setEstilo(Integer estilo) {
        this.estilo = estilo;
    }

}
