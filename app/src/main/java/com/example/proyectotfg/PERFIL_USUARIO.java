package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class PERFIL_USUARIO extends AppCompatActivity {
    ImageButton imgbtnatras;
    TextView txt_dni;
    EditText etxt_usuario;
    EditText etxt_contraseña;
    EditText etxt_matricula_principal;
    EditText Etxt_matricula_secundaria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        imgbtnatras = findViewById(R.id.btnatras3);
        txt_dni = findViewById(R.id.TextVievUsuario);
        etxt_usuario = findViewById(R.id.PEEditTextUsuario);
        etxt_contraseña = findViewById(R.id.PEEditTextContraseña);
        etxt_matricula_principal = findViewById(R.id.PEEditTextMatriculaprin);
        Etxt_matricula_secundaria = findViewById(R.id.PEEditTextMatriculasecun1);
    }
    public void atrass (View view){
        Intent i = new Intent(this, PARKING_USUARIOS_P1.class);
        startActivity(i);
    }
}