/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedro.carworkapp.model;

import java.util.ArrayList;


public class Versao {

    private int id_versao;
    private String nome_versao;
    private Modelo modelo;

    public int getId_versao() {
        return id_versao;
    }

    public void setId_versao(int id_versao) {
        this.id_versao = id_versao;
    }

    public String getNome_versao() {
        return nome_versao;
    }

    public void setNome_versao(String nome_versao) {
        this.nome_versao = nome_versao;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }


}
