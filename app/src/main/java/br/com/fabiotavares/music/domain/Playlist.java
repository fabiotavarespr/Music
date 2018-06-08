package br.com.fabiotavares.music.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Playlist implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private List<Music> musicas = new ArrayList<>();

    public Playlist() {

    }

    public Playlist(String nome, List<Music> musicas) {
        this.nome = nome;
        this.musicas = musicas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Music> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Music> musicas) {
        this.musicas = musicas;
    }
}
