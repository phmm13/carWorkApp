/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pedro.carworkapp.model;

import java.io.Serializable;
import java.util.ArrayList;



public class TipoServico implements Serializable {
    private int id_tipo_servico;
    private String des_tipo_servico;

    public int getId_tipo_servico() {
        return id_tipo_servico;
    }

    public void setId_tipo_servico(int id_tipo_servico) {
        this.id_tipo_servico = id_tipo_servico;
    }

    public String getDes_tipo_servico() {
        return des_tipo_servico;
    }

    public void setDes_tipo_servico(String des_tipo_servico) {
        this.des_tipo_servico = des_tipo_servico;
    }

}
