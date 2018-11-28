/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pedro.carworkapp.model;

import java.io.Serializable;
import java.util.ArrayList;



public class Oficina implements Serializable{
    private int id_oficina;
    private String nome_oficina;
    private String telefone_oficina;
    private String cnpj_oficina;
    private String eml_oficina;
    private String usr_oficina;
    private String pwd_oficina;
    private String cep_oficina;
    private String lgd_oficina;
    private String bai_oficina;
    private int num_oficina;
    private TipoOficina tipoOfcina;

    public int getId_oficina() {
        return id_oficina;
    }

    public void setId_oficina(int id_oficina) {
        this.id_oficina = id_oficina;
    }

    public String getNome_oficina() {
        return nome_oficina;
    }

    public void setNome_oficina(String nome_oficina) {
        this.nome_oficina = nome_oficina;
    }

    public String getTelefone_oficina() {
        return telefone_oficina;
    }

    public void setTelefone_oficina(String telefone_oficina) {
        this.telefone_oficina = telefone_oficina;
    }

    public String getCnpj_oficina() {
        return cnpj_oficina;
    }

    public void setCnpj_oficina(String cnpj_oficina) {
        this.cnpj_oficina = cnpj_oficina;
    }

    public String getEml_oficina() {
        return eml_oficina;
    }

    public void setEml_oficina(String eml_oficina) {
        this.eml_oficina = eml_oficina;
    }

    public String getUsr_oficina() {
        return usr_oficina;
    }

    public void setUsr_oficina(String usr_oficina) {
        this.usr_oficina = usr_oficina;
    }

    public String getPwd_oficina() {
        return pwd_oficina;
    }

    public void setPwd_oficina(String pwd_oficina) {
        this.pwd_oficina = pwd_oficina;
    }

    public String getCep_oficina() {
        return cep_oficina;
    }

    public void setCep_oficina(String cep_oficina) {
        this.cep_oficina = cep_oficina;
    }

    public String getLgd_oficina() {
        return lgd_oficina;
    }

    public void setLgd_oficina(String lgd_oficina) {
        this.lgd_oficina = lgd_oficina;
    }

    public String getBai_oficina() {
        return bai_oficina;
    }

    public void setBai_oficina(String bai_oficina) {
        this.bai_oficina = bai_oficina;
    }

    public int getNum_oficina() {
        return num_oficina;
    }

    public void setNum_oficina(int num_oficina) {
        this.num_oficina = num_oficina;
    }
    
    public TipoOficina getTipoOfcina() {
        return tipoOfcina;
    }

    public void setTipoOfcina(TipoOficina tipoOfcina) {
        this.tipoOfcina = tipoOfcina;
    }

}
