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
import br.com.pedro.carworkapp.model.TipoServico;

public class AdapterSpinnerTipoServico extends BaseAdapter {
    List<TipoServico> tipoServicos = new ArrayList<TipoServico>();
    LayoutInflater inflater;

    public AdapterSpinnerTipoServico(List<TipoServico> servicos,Context applicationContext){
        this.tipoServicos = servicos;
        this.inflater = LayoutInflater.from(applicationContext);
    }
    @Override
    public int getCount() {
        return tipoServicos.size();
    }

    @Override
    public Object getItem(int position) {
        return tipoServicos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tipoServicos.get(position).getId_tipo_servico();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.adapter_spinner_tipo_servico,null);
        TextView tipoServico = convertView.findViewById(R.id.desTipoServico);
        tipoServico.setText(tipoServicos.get(position).getDes_tipo_servico());
        return convertView;
    }
}
