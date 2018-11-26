package br.com.pedro.carworkapp.Dao;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


import java.util.ArrayList;
import java.util.Vector;

import br.com.pedro.carworkapp.model.Oficina;
import br.com.pedro.carworkapp.model.TipoOficina;

public class OficinaDAO implements Runnable{


    private static final String URL = "http://192.168.25.7:8080/carWorkWebService/services/OficinaDAO?wsdl"; //local do servidor do webservice
    private static final String NAMESPACE = "http://Dao.carWorkWebService.com.br" ;

    private static final String LISTARTODOS = "listar";
    private static final String LISTARPORPARAMETROS = "buscarPorCarro";

    private int idCarro,idTipoServico,idTipoOficina;

    public Vector<Oficina> lista = new Vector<Oficina>();

    public Vector<Oficina> listar(){
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

    public Vector<Oficina> listarPorCarro(int idCarro, int idTipoServico, int idTipoOficina){
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
                Oficina oficina = new Oficina();

                TipoOficina to = new TipoOficina();
                to.setDes_oficina(((SoapObject) so.getProperty(10)).getProperty("des_oficina").toString());
                to.setId_tipo_oficina(Integer.parseInt(((SoapObject) so.getProperty(10)).getProperty("id_tipo_oficina").toString()));

                oficina.setId_oficina(Integer.parseInt(so.getProperty("id_oficina").toString()));
                oficina.setNome_oficina(so.getProperty("nome_oficina").toString());
                oficina.setTipoOfcina(to);

                this.lista.add(oficina);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
