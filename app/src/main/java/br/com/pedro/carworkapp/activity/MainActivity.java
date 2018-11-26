package br.com.pedro.carworkapp.activity;

import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import br.com.pedro.carworkapp.Dao.OficinaDAO;
import br.com.pedro.carworkapp.R;

public class MainActivity extends AppCompatActivity{
    private TextView teste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    public void clicaBotao(View view){
        Intent intent = new Intent(this,ListaOficinaActivity.class);
        startActivity(intent);
    }

}
