package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class PARKING_USUARIOS_P3 extends AppCompatActivity {
    Button btn_perfil_p3;
    Button btn_planta1, btn_planta2, btn_planta3, btn_disponible1, btn_disponible2, btn_disponible3, btn_disponible4, btn_disponible5, btn_disponible6, btn_disponible7, btn_disponible8, btn_disponible9,
            btn_disponible10, btn_disponible11, btn_disponible12, btn_disponible13, btn_disponible14, btn_disponible15, btn_disponible16;
    String num_documento = "";
    String planta= "";
    String plaza = "";
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_usuarios_p3);
        btn_perfil_p3 = findViewById(R.id.Perfil_P3);
        btn_planta1 = findViewById(R.id.Usuarios_P3_p1);
        btn_planta2 = findViewById(R.id.Usuarios_P3_p2);
        btn_planta3 = findViewById(R.id.Usuarios_P3_p3);
        btn_disponible1 = findViewById(R.id.usuariosBTNdispo01P3);
        btn_disponible2 = findViewById(R.id.usuariosBTNdispo02P3);
        btn_disponible3 = findViewById(R.id.usuariosBTNdispo03P3);
        btn_disponible4 = findViewById(R.id.usuariosBTNdispo04P3);
        btn_disponible5 = findViewById(R.id.usuariosBTNdispo05P3);
        btn_disponible6 = findViewById(R.id.usuariosBTNdispo06P3);
        btn_disponible7 = findViewById(R.id.usuariosBTNdispo07P3);
        btn_disponible8 = findViewById(R.id.usuariosBTNdispo08P3);
        btn_disponible9 = findViewById(R.id.usuariosBTNdispo09P3);
        btn_disponible10 = findViewById(R.id.usuariosBTNdispo10P3);
        btn_disponible11 = findViewById(R.id.usuariosBTNdispo11P3);
        btn_disponible12 = findViewById(R.id.usuariosBTNdispo12P3);
        btn_disponible13 = findViewById(R.id.usuariosBTNdispo13P3);
        btn_disponible14 = findViewById(R.id.usuariosBTNdispo14P3);
        btn_disponible15 = findViewById(R.id.usuariosBTNdispo15P3);
        btn_disponible16 = findViewById(R.id.usuariosBTNdispo16P3);


        requestQueue = Volley.newRequestQueue(this);
        System.out.println("Número de documento: " + num_documento);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("num_documento")) {
            num_documento = intent.getStringExtra("num_documento");
        }
        System.out.println("Número de documento: " + num_documento);
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
        i.putExtra("num_documento", num_documento);
        obtenerPlazaPlanta(view);
        i.putExtra("plaza", plaza);
        i.putExtra("numero_planta", planta);
        startActivity(i);
    }

    public void obtenerPlazaPlanta(View view) {
        int id = view.getId();

        String nombreRecurso = getResources().getResourceEntryName(id);

        plaza = nombreRecurso.substring(nombreRecurso.length() - 4, nombreRecurso.length() - 2);
        planta = nombreRecurso.substring(nombreRecurso.length() - 1);

        System.out.println("Plaza: " + plaza + ", Planta: " + planta);
    }
}