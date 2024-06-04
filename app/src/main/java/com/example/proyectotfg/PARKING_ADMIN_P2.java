package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Map;

public class PARKING_ADMIN_P2 extends AppCompatActivity {
    Button btn_planta1, btn_planta2, btn_planta3, btn_disponible01, btn_disponible02, btn_disponible03, btn_disponible04, btn_disponible05, btn_disponible06, btn_disponible07, btn_disponible08, btn_disponible09,
            btn_disponible10, btn_disponible11, btn_disponible12, btn_disponible13, btn_disponible14, btn_disponible15, btn_disponible16;
    String planta= "";
    String plaza = "";
    PARKING_UTIL pkUtil = new PARKING_UTIL();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_admin_p2);
        btn_planta1 = findViewById(R.id.AdminPlanta1P3);
        btn_planta2 = findViewById(R.id.AdminPlanta2P3);
        btn_planta3 = findViewById(R.id.AdminPlanta3P3);
        btn_disponible01 = findViewById(R.id.adminBTNdispo01P2);
        btn_disponible02 = findViewById(R.id.adminBTNdispo02P2);
        btn_disponible03 = findViewById(R.id.adminBTNdispo03P2);
        btn_disponible04 = findViewById(R.id.adminBTNdispo04P2);
        btn_disponible05 = findViewById(R.id.adminBTNdispo05P2);
        btn_disponible06 = findViewById(R.id.adminBTNdispo06P2);
        btn_disponible07 = findViewById(R.id.adminBTNdispo07P2);
        btn_disponible08 = findViewById(R.id.adminBTNdispo08P2);
        btn_disponible09 = findViewById(R.id.adminBTNdispo09P2);
        btn_disponible10 = findViewById(R.id.adminBTNdispo10P2);
        btn_disponible11 = findViewById(R.id.adminBTNdispo11P2);
        btn_disponible12 = findViewById(R.id.adminBTNdispo12P2);
        btn_disponible13 = findViewById(R.id.adminBTNdispo13P2);
        btn_disponible14 = findViewById(R.id.adminBTNdispo14P2);
        btn_disponible15 = findViewById(R.id.adminBTNdispo15P2);
        btn_disponible16 = findViewById(R.id.adminBTNdispo16P2);
        pkUtil.obtenerEstadoPlazaPlantaAdmin2(this,"1",this);
    }
    public void cambioPestaña2_P1(View view){
        Intent i = new Intent(this, PARKING_ADMIN_P1.class);
        startActivity(i);
    }
    public void cambioPestaña2_P3(View view){
        Intent i = new Intent(this, PARKING_ADMIN_P3.class);
        startActivity(i);
    }

    public void botones2(View view){
        Intent i = new Intent(this, MODIFICACION_PLAZA.class);
        obtenerPlazaPlanta(view);
        i.putExtra("plaza", plaza);
        i.putExtra("numero_planta", planta);
        startActivity(i);
    }

    public void updateUIWithPlazas(Map<String, String> plazasDisponibles) {
        for (Map.Entry<String, String> entry : plazasDisponibles.entrySet()) {
            int numPlaza = getPlazaButtonId(Integer.parseInt(entry.getKey()));
            Button plazaButton = findViewById(numPlaza);
            if (plazaButton != null) {
                if ("1".equals(entry.getValue())) {
                    plazaButton.setBackgroundColor(Color.GREEN);
                    plazaButton.setClickable(true);
                } else {
                    plazaButton.setBackgroundColor(Color.RED);
                    plazaButton.setClickable(false);
                }
            } else {
                Log.e("UI Update", "Button ID not found for plaza number: " + numPlaza);
            }
        }
    }

    public int getPlazaButtonId(int numPlaza) {
        String buttonIdName = "adminBTNdispo" + String.format("%02d", numPlaza) + "P2";
        int id = getResources().getIdentifier(buttonIdName, "id", getPackageName());
        if (id == 0) {
            Log.e("getPlazaButtonId", "Button ID not found for plaza number: " + numPlaza + " with name: " + buttonIdName);
        }
        return id;
    }

    public void obtenerPlazaPlanta(View view) {
        int id = view.getId();

        String nombreRecurso = getResources().getResourceEntryName(id);

        plaza = nombreRecurso.substring(nombreRecurso.length() - 4, nombreRecurso.length() - 2);
        planta = nombreRecurso.substring(nombreRecurso.length() - 1);

        System.out.println("Plaza: " + plaza + ", Planta: " + planta);
    }
}