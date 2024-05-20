package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class REGISTRO_USUARIO extends AppCompatActivity {
    ImageButton btnatras;
    EditText etxt_Dni;
    EditText etxt_Usuario;
    EditText etxt_Contrasena;
    EditText etxt_Matricula_principal;
    EditText etxt_Matricula_secundaria;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        etxt_Dni = findViewById(R.id.REEditTextDNI);
        etxt_Usuario = findViewById(R.id.REEditTextUsuario);
        etxt_Contrasena = findViewById(R.id.REEditTextContraseña);
        etxt_Matricula_principal = findViewById(R.id.REEditTextMatriculaprin);
        etxt_Matricula_secundaria = findViewById(R.id.REEditTextMatriculasecun1);

        btnatras = findViewById(R.id.btnatras4);
        btnatras.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            }

        });
    }

    public void registrobtn(View view){
        Intent i = new Intent(this, INICIO_SESION.class);
        ejecutarServicio();
        startActivity(i);
    }


    private void ejecutarServicio(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.91.1/bbdd_tfg/insertado_registro.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operación existosa", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
    })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("num_documento",etxt_Dni.getText().toString());
                parametros.put("nombre",etxt_Usuario.getText().toString());
                parametros.put("contrasena",etxt_Contrasena.getText().toString());
                parametros.put("matricula_primaria",etxt_Matricula_principal.getText().toString());
                parametros.put("matricula_secundaria",etxt_Matricula_secundaria.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}