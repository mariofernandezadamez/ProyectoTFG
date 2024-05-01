package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class INICIO_SESION extends AppCompatActivity {
    Button btn_iniciar_sesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        btn_iniciar_sesion = findViewById(R.id.ISBtnIniciarSesion);
    }
    public void inicio(View view){
        Intent i = new Intent(this, PARKING_USUARIOS_P1.class);
        startActivity(i);
    }

}