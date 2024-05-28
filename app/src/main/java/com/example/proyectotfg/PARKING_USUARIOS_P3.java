package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PARKING_USUARIOS_P3 extends AppCompatActivity {
    Button btn_perfil_p3;
    Button btn_planta1, btn_planta2, btn_planta3, btn_disponible1, btn_disponible2, btn_disponible3, btn_disponible4, btn_disponible5, btn_disponible6, btn_disponible7, btn_disponible8, btn_disponible9,
            btn_disponible10, btn_disponible11, btn_disponible12, btn_disponible13, btn_disponible14, btn_disponible15, btn_disponible16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_usuarios_p3);
        btn_perfil_p3 = findViewById(R.id.Perfil_P3);
        btn_planta1 = findViewById(R.id.Usuarios_P3_p1);
        btn_planta2 = findViewById(R.id.Usuarios_P3_p2);
        btn_planta3 = findViewById(R.id.Usuarios_P3_p3);
        btn_disponible1 = findViewById(R.id.usuariosBTNdispo1P3);
        btn_disponible2 = findViewById(R.id.usuariosBTNdispo2P3);
        btn_disponible3 = findViewById(R.id.usuariosBTNdispo3P3);
        btn_disponible4 = findViewById(R.id.usuariosBTNdispo4P3);
        btn_disponible5 = findViewById(R.id.usuariosBTNdispo5P3);
        btn_disponible6 = findViewById(R.id.usuariosBTNdispo6P3);
        btn_disponible7 = findViewById(R.id.usuariosBTNdispo7P3);
        btn_disponible8 = findViewById(R.id.usuariosBTNdispo8P3);
        btn_disponible9 = findViewById(R.id.usuariosBTNdispo9P3);
        btn_disponible10 = findViewById(R.id.usuariosBTNdispo10P3);
        btn_disponible11 = findViewById(R.id.usuariosBTNdispo11P3);
        btn_disponible12 = findViewById(R.id.usuariosBTNdispo12P3);
        btn_disponible13 = findViewById(R.id.usuariosBTNdispo13P3);
        btn_disponible14 = findViewById(R.id.usuariosBTNdispo14P3);
        btn_disponible15 = findViewById(R.id.usuariosBTNdispo15P3);
        btn_disponible16 = findViewById(R.id.usuariosBTNdispo16P3);
    }
    public void perfil3 (View view){
        Intent i = new Intent(this, PERFIL_USUARIO.class);
        startActivity(i);
    }
    public void planta1_3 (View view){
        Intent i = new Intent(this, PARKING_USUARIOS_P1.class);
        startActivity(i);
    }
    public void planta2_3 (View view){
        Intent i = new Intent(this, PARKING_USUARIOS_P2.class);
        startActivity(i);
    }
    public void planta3_3 (View view){
        Intent i = new Intent(this, PARKING_USUARIOS_P3.class);
        startActivity(i);
    }
    public void reservas_3 (View view){
        Intent i = new Intent(this, RESERVA_PLAZA.class);
        startActivity(i);
    }
}