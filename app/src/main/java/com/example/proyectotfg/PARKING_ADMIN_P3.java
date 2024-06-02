package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PARKING_ADMIN_P3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_admin_p3);
    }

    public void botones3(View view){
        Intent i = new Intent(this, MODIFICACION_PLAZA.class);
        startActivity(i);
    }
}