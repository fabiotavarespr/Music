package br.com.fabiotavares.music.domain;

import java.io.Serializable;

public class Music implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String duracao;

    public Music(String nome, String duracao) {
        this.nome = nome;
        this.duracao = duracao;
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

}
