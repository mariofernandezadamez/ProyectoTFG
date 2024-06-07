package com.example.proyectotfg;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.PixelCopy;
import android.view.View;
import android.widget.Button;
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
    Button btnatras, btncerrarsesion;
    TextView txt_dni;
    EditText etxt_usuario;
    EditText etxt_contraseña;
    EditText etxt_matricula_principal;
    EditText Etxt_matricula_secundaria;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        txt_dni = findViewById(R.id.TextVievUsuario);
        etxt_usuario = findViewById(R.id.PEEditTextUsuario);
        etxt_contraseña = findViewById(R.id.PEEditTextContraseña);
        etxt_matricula_principal = findViewById(R.id.PEEditTextMatriculaprin);
        Etxt_matricula_secundaria = findViewById(R.id.PEEditTextMatriculasecun1);
        btnatras = findViewById(R.id.btnatrasperfil);
        btncerrarsesion = findViewById(R.id.btncerrarsesion);

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

        }
    }

    public void cerra_sesion(View view){
        Intent i = new Intent(this, INICIO_SESION.class);
        Toast.makeText(this, "Sesión cerrada correctamente", Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    public void atrass(View view) {
        Intent i = new Intent(this, PARKING_USUARIOS_P1.class);
        startActivity(i);
    }

    public void btn_modificar(View view){
        if (comp_campos_vacios()){
            Toast.makeText(this, "Completa todos los campos obligatorios", Toast.LENGTH_SHORT).show();
        } else if (!comp_matricula(etxt_matricula_principal.getText().toString())) {
            Toast.makeText(this, "Formato de la Matrícula principal invalido", Toast.LENGTH_SHORT).show();
        } else if (etxt_matricula_principal.getText().toString().isEmpty()) {
            if (!comp_matricula(Etxt_matricula_secundaria.getText().toString())) {
                Toast.makeText(this, "Formato de la Matrícula secundaria invalido", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Todos lo campos son validos", Toast.LENGTH_SHORT).show();
            modificacion_usuario();
        }
    }

    public boolean comp_campos_vacios(){

        String usuario = etxt_usuario.getText().toString().trim();
        String contrasena = etxt_contraseña.getText().toString().trim();
        String matriculaPrincipal = etxt_matricula_principal.getText().toString().trim();

        return usuario.isEmpty() || contrasena.isEmpty() || matriculaPrincipal.isEmpty();
    }

    public boolean comp_matricula(String matricula){

        if (matricula.length() != 7){
            return false;
        }

        String num_matricula = matricula.substring(0,4);
        try {
            int num_matricula_final = Integer.parseInt(num_matricula);
        }catch (NumberFormatException e){
            return false;
        }

        String letras_matricula = matricula.substring(4,7);
        if ((letras_matricula.contains("a") || letras_matricula.contains("e") || letras_matricula.contains("i") || letras_matricula.contains("o") || letras_matricula.contains("u") ||
                letras_matricula.contains("1") || letras_matricula.contains("2") || letras_matricula.contains("3") || letras_matricula.contains("4") || letras_matricula.contains("5") ||
                letras_matricula.contains("6") || letras_matricula.contains("7") || letras_matricula.contains("8") || letras_matricula.contains("9") || letras_matricula.contains("0")) || letras_matricula.equals(letras_matricula.toLowerCase())){
            return false;
        }

        return true;
    }

    public void modificacion_usuario(){

        String num_documentos_mod = txt_dni.getText().toString();
        String usuario_mod = etxt_usuario.getText().toString();
        String contrasena_mod = etxt_contraseña.getText().toString();
        String matricula_1_mod = etxt_matricula_principal.getText().toString();
        String matricula_2_mod = Etxt_matricula_secundaria.getText().toString();

        System.out.println("Num_doc:"+num_documentos_mod+ " usuario:"+usuario_mod+ " contra:"+ contrasena_mod+ " mat_1:"+ matricula_1_mod+ " mat_2:"+matricula_2_mod );

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.1.41/bbdd_tfg/modificacion_perfil.php?num_documento=" + num_documentos_mod, new Response.Listener<String>() {
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
