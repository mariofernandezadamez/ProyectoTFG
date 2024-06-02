package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MENU_GRAFICOS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_graficos);
    }

    public void grafico_pastel(View view){
        Intent i = new Intent(this, GRAF_PASTEL.class);
        startActivity(i);
    }

    public void grafico_barras(View view){
        Intent i = new Intent(this, GRAF_BARRAS_PLANTAS.class);
        startActivity(i);
    }

    public void grafico_linear(View view){
        //Intent i = new Intent(this, GRAF_BARRAS_PLANTAS.class);
        //startActivity(i);
    }
}


