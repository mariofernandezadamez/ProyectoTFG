package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

public class INICIO_SESION extends AppCompatActivity {
    Button btn_iniciar_sesion;
    TextView btnregistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        btn_iniciar_sesion = findViewById(R.id.ISBtnIniciarSesion);
        btnregistro = findViewById(R.id.ISTextRegistrate);
    }
    public void inicio(View view){
        Intent i = new Intent(this, PARKING_USUARIOS_P1.class);
        startActivity(i);
    }

    public void registro(View view){
        Intent i = new Intent(this, REGISTRO_USUARIO.class);
        startActivity(i);
    }
    public void aboutus(View view){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://aparcafacil3.wordpress.com/"));
        startActivity(i);
    }

    //private void ejecutarInicio_Sesion(){
       // StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.168.91.1/bbdd_tfg/inicio_sesion.php", new Response.Listener<String>() {
        //    @Override
        //    public void onResponse(String response) {
             //   if (!response.isEmpty()){
             //       Intent intent = new Intent(getApplicationContext(),PARKING_USUARIOS_P1.class);
              //      startActivity(intent);
             //   }
             //   Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
           // }
     //   }, new Response.ErrorListener() {
        //    @Override
         //   public void onErrorResponse(VolleyError error) {

          //  }
      //  })
       // {
       //     protected Map
       // }

  //  }
}