package com.example.uno_2543.crr.Models;

import java.util.Date;

/**
 * Created by uno-2543 on 26/02/16.
 */
public class ControleAplicacao {
    private Integer _id;
    private String tipo, descricao, som;
    private Integer duracao, tipo_notificacao;
    private String validade_produto;

    public ControleAplicacao(){

    }

    public ControleAplicacao(Integer _id, String tipo, String descricao,
                             String som, Integer duracao, Integer tipo_notificacao, String validade_produto) {
        this._id = _id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.som = som;
        this.duracao = duracao;
        this.tipo_notificacao = tipo_notificacao;
        this.validade_produto = validade_produto;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSom() {
        return som;
    }

    public void setSom(String som) {
        this.som = som;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Integer getTipo_notificacao() {
        return tipo_notificacao;
    }

    public void setTipo_notificacao(Integer tipo_notificacao) {
        this.tipo_notificacao = tipo_notificacao;
    }

    public String getValidade_produto() {
        return validade_produto;
    }

    public void setValidade_produto(String validade_produto) {
        this.validade_produto = validade_produto;
    }

}
