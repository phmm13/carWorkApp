package br.com.pedro.carworkapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.pedro.carworkapp.Dao.OficinaDAO;
import br.com.pedro.carworkapp.R;
import br.com.pedro.carworkapp.model.Carro;
import br.com.pedro.carworkapp.model.Oficina;
import br.com.pedro.carworkapp.model.TipoServico;

public class DetalheOficinaActivity extends AppCompatActivity {

    private Oficina oficina;
    private TextView nomeOficina,tipoOficina,telefoneOficina,carroOficina,servicoOficina,emailOficina,enderecoOficina;
    private List<TipoServico> listaServico = new ArrayList<TipoServico>();
    private List<Carro> listaCarro = new ArrayList<Carro>();
    SimpleDateFormat saida = new SimpleDateFormat("yyyy");
    SimpleDateFormat entrada = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_oficina);

        Intent intent = getIntent();
        this.oficina = (Oficina) intent.getSerializableExtra("oficina");
        this.inicializaComponentes();
    }
    public void inicializaComponentes(){
        this.nomeOficina = findViewById(R.id.detalheNomeOficina);
        this.tipoOficina = findViewById(R.id.detalheTipoOficina);
        this.telefoneOficina = findViewById(R.id.detalheTelefoneOficina);
        this.carroOficina = findViewById(R.id.detalheCarroOficina);
        this.servicoOficina = findViewById(R.id.detalheServicoOficina);
        this.emailOficina = findViewById(R.id.detalheEmailOficina);
        this.enderecoOficina = findViewById(R.id.detalheEnderecoOficina);

        this.nomeOficina.setText(this.oficina.getNome_oficina());
        this.tipoOficina.setText(this.oficina.getTipoOfcina().getDes_oficina());
        this.telefoneOficina.setText(this.oficina.getTelefone_oficina());
        this.emailOficina.setText(this.oficina.getEml_oficina());
        this.enderecoOficina.setText(this.concatenarEndereco());

        this.listaCarro = new OficinaDAO().listarPorCarro("carro",this.oficina);
        this.listaServico = new OficinaDAO().listarPorServico("tipoServico",this.oficina);

        this.carroOficina.setText(concatenarCarro());
        this.servicoOficina.setText(concatenarServico());
    }
    public String concatenarCarro(){
        String concat="";

        for(Carro co : this.listaCarro){
            try{
                concat = concat + co.getVersao().getModelo().getMarca().getNome_marca() +
                        "-"+co.getVersao().getModelo().getNome_modelo() +
                        "-" + co.getVersao().getNome_versao() +
                        "-" + saida.format(entrada.parse(co.getAno_carro())) + " \n";
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return concat;
    }
    public String concatenarServico(){
        String concat = "";

        for(TipoServico so : this.listaServico){
            concat = concat + "" + so.getDes_tipo_servico() +"\n";
        }
        return concat;
    }
    public String concatenarEndereco(){
        return this.oficina.getBai_oficina() + " - " + this.oficina.getLgd_oficina() + " " + this.oficina.getNum_oficina() + " - " + this.oficina.getCep_oficina();
    }
}
