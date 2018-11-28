package br.com.pedro.carworkapp.Dao;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Vector;

import br.com.pedro.carworkapp.model.TipoOficina;

public class TipoOficinaDAO implements Runnable {
    private static final String URL = "http://192.168.25.7:8080/carWorkWebService/services/TipoOficinaDAO?wsdl"; //local do servidor do webservice
    private static final String NAMESPACE = "http://Dao.carWorkWebService.com.br" ;

    private static final String LISTARTODOS = "listar";

    public Vector<TipoOficina> lista = new Vector<TipoOficina>();

    public Vector<TipoOficina> listar(){
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
        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call("urn:"+LISTARTODOS,envelope);

            Vector<SoapObject>resposta = (Vector<SoapObject>) envelope.getResponse();

            for(SoapObject so : resposta){
                TipoOficina tipoOficina = new TipoOficina();
                tipoOficina.setId_tipo_oficina(Integer.parseInt(so.getProperty("id_tipo_oficina").toString()));
                tipoOficina.setDes_oficina(so.getProperty("des_oficina").toString());

                this.lista.add(tipoOficina);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
