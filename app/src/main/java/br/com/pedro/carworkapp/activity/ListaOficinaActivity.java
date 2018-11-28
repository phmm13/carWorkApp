package br.com.pedro.carworkapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.pedro.carworkapp.Dao.OficinaDAO;
import br.com.pedro.carworkapp.R;
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

        recyclerView = findViewById(R.id.recyclerOficina);

        this.carregaLista(idCarro,idTipoServico,idTipoOficina);


        if(this.listaOficina == null){
            this.listaOficina = new ArrayList<Oficina>();
        }
        //configurar adapter, adapter recebe os dados, formata e joga no recycler view
        AdapterListaOficina adapter = new AdapterListaOficina(this.listaOficina);


        //configurar recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration( new DividerItemDecoration(getApplicationContext(),LinearLayout.VERTICAL)); //linha em cada item

        //evento de click
        /*
        recyclerView.addOnItemTouchListener(
                //recomendação do google, baixar classe pronta
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.onItemClickListener(){
                            //sao 3 metodos a ser implementado

                            public void onItemClick(View view, int position){
                                //position retorna a posição
                                Oficina oficina = listaOficina.get(position);

                                Toast.makeText(getApplicationContext(),"item pressionado",Toast.LENGTH_SHORT).show();

                                //aqui usa o intent para mandar pra pagina de detalhe
                            }
                        }
                );
		);
         */

    }
    public void carregaLista(int idCarro,int idTipoServico ,  int idTipoOficina){
        try {
            this.listaOficina = new OficinaDAO(idCarro,idTipoServico,idTipoOficina).listar();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
