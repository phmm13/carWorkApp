package br.com.pedro.carworkapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.pedro.carworkapp.R;
import br.com.pedro.carworkapp.model.Carro;

public class AdapterSpinnerCarro extends BaseAdapter {
    List<Carro> carros = new ArrayList<Carro>();
    LayoutInflater inflater;
    SimpleDateFormat saida = new SimpleDateFormat("yyyy");
    SimpleDateFormat entrada = new SimpleDateFormat("yyyy-MM-dd");

    public AdapterSpinnerCarro(List<Carro> carros, Context context){
        this.carros = carros;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return carros.size();
    }

    @Override
    public Object getItem(int position) {
        return carros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.carros.get(position).getId_carro();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.adapter_spinner_carro,null);
        TextView carro = convertView.findViewById(R.id.nomeCarro);
        try{

            String nomeDoCarro = this.carros.get(position).getVersao().getModelo().getMarca().getNome_marca()
                    + " - " + this.carros.get(position).getVersao().getModelo().getNome_modelo()
                    + " - " + this.carros.get(position).getVersao().getNome_versao()
                    + " - " + saida.format(entrada.parse(this.carros.get(position).getAno_carro()));

            carro.setText(nomeDoCarro);
            return convertView;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
