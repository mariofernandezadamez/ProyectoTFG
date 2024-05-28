package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PARKING_USUARIOS_P1 extends AppCompatActivity {
    Button btn_planta1, btn_planta2, btn_planta3, btn_disponible1, btn_disponible2, btn_disponible3, btn_disponible4, btn_disponible5, btn_disponible6, btn_disponible7, btn_disponible8, btn_disponible9,
            btn_disponible10, btn_disponible11, btn_disponible12, btn_disponible13, btn_disponible14, btn_disponible15, btn_disponible16;
    ImageButton btn_perfil;
    String num_documento = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_usuarios_p1);
        btn_perfil = findViewById(R.id.imageButton5);
        btn_planta1 = findViewById(R.id.Usuarios_P1_p1);
        btn_planta2 = findViewById(R.id.Usuarios_P1_p2);
        btn_planta3 = findViewById(R.id.Usuarios_P1_p3);
        btn_disponible1 = findViewById(R.id.usuariosBTNdispo1P1);
        btn_disponible2 = findViewById(R.id.usuariosBTNdispo2P1);
        btn_disponible3 = findViewById(R.id.usuariosBTNdispo3P1);
        btn_disponible4 = findViewById(R.id.usuariosBTNdispo4P1);
        btn_disponible5 = findViewById(R.id.usuariosBTNdispo5P1);
        btn_disponible6 = findViewById(R.id.usuariosBTNdispo6P1);
        btn_disponible7 = findViewById(R.id.usuariosBTNdispo7P1);
        btn_disponible8 = findViewById(R.id.usuariosBTNdispo8P1);
        btn_disponible9 = findViewById(R.id.usuariosBTNdispo9P1);
        btn_disponible10 = findViewById(R.id.usuariosBTNdispo10P1);
        btn_disponible11 = findViewById(R.id.usuariosBTNdispo11P1);
        btn_disponible12 = findViewById(R.id.usuariosBTNdispo12P1);
        btn_disponible13 = findViewById(R.id.usuariosBTNdispo13P1);
        btn_disponible14 = findViewById(R.id.usuariosBTNdispo14P1);
        btn_disponible15 = findViewById(R.id.usuariosBTNdispo15P1);
        btn_disponible16 = findViewById(R.id.usuariosBTNdispo16P1);
        System.out.println("Número de documento: " + num_documento);

        // Obtener el número de documento si se pasa como extra en el Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("num_documento")) {
            num_documento = intent.getStringExtra("num_documento");
        }
        System.out.println("Número de documento: " + num_documento);
    }

    public void perfil(View view) {
        Intent i = new Intent(this, PERFIL_USUARIO.class);
        i.putExtra("num_documento", num_documento);
        startActivity(i);
    }

    public void planta1(View view) {
        Intent i = new Intent(this, PARKING_USUARIOS_P1.class);
        i.putExtra("num_documento", num_documento);
        startActivity(i);
    }

    public void planta2(View view) {
        Intent i = new Intent(this, PARKING_USUARIOS_P2.class);
        i.putExtra("num_documento", num_documento);
        startActivity(i);
    }

    public void planta3(View view) {
        Intent i = new Intent(this, PARKING_USUARIOS_P3.class);
        i.putExtra("num_documento", num_documento);
        startActivity(i);
    }

    public void reservas(View view) {
        Intent i = new Intent(this, RESERVA_PLAZA.class);
        i.putExtra("num_documento", num_documento);
        startActivity(i);
    }
}
