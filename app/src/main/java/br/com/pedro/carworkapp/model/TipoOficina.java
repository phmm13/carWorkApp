/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pedro.carworkapp.model;

import java.io.Serializable;
import java.util.ArrayList;



public class TipoOficina implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer id_tipo_oficina;
    private String des_oficina;

    public Integer getId_tipo_oficina() {
        return id_tipo_oficina;
    }

    public void setId_tipo_oficina(Integer id_tipo_oficina) {
        this.id_tipo_oficina = id_tipo_oficina;
    }

    public String getDes_oficina() {
        return des_oficina;
    }

    public void setDes_oficina(String des_oficina) {
        this.des_oficina = des_oficina;
    }

}
