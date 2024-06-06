package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ADMIN_HOME extends AppCompatActivity {
    ImageButton imv;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin_home);
    }

    public void btn_graficos(View view){
        Intent i = new Intent(this, MENU_GRAFICOS.class);
        startActivity(i);
    }

    public void btn_modificacion(View view){
        Intent i = new Intent(this, PARKING_ADMIN_P1.class);
        startActivity(i);
    }
    public void cerrarsesion(View view){
        Intent i = new Intent(this, INICIO_SESION.class);
        startActivity(i);
    }
}