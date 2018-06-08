package br.com.fabiotavares.music.domain.enums;

public enum Estilo {

    ACOSTIC(1, "Acustic"),
    ELETRONIC(2, "Eletronic"),
    POP(3, "Pop"),
    ROCK(4, "Rock");

    private int cod;
    private String descricao;

    Estilo(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Estilo toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Estilo x : Estilo.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        return null;

    }
}
