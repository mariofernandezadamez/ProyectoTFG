package com.example.proyectotfg;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.PixelCopy;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class PERFIL_USUARIO extends AppCompatActivity {
    ImageButton imgbtnatras;
    TextView txt_dni;
    EditText etxt_usuario;
    EditText etxt_contraseña;
    EditText etxt_matricula_principal;
    EditText Etxt_matricula_secundaria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        imgbtnatras = findViewById(R.id.btnatras3);
        txt_dni = findViewById(R.id.TextVievUsuario);
        etxt_usuario = findViewById(R.id.PEEditTextUsuario);
        etxt_contraseña = findViewById(R.id.PEEditTextContraseña);
        etxt_matricula_principal = findViewById(R.id.PEEditTextMatriculaprin);
        Etxt_matricula_secundaria = findViewById(R.id.PEEditTextMatriculasecun1);

        Intent intent1 = getIntent();
        if (intent1.hasExtra("num_documento")) {
            String num_documento = intent1.getStringExtra("num_documento");
            String usuario = intent1.getStringExtra("usuario");
            String contrasena = intent1.getStringExtra("contrasena");
            String matricula_principal = intent1.getStringExtra("matricula_principal");
            String matricula_secundaria = intent1.getStringExtra("matricula_secundaria");

            txt_dni.setText(num_documento);
            etxt_usuario.setText(usuario);
            etxt_contraseña.setText(contrasena);
            etxt_matricula_principal.setText(matricula_principal);
            Etxt_matricula_secundaria.setText(matricula_secundaria);

            Toast.makeText(getApplicationContext(), "Número de documento: " + num_documento, Toast.LENGTH_SHORT).show();
        }
    }

    public void atrass(View view) {
        Intent i = new Intent(this, PARKING_USUARIOS_P1.class);
        startActivity(i);
    }

    public void modificacion_usuario(View view){

        String num_documentos_mod = txt_dni.getText().toString();
        String usuario_mod = etxt_usuario.getText().toString();
        String contrasena_mod = etxt_contraseña.getText().toString();
        String matricula_1_mod = etxt_matricula_principal.getText().toString();
        String matricula_2_mod = Etxt_matricula_secundaria.getText().toString();

        System.out.println("Num_doc:"+num_documentos_mod+ " usuario:"+usuario_mod+ " contra:"+ contrasena_mod+ " mat_1:"+ matricula_1_mod+ " mat_2:"+matricula_2_mod );

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.227.1/bbdd_tfg/modificacion_perfil.php?num_documento=" + num_documentos_mod, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("Usuario modificado")){
                Toast.makeText(PERFIL_USUARIO.this, response, Toast.LENGTH_SHORT).show();
            }else {
                    Toast.makeText(PERFIL_USUARIO.this, response , Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error"+ error.getMessage() ,Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<>();
                parametros.put("num_documento",num_documentos_mod);
                parametros.put("nombre",usuario_mod);
                parametros.put("contrasena",contrasena_mod);
                parametros.put("matricula_primaria",matricula_1_mod);
                parametros.put("matricula_secundaria",matricula_2_mod);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(PERFIL_USUARIO.this);
        requestQueue.add(stringRequest);
    }

    }
