package com.sergio.gymtrainning;

import java.io.Serializable;

public class Exercicio implements Serializable {

    private String _id;
    private String nomeExercicio;
    private String descricao;
    private String url;

    public Exercicio(String id, String nomeExercicio, String descricao) {
        this._id = id;
        this.nomeExercicio = nomeExercicio;
        this.descricao = descricao;
    }

    public String getNomeExercicio() {
        return nomeExercicio;
    }

    public void setNomeExercicio(String nomeExercicio) {
        this.nomeExercicio = nomeExercicio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


    @Override
    public String toString() {
        return nomeExercicio;
    }
}
