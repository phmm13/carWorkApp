package br.com.pedro.carworkapp.Dao;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Vector;

import br.com.pedro.carworkapp.model.TipoServico;

public class TipoServicoDAO implements Runnable {
    private static final String URL = "http://192.168.25.7:8080/carWorkWebService/services/TipoServicoDAO?wsdl"; //local do servidor do webservice
    private static final String NAMESPACE = "http://Dao.carWorkWebService.com.br" ;

    private static final String LISTARTODOS = "listar";

    public Vector<TipoServico> lista = new Vector<TipoServico>();

    public Vector<TipoServico> listar(){
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
                TipoServico tipoServico = new TipoServico();
                tipoServico.setId_tipo_servico(Integer.parseInt(so.getProperty("id_tipo_servico").toString()));
                tipoServico.setDes_tipo_servico(so.getProperty("des_tipo_servico").toString());

                this.lista.add(tipoServico);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
