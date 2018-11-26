package br.com.pedro.carworkapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.pedro.carworkapp.R;
import br.com.pedro.carworkapp.model.Oficina;

public class AdapterListaOficina extends RecyclerView.Adapter<AdapterListaOficina.ViewHolder>{

    private List<Oficina> listaOficina;

    public AdapterListaOficina(List<Oficina> oficinas){
        this.listaOficina = oficinas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_lista,viewGroup,false); //conmverte xml em vizualização

        return new ViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Oficina oficina = this.listaOficina.get( i );

        viewHolder.nomeOficina.setText(oficina.getNome_oficina());
        viewHolder.tipoOficina.setText(oficina.getTipoOfcina().getDes_oficina());
    }

    @Override
    public int getItemCount(){//retorna quantidade de itens
        return this.listaOficina.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nomeOficina,tipoOficina;

        public ViewHolder (View itemView){
            super(itemView);

            //recuperar os itens de tela do xml adapter_lista

            nomeOficina = itemView.findViewById(R.id.nomeOficina);
            tipoOficina = itemView.findViewById(R.id.tipoOficina);
        }
    }
}
