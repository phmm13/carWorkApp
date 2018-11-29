package br.com.pedro.carworkapp.Dao;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Vector;

import br.com.pedro.carworkapp.model.Carro;
import br.com.pedro.carworkapp.model.Marca;
import br.com.pedro.carworkapp.model.Modelo;
import br.com.pedro.carworkapp.model.Oficina;
import br.com.pedro.carworkapp.model.TipoOficina;
import br.com.pedro.carworkapp.model.TipoServico;
import br.com.pedro.carworkapp.model.Versao;

public class OficinaDAO implements Runnable{


    private static final String URL = "http://192.168.25.7:8080/carWorkWebService/services/OficinaDAO?wsdl"; //local do servidor do webservice
    private static final String NAMESPACE = "http://Dao.carWorkWebService.com.br" ;

    private static final String LISTARPORPARAMETROS = "buscaPorCarro";
    private static final String LISTARPORCARRO = "carrosVinculados";
    private static final String LISTARPORSERVICO = "servicosVinculados";

    public Oficina oficina;
    private int idCarro,idTipoServico,idTipoOficina;
    public String opcao;

    public Vector<Carro> listaCarro = new Vector<Carro>();
    public Vector<TipoServico> listaServico = new Vector<TipoServico>();
    public Vector<Oficina> lista = new Vector<Oficina>();

    public OficinaDAO(int idCarro, int idTipoServico, int idTipoOficina){
        this.idCarro = idCarro;
        this.idTipoServico = idTipoServico;
        this.idTipoOficina = idTipoOficina;
    }

    public OficinaDAO() {

    }

    public Vector<Carro> listarPorCarro(String opcao,Oficina oficina){
        this.oficina = oficina;
        this.opcao = opcao;
        Thread thread = new Thread(this);
        thread.start();

        try{
            thread.join();
            return this.listaCarro;
        }catch(Exception e){
            e.printStackTrace();
            return this.listaCarro;
        }
    }
    public Vector<TipoServico> listarPorServico(String opcao,Oficina oficina){
        this.oficina = oficina;
        this.opcao = opcao;
        Thread thread = new Thread(this);
        thread.start();

        try{
            thread.join();
            return this.listaServico;
        }catch(Exception e){
            e.printStackTrace();
            return this.listaServico;
        }
    }
    public Vector<Oficina> listar(String opcao,Oficina oficina){
        this.oficina = oficina;
        this.opcao = opcao;
        Thread thread = new Thread(this);
        thread.start();

        try{
            thread.join();
            return lista;
        }catch(Exception e){
            e.printStackTrace();
            return lista;
        }
    }

    @Override
    public void run() {

        switch (this.opcao){
            case "oficina":{
                this.listarOficina();
                break;
            }
            case "carro":{
                this.listarCarroOficina();
                break;
            }
            case "tipoServico":{
                this.listarServicoOficina();
                break;
            }
        }

    }
    public void listarOficina(){
        SoapObject listarOficina = new SoapObject(NAMESPACE,LISTARPORPARAMETROS);
        listarOficina.addProperty("idCarro",this.idCarro);
        listarOficina.addProperty("idTipoServico",this.idTipoServico);
        listarOficina.addProperty("idTipoOficina",this.idTipoOficina);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(listarOficina);
        envelope.implicitTypes = true;
        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call("urn:"+LISTARPORPARAMETROS,envelope);

            Vector<SoapObject>resposta = (Vector<SoapObject>) envelope.getResponse();
            if(resposta != null){
                for(SoapObject so : resposta){
                    Oficina oficina = new Oficina();

                    TipoOficina to = new TipoOficina();
                    to.setDes_oficina(((SoapObject) so.getProperty(10)).getProperty("des_oficina").toString());
                    to.setId_tipo_oficina(Integer.parseInt(((SoapObject) so.getProperty(10)).getProperty("id_tipo_oficina").toString()));

                    oficina.setId_oficina(Integer.parseInt(so.getProperty("id_oficina").toString()));
                    oficina.setNome_oficina(so.getProperty("nome_oficina").toString());
                    oficina.setBai_oficina(so.getProperty("bai_oficina").toString());
                    oficina.setCep_oficina(so.getProperty("cep_oficina").toString());
                    oficina.setCnpj_oficina(so.getProperty("cnpj_oficina").toString());
                    oficina.setLgd_oficina(so.getProperty("lgd_oficina").toString());
                    oficina.setNum_oficina(Integer.parseInt(so.getProperty("num_oficina").toString()));
                    oficina.setTelefone_oficina(so.getProperty("telefone_oficina").toString());
                    oficina.setUsr_oficina(so.getProperty("usr_oficina").toString());
                    oficina.setPwd_oficina(so.getProperty("pwd_oficina").toString());
                    oficina.setEml_oficina(so.getProperty("eml_oficina").toString());
                    oficina.setTipoOfcina(to);

                    this.lista.add(oficina);

                }
            }else{
                this.lista = null;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void listarCarroOficina(){
        SoapObject listarCarro = new SoapObject(NAMESPACE,LISTARPORCARRO);
        SoapObject oficina = new SoapObject(NAMESPACE,"of");
        SoapObject tipoOficina = new SoapObject(NAMESPACE,"tipoOficina");

        oficina.addProperty("bai_oficina",this.oficina.getBai_oficina());
        oficina.addProperty("cep_oficina",this.oficina.getCep_oficina());
        oficina.addProperty("cnpj_oficina",this.oficina.getCnpj_oficina());
        oficina.addProperty("eml_oficina",this.oficina.getEml_oficina());
        oficina.addProperty("id_oficina",this.oficina.getId_oficina());
        oficina.addProperty("lgd_oficina",this.oficina.getLgd_oficina());
        oficina.addProperty("nome_oficina",this.oficina.getNome_oficina());
        oficina.addProperty("num_oficina",this.oficina.getNum_oficina());
        oficina.addProperty("pwd_oficina",this.oficina.getPwd_oficina());
        oficina.addProperty("telefone_oficina",this.oficina.getTelefone_oficina());

        tipoOficina.addProperty("des_oficina",this.oficina.getTipoOfcina().getDes_oficina());
        tipoOficina.addProperty("id_tipo_oficina",this.oficina.getTipoOfcina().getId_tipo_oficina());

        oficina.addProperty("usr_oficina",this.oficina.getUsr_oficina());

        oficina.addSoapObject(tipoOficina);
        listarCarro.addSoapObject(oficina);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(listarCarro);
        envelope.implicitTypes = true;
        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call("urn:"+LISTARPORCARRO,envelope);

            Vector<SoapObject>resposta = (Vector<SoapObject>) envelope.getResponse();
            if(resposta != null){
                for(SoapObject so : resposta){
                    Carro carro = new Carro();
                    carro.setId_carro(Integer.parseInt(so.getProperty("id_carro").toString()));
                    carro.setAno_carro(so.getProperty("ano_carro").toString());

                    Versao versao = new Versao();
                    versao.setId_versao((Integer.parseInt(((SoapObject) so.getProperty(2)).getProperty("id_versao").toString())));
                    versao.setNome_versao(((SoapObject) so.getProperty(2)).getProperty("id_versao").toString());

                    Modelo modelo = new Modelo();
                    modelo.setId_modelo(Integer.parseInt(((SoapObject)((SoapObject)so.getProperty(2)).getProperty(1)).getProperty("id_modelo").toString()));
                    modelo.setNome_modelo(((SoapObject)((SoapObject)so.getProperty(2)).getProperty(1)).getProperty("nome_modelo").toString());

                    Marca marca = new Marca();
                    marca.setId_marca(Integer.parseInt(((SoapObject)((SoapObject)((SoapObject)so.getProperty(2)).getProperty(1)).getProperty(1)).getProperty("id_marca").toString()));
                    marca.setNome_marca(((SoapObject)((SoapObject)((SoapObject)so.getProperty(2)).getProperty(1)).getProperty(1)).getProperty("nome_marca").toString());


                    modelo.setMarca(marca);
                    versao.setModelo(modelo);
                    carro.setVersao(versao);


                    this.listaCarro.add(carro);

                }
            }else{
                this.listaCarro = null;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void listarServicoOficina() {
        SoapObject listarServico = new SoapObject(NAMESPACE,LISTARPORSERVICO);

        SoapObject oficina = new SoapObject(NAMESPACE,"of");
        SoapObject tipoOficina = new SoapObject(NAMESPACE,"tipoOficina");

        oficina.addProperty("bai_oficina",this.oficina.getBai_oficina());
        oficina.addProperty("cep_oficina",this.oficina.getCep_oficina());
        oficina.addProperty("cnpj_oficina",this.oficina.getCnpj_oficina());
        oficina.addProperty("eml_oficina",this.oficina.getEml_oficina());
        oficina.addProperty("id_oficina",this.oficina.getId_oficina());
        oficina.addProperty("lgd_oficina",this.oficina.getLgd_oficina());
        oficina.addProperty("nome_oficina",this.oficina.getNome_oficina());
        oficina.addProperty("num_oficina",this.oficina.getNum_oficina());
        oficina.addProperty("pwd_oficina",this.oficina.getPwd_oficina());
        oficina.addProperty("telefone_oficina",this.oficina.getTelefone_oficina());

        tipoOficina.addProperty("des_oficina",this.oficina.getTipoOfcina().getDes_oficina());
        tipoOficina.addProperty("id_tipo_oficina",this.oficina.getTipoOfcina().getId_tipo_oficina());

        oficina.addProperty("usr_oficina",this.oficina.getUsr_oficina());

        oficina.addSoapObject(tipoOficina);
        listarServico.addSoapObject(oficina);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(listarServico);
        envelope.implicitTypes = true;
        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call("urn:"+LISTARPORSERVICO,envelope);

            Vector<SoapObject>resposta = (Vector<SoapObject>) envelope.getResponse();
            if(resposta != null){
                for(SoapObject so : resposta){
                    TipoServico tipoServico = new TipoServico();
                    tipoServico.setId_tipo_servico(Integer.parseInt(so.getProperty("id_tipo_servico").toString()));
                    tipoServico.setDes_tipo_servico(so.getProperty("des_tipo_servico").toString());

                    this.listaServico.add(tipoServico);

                }
            }else{
                this.lista = null;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
