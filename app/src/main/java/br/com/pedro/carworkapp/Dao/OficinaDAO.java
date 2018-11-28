package br.com.pedro.carworkapp.Dao;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Vector;

import br.com.pedro.carworkapp.model.Oficina;
import br.com.pedro.carworkapp.model.TipoOficina;

public class OficinaDAO implements Runnable{


    private static final String URL = "http://192.168.25.7:8080/carWorkWebService/services/OficinaDAO?wsdl"; //local do servidor do webservice
    private static final String NAMESPACE = "http://Dao.carWorkWebService.com.br" ;

    //private static final String LISTARTODOS = "listar";
    private static final String LISTARPORPARAMETROS = "buscaPorCarro";

    private int idCarro,idTipoServico,idTipoOficina;


    public Vector<Oficina> lista = new Vector<Oficina>();

    public OficinaDAO(int idCarro, int idTipoServico, int idTipoOficina){
        this.idCarro = idCarro;
        this.idTipoServico = idTipoServico;
        this.idTipoOficina = idTipoOficina;
    }
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

    public Vector<Oficina> listarPorCarro(){

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
}
