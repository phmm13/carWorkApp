package br.com.pedro.carworkapp.Dao;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Vector;

import br.com.pedro.carworkapp.model.Carro;
import br.com.pedro.carworkapp.model.Marca;
import br.com.pedro.carworkapp.model.Modelo;
import br.com.pedro.carworkapp.model.Versao;

public class CarroDAO implements Runnable {
    private static final String URL = "http://192.168.25.7:8080/carWorkWebService/services/CarroDAO?wsdl"; //local do servidor do webservice
    private static final String NAMESPACE = "http://Dao.carWorkWebService.com.br" ;

    private static final String LISTARTODOS = "listar";

    public Vector<Carro> lista = new Vector<Carro>();

    public Vector<Carro> listar(){
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
        SoapObject listarOficina = new SoapObject(NAMESPACE,LISTARTODOS);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(listarOficina);
        envelope.implicitTypes = true;
        HttpTransportSE http = new HttpTransportSE(URL,60000);

        try {
            http.call("urn:"+LISTARTODOS,envelope);

            Vector<SoapObject>resposta = (Vector<SoapObject>) envelope.getResponse();

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


                this.lista.add(carro);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
