package br.com.fabiotavares.music.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Playlist implements Parcelable {

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

    Playlist(Parcel in) {
        this.nome = in.readString();
        this.musicas = new ArrayList<Music>();
        in.readTypedList(musicas, Music.CREATOR);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeTypedList(musicas);
    }

    public int describeContents() {
        return 0;
    }


    public static final Parcelable.Creator<Playlist> CREATOR
            = new Parcelable.Creator<Playlist>() {

        public Playlist createFromParcel(Parcel in) {
            return new Playlist(in);
        }

        public Playlist[] newArray(int size) {
            return new Playlist[size];
        }
    };
}
