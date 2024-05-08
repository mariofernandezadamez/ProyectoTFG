package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class REGISTRO_USUARIO extends AppCompatActivity {
    Button btnatras, btnregistro;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        btnregistro = findViewById(R.id.REBtnRegistrate);
        btnatras = findViewById(R.id.btnatras);
    }
    public void atras(View view){
        Intent i = new Intent(this, INICIO_SESION.class);
        startActivity(i);
    }
    public void registrobtn(View view){
        Intent i = new Intent(this, INICIO_SESION.class);
        Toast.makeText(this, "Se ha registrado correctamente", Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
}