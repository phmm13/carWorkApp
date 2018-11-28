package br.com.pedro.carworkapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.pedro.carworkapp.R;
import br.com.pedro.carworkapp.model.Oficina;

public class DetalheOficinaActivity extends AppCompatActivity {

    private Oficina oficina;
    private TextView nomeOficina,tipoOficina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_oficina);

        Intent intent = getIntent();
        this.oficina = (Oficina) intent.getSerializableExtra("oficina");
    }
    public void inicializaComponentes(){
        this.nomeOficina = findViewById(R.id.detalheNomeOficina);
        this.tipoOficina = findViewById(R.id.detalheTipoOficina);

        this.nomeOficina.setText(this.oficina.getNome_oficina());
        this.tipoOficina.setText(this.oficina.getTipoOfcina().getDes_oficina());
    }
}
