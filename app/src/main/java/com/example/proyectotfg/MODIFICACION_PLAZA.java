package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MODIFICACION_PLAZA extends AppCompatActivity {
    ImageButton botonatras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificacion_plaza);
        //botonatras = findViewById(R.id.btnatras2);
        botonatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}