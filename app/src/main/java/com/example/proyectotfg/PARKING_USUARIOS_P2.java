package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PARKING_USUARIOS_P2 extends AppCompatActivity {
    Button btn_perfil_p2;
    Button  btn_planta1, btn_planta2, btn_planta3, btn_disponible1, btn_disponible2, btn_disponible3, btn_disponible4, btn_disponible5, btn_disponible6, btn_disponible7, btn_disponible8, btn_disponible9,
            btn_disponible10, btn_disponible11, btn_disponible12, btn_disponible13, btn_disponible14, btn_disponible15, btn_disponible16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_usuarios_p2);
        btn_perfil_p2 = findViewById(R.id.Perfil_P2);
        btn_planta1 = findViewById(R.id.Usuarios_P2_p1);
        btn_planta2 = findViewById(R.id.Usuarios_P2_p2);
        btn_planta3 = findViewById(R.id.Usuarios_P2_p3);
        btn_disponible1 = findViewById(R.id.usuariosBTNdispo1P2);
        btn_disponible2 = findViewById(R.id.usuariosBTNdispo2P2);
        btn_disponible3 = findViewById(R.id.usuariosBTNdispo3P2);
        btn_disponible4 = findViewById(R.id.usuariosBTNdispo4P2);
        btn_disponible5 = findViewById(R.id.usuariosBTNdispo5P2);
        btn_disponible6 = findViewById(R.id.usuariosBTNdispo6P2);
        btn_disponible7 = findViewById(R.id.usuariosBTNdispo7P2);
        btn_disponible8 = findViewById(R.id.usuariosBTNdispo8P2);
        btn_disponible9 = findViewById(R.id.usuariosBTNdispo9P2);
        btn_disponible10 = findViewById(R.id.usuariosBTNdispo10P2);
        btn_disponible11 = findViewById(R.id.usuariosBTNdispo11P2);
        btn_disponible12 = findViewById(R.id.usuariosBTNdispo12P2);
        btn_disponible13 = findViewById(R.id.usuariosBTNdispo13P2);
        btn_disponible14 = findViewById(R.id.usuariosBTNdispo14P2);
        btn_disponible15 = findViewById(R.id.usuariosBTNdispo15P2);
        btn_disponible16 = findViewById(R.id.usuariosBTNdispo16P2);
    }
    public void perfil2 (View view){
        Intent i = new Intent(this, PERFIL_USUARIO.class);
        startActivity(i);
    }
    public void planta1_2 (View view){
        Intent i = new Intent(this, PARKING_USUARIOS_P1.class);
        startActivity(i);
    }
    public void planta2_2 (View view){
        Intent i = new Intent(this, PARKING_USUARIOS_P2.class);
        startActivity(i);
    }
    public void planta3_2 (View view){
        Intent i = new Intent(this, PARKING_USUARIOS_P3.class);
        startActivity(i);
    }
    public void reservas_2 (View view){
        Intent i = new Intent(this, RESERVA_PLAZA.class);
        startActivity(i);
    }
}