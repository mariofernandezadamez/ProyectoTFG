package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ADMIN_HOME extends AppCompatActivity {
    ImageView imv;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin_home);

        imv = findViewById(R.id.btnatras1);
        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ADMIN_HOME.this, INICIO_SESION.class);
                startActivity(intent);
            }
        });
    }
}