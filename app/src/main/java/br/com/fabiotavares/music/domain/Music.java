package br.com.fabiotavares.music.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Music implements Parcelable {

    private String nome;
    private String duracao;

    public Music() {
    }

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


    Music(Parcel in) {
        this.nome = in.readString();
        this.duracao = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(duracao);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Music> CREATOR
            = new Parcelable.Creator<Music>() {

        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        public Music[] newArray(int size) {
            return new Music[size];
        }
    };
}
