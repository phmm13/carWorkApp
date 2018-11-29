package br.com.pedro.carworkapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.pedro.carworkapp.Dao.OficinaDAO;
import br.com.pedro.carworkapp.R;
import br.com.pedro.carworkapp.RecyclerItemClickListener;
import br.com.pedro.carworkapp.adapter.AdapterListaOficina;
import br.com.pedro.carworkapp.model.Oficina;

public class ListaOficinaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Oficina> listaOficina = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_oficina);

        Intent intent = getIntent();
        int idCarro =  intent.getExtras().getInt("idCarro");
        int idTipoServico =   intent.getExtras().getInt("idTipoServico");
        int idTipoOficina =   intent.getExtras().getInt("idTipoOficina");



        this.carregaLista(idCarro,idTipoServico,idTipoOficina);

        this.carregaRecyclerView();

        //evento de click
        this.recyclerView.addOnItemTouchListener(
                //recomendação do google
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener(){
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }

                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(getApplicationContext(),DetalheOficinaActivity.class);
                                intent.putExtra("oficina", (Serializable) listaOficina.get(position));
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }
                        }
                ));



    }
    public void carregaLista(int idCarro,int idTipoServico ,  int idTipoOficina){
        try {
            this.listaOficina = new OficinaDAO(idCarro,idTipoServico,idTipoOficina).listar("oficina" , new Oficina());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void carregaRecyclerView (){
        this.recyclerView = findViewById(R.id.recyclerOficina);

        if(this.listaOficina == null){
            this.listaOficina = new ArrayList<Oficina>();
        }
        //configurar adapter, adapter recebe os dados, formata e joga no recycler view
        AdapterListaOficina adapter = new AdapterListaOficina(this.listaOficina);


        //configurar recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.addItemDecoration( new DividerItemDecoration(getApplicationContext(),LinearLayout.VERTICAL)); //linha em cada item

    }
}
