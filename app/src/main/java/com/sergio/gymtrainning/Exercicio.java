package com.sergio.gymtrainning;

public class Exercicio {

    private String id;
    private String nomeExercicio;
    private String descricao;
    private String imagem;

    public Exercicio() {
    }

    public Exercicio(String id, String nomeExercicio, String descricao, String imagem) {
        this.id = id;
        this.nomeExercicio = nomeExercicio;
        this.descricao = descricao;
        this.imagem = imagem;
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


    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
