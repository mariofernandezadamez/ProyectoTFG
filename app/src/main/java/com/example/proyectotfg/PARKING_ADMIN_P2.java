package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PARKING_ADMIN_P2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_admin_p2);
    }

    public void botones2(View view){
        Intent i = new Intent(this, MODIFICACION_PLAZA.class);
        startActivity(i);
    }
}