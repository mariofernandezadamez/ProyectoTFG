package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class INICIO_SESION extends AppCompatActivity {
    Button btn_iniciar_sesion;
    TextView btnregistro;

    EditText etxt_dni;
    EditText etxt_contra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        btn_iniciar_sesion = findViewById(R.id.ISBtnIniciarSesion);
        btnregistro = findViewById(R.id.ISTextRegistrate);
        etxt_dni = findViewById(R.id.ISEditTextUsuario);
        etxt_contra = findViewById(R.id.ISEditTextContraseña);
    }
    public void inicio(View view){
        inicio_sesion();
    }

    public void registro(View view){
        Intent i = new Intent(this, REGISTRO_USUARIO.class);
        startActivity(i);
    }
    public void aboutus(View view){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://aparcafacil3.wordpress.com/"));
        startActivity(i);
    }

    public void inicio_sesion(){
        if (etxt_dni.getText().toString().equals("") && etxt_contra.getText().toString().equals("") ){
            Toast.makeText(getApplicationContext(), "Ambos campos están vacios", Toast.LENGTH_SHORT).show();
        } else if (etxt_dni.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "El campo del DNI está vacio", Toast.LENGTH_SHORT).show();
        }else if (etxt_contra.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "El campo de la contraseña esta vacio", Toast.LENGTH_SHORT).show();
        }else{
            final String numDocumento = etxt_dni.getText().toString();
            if (numDocumento == null || numDocumento.isEmpty()) {
                Toast.makeText(getApplicationContext(), "El número de documento está vacío", Toast.LENGTH_SHORT).show();
                return;
            }
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.1.41/bbdd_tfg/inicio_sesion.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String[] partes_respuesta = response.split("\\|");
                    String msg_Inicio_Sesion = partes_respuesta[0];
                    String msg_rolUsuario = partes_respuesta[1];

                    if (msg_Inicio_Sesion.contains("Inicio de sesión correcto")) {
                        if (msg_rolUsuario.contains("usuario")){
                            Intent intent_user = new Intent(getApplicationContext(),PARKING_USUARIOS_P1.class);
                            intent_user.putExtra("num_documento", etxt_dni.getText().toString());
                            startActivity(intent_user);
                        }else{
                            Intent intent_admin = new Intent(getApplicationContext(),RESERVA_PLAZA.class);
                            startActivity(intent_admin);
                        }

                        etxt_dni.setText("");
                        etxt_contra.setText("");
                    }else{
                        Toast.makeText(getApplicationContext(), "Usuario o constraseña incorrecto" ,Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error"+error.getMessage() ,Toast.LENGTH_SHORT).show();
                }
            }){
                protected  Map<String,String> getParams() throws AuthFailureError{
                    Map<String,String> parametros = new HashMap<>();
                    parametros.put("num_documento",etxt_dni.getText().toString());
                    parametros.put("contrasena",etxt_contra.getText().toString());
                    return parametros;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(INICIO_SESION.this);
            requestQueue.add(stringRequest);
        }
    }
}