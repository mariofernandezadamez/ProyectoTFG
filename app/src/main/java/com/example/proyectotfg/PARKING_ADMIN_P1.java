package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PARKING_ADMIN_P1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_admin_p1);

    }

    public void cambioPestaña1_P2(View view){
        Intent i = new Intent(this, PARKING_ADMIN_P2.class);
        startActivity(i);
    }

    public void cambioPestaña1_P3(View view){
        Intent i = new Intent(this, PARKING_ADMIN_P3.class);
        startActivity(i);
    }

    public void botones(View view){
        Intent i = new Intent(this, MODIFICACION_PLAZA.class);
        startActivity(i);
    }

}
