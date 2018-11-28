package br.com.pedro.carworkapp.activity;

import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.pedro.carworkapp.Dao.CarroDAO;
import br.com.pedro.carworkapp.Dao.TipoOficinaDAO;
import br.com.pedro.carworkapp.Dao.TipoServicoDAO;
import br.com.pedro.carworkapp.R;
import br.com.pedro.carworkapp.adapter.AdapterSpinnerCarro;
import br.com.pedro.carworkapp.adapter.AdapterSpinnerTipoOficina;
import br.com.pedro.carworkapp.adapter.AdapterSpinnerTipoServico;
import br.com.pedro.carworkapp.model.Carro;
import br.com.pedro.carworkapp.model.TipoOficina;
import br.com.pedro.carworkapp.model.TipoServico;

import static java.lang.Math.toIntExact;

public class MainActivity extends AppCompatActivity{
    private TextView teste;
    private Spinner spinnerCarro,spinnerTipoOficina,spinnerServico;

    private List<Carro> carros = new ArrayList<Carro>();
    private List<TipoServico> servicos = new ArrayList<TipoServico>();
    private List<TipoOficina> tipoOficinas = new ArrayList<TipoOficina>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaComponentes();
        carregaSpinners();



    }
    public void clicaBotao(View view){
        Intent intent = new Intent(this,ListaOficinaActivity.class);
        intent.putExtra("idCarro",toIntExact(spinnerCarro.getSelectedItemId()));
        intent.putExtra("idTipoServico",toIntExact(spinnerServico.getSelectedItemId()));
        intent.putExtra("idTipoOficina",toIntExact(spinnerTipoOficina.getSelectedItemId()));
        startActivity(intent);
    }
    public void inicializaComponentes(){
        this.spinnerCarro = findViewById(R.id.spinnerCarro);
        this.spinnerTipoOficina = findViewById(R.id.spinnerTipoOficina);
        this.spinnerServico = findViewById(R.id.spinnerServico);
    }
    public void carregaSpinners(){
        carregaListas();




        AdapterSpinnerTipoOficina asto = new AdapterSpinnerTipoOficina(this.tipoOficinas,getApplicationContext());
        spinnerTipoOficina.setAdapter(asto);


        AdapterSpinnerTipoServico asts = new AdapterSpinnerTipoServico(this.servicos,getApplicationContext());
        spinnerServico.setAdapter(asts);



        AdapterSpinnerCarro asc = new AdapterSpinnerCarro(this.carros,getApplicationContext());
        spinnerCarro.setAdapter(asc);

    }
    public void carregaListas(){
        this.carros = new CarroDAO().listar();
        this.servicos = new TipoServicoDAO().listar();
        this.tipoOficinas = new TipoOficinaDAO().listar();
    }
}
