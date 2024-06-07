package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    Button btnatras;
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
        btnatras = findViewById(R.id.btnatrasregistro);
    }

    public void registrobtn(View view){
        if (comp_campos_vacios()) {
            Toast.makeText(this, "Completa todos los campos obligatorios", Toast.LENGTH_SHORT).show();
        }else if (!comp_dni(etxt_Dni.getText().toString())){
            Toast.makeText(this, "Formato del DNI invalido", Toast.LENGTH_SHORT).show();
        }else if (!comp_matricula(etxt_Matricula_principal.getText().toString())) {
            Toast.makeText(this, "Formato de la Matrícula principal invalido", Toast.LENGTH_SHORT).show();
        } else if (!(etxt_Matricula_secundaria.getText().toString().isEmpty())) {
            if (!comp_matricula(etxt_Matricula_secundaria.getText().toString())){
                Toast.makeText(this, "Formato de la Matrícula secundaria invalido", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Todos lo campos son validos", Toast.LENGTH_SHORT).show();
                ejecutarServicio();
            }
    }else {
            Toast.makeText(this, "Todos lo campos son validos", Toast.LENGTH_SHORT).show();
            ejecutarServicio();
        }
    }

    public void atrasregistro(View view){
        Intent i = new Intent(this, INICIO_SESION.class);
        startActivity(i);
    }
    public boolean comp_campos_vacios(){
        String dni = etxt_Dni.getText().toString().trim();
        String usuario = etxt_Usuario.getText().toString().trim();
        String contrasena = etxt_Contrasena.getText().toString().trim();
        String matriculaPrincipal = etxt_Matricula_principal.getText().toString().trim();

        return dni.isEmpty() || usuario.isEmpty() || contrasena.isEmpty() || matriculaPrincipal.isEmpty();
    }

    public boolean comp_dni(String dni){

        if (dni.length() != 9){
            return false;
        }

        String num_dni = dni.substring(0,8);
        try {
            int num_dni_final = Integer.parseInt(num_dni);
        }catch (NumberFormatException e){
            return false;
        }


        String letra_dni = dni.substring(8);
        if ((letra_dni.contains("A")||letra_dni.contains("E")|| letra_dni.contains("I")||letra_dni.contains("O")||letra_dni.contains("U")||
                letra_dni.contains("1")||letra_dni.contains("2")||letra_dni.contains("3")||letra_dni.contains("4")|| letra_dni.contains("5")||
                letra_dni.contains("6")||letra_dni.contains("7")||letra_dni.contains("8")||letra_dni.contains("9")||letra_dni.contains("0")) || letra_dni.contains(letra_dni.toLowerCase())){
            return false;
        }

        return true;
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
        String letra_matricula_1 = letras_matricula.substring(0,1);
        String letra_matricula_2 = letras_matricula.substring(1,2);
        String letra_matricula_3 = letras_matricula.substring(2,3);
        System.out.println("L1: "+letra_matricula_1+ " L2: "+ letra_matricula_2+ " L3: "+ letra_matricula_3);
       if ((letras_matricula.contains("A") || letras_matricula.contains("E") || letras_matricula.contains("I") || letras_matricula.contains("O") || letras_matricula.contains("U") ||
               letras_matricula.contains("1") || letras_matricula.contains("2") || letras_matricula.contains("3") || letras_matricula.contains("4") || letras_matricula.contains("5") ||
               letras_matricula.contains("6") || letras_matricula.contains("7") || letras_matricula.contains("8") || letras_matricula.contains("9") || letras_matricula.contains("0")) ||
               letra_matricula_1.contains(letra_matricula_1.toLowerCase()) || letra_matricula_2.contains(letra_matricula_2.toLowerCase()) || letra_matricula_3.contains(letra_matricula_3.toLowerCase()) ){
           return false;
       }

       return true;
    }

    private void ejecutarServicio(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.227.1/bbdd_tfg/insertado_registro.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("El usuario ya existe en la BBDD")) {
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(REGISTRO_USUARIO.this, INICIO_SESION.class);
                    startActivity(i);
                }
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