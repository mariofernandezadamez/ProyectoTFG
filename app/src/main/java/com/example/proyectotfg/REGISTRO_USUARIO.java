package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class REGISTRO_USUARIO extends AppCompatActivity {
    ImageButton btnatras;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        //btnatras = findViewById(R.id.btnatras4);
        btnatras.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void registrobtn(View view){
        Intent i = new Intent(this, INICIO_SESION.class);
        Toast.makeText(this, "Se ha registrado correctamente", Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
}
