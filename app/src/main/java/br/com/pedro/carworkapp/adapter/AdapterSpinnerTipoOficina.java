package br.com.pedro.carworkapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.pedro.carworkapp.R;
import br.com.pedro.carworkapp.model.TipoOficina;

public class AdapterSpinnerTipoOficina extends BaseAdapter {
    List<TipoOficina> tipoOficinas = new ArrayList<TipoOficina>();
    LayoutInflater inflater;

    public AdapterSpinnerTipoOficina(List<TipoOficina> tipoOficinas,Context applicationContext){
        this.tipoOficinas = tipoOficinas;
        this.inflater = LayoutInflater.from(applicationContext);
    }
    @Override
    public int getCount() {
        return tipoOficinas.size();
    }

    @Override
    public Object getItem(int position) {
        return tipoOficinas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.tipoOficinas.get(position).getId_tipo_oficina();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.adapter_spinner_tipo_oficina,null);
        TextView tipoOficina = convertView.findViewById(R.id.desTipoOficina);
        tipoOficina.setText(tipoOficinas.get(position).getDes_oficina());
        return convertView;
    }
}
