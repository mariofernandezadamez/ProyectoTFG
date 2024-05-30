package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PARKING_USUARIOS_P2 extends AppCompatActivity {

    private static final int REQUEST_CODE_RESERVA = 1;
    Button  btn_planta1, btn_planta2, btn_planta3, btn_disponible1, btn_disponible2, btn_disponible3, btn_disponible4, btn_disponible5, btn_disponible6, btn_disponible7, btn_disponible8, btn_disponible9,
            btn_disponible10, btn_disponible11, btn_disponible12, btn_disponible13, btn_disponible14, btn_disponible15, btn_disponible16;

    RequestQueue requestQueue;
    Button btn_perfil_p2;
    String num_documento = "";
    String planta= "";
    String plaza = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_usuarios_p2);
        btn_perfil_p2 = findViewById(R.id.Perfil_P2);
        btn_planta1 = findViewById(R.id.Usuarios_P2_p1);
        btn_planta2 = findViewById(R.id.Usuarios_P2_p2);
        btn_planta3 = findViewById(R.id.Usuarios_P2_p3);
        btn_disponible1 = findViewById(R.id.usuariosBTNdispo01P2);
        btn_disponible2 = findViewById(R.id.usuariosBTNdispo02P2);
        btn_disponible3 = findViewById(R.id.usuariosBTNdispo03P2);
        btn_disponible4 = findViewById(R.id.usuariosBTNdispo04P2);
        btn_disponible5 = findViewById(R.id.usuariosBTNdispo05P2);
        btn_disponible6 = findViewById(R.id.usuariosBTNdispo06P2);
        btn_disponible7 = findViewById(R.id.usuariosBTNdispo07P2);
        btn_disponible8 = findViewById(R.id.usuariosBTNdispo08P2);
        btn_disponible9 = findViewById(R.id.usuariosBTNdispo09P2);
        btn_disponible10 = findViewById(R.id.usuariosBTNdispo10P2);
        btn_disponible11 = findViewById(R.id.usuariosBTNdispo11P2);
        btn_disponible12 = findViewById(R.id.usuariosBTNdispo12P2);
        btn_disponible13 = findViewById(R.id.usuariosBTNdispo13P2);
        btn_disponible14 = findViewById(R.id.usuariosBTNdispo14P2);
        btn_disponible15 = findViewById(R.id.usuariosBTNdispo15P2);
        btn_disponible16 = findViewById(R.id.usuariosBTNdispo16P2);

        obtenerEstadoPlazas();
        requestQueue = Volley.newRequestQueue(this);
        System.out.println("Número de documento: " + num_documento);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("num_documento")) {
            num_documento = intent.getStringExtra("num_documento");
        }
        System.out.println("Número de documento: " + num_documento);

    }
    public void perfil2 (View view){
        Intent i = new Intent(this, PERFIL_USUARIO.class);
        rellenar_perfil();
        startActivity(i);
    }
    public void planta1_2 (View view){
        Intent i = new Intent(this, PARKING_USUARIOS_P1.class);
        startActivity(i);
    }
    public void planta2_2 (View view){
        Intent i = new Intent(this, PARKING_USUARIOS_P2.class);
        startActivity(i);
    }
    public void planta3_2 (View view){
        Intent i = new Intent(this, PARKING_USUARIOS_P3.class);
        startActivity(i);
    }
    public void reservas_2 (View view){
        Intent i = new Intent(this, RESERVA_PLAZA.class);
        i.putExtra("num_documento", num_documento);
        obtenerPlazaPlanta(view);
        i.putExtra("plaza", plaza);
        i.putExtra("numero_planta", planta);
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_RESERVA && resultCode == RESULT_OK) {
            // Si se devuelve un resultado exitoso desde RESERVA_PLAZA, actualiza el estado de las plazas
            obtenerEstadoPlazas();
        }
    }

    public void rellenar_perfil() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.168.227.1/bbdd_tfg/mostrar_datos.php?num_documento=" + num_documento, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String[] partes_conjuntas = response.split("\\:");

                String msg_valor = partes_conjuntas[1];
                String msg_valor_2 = partes_conjuntas[3];
                String msg_valor_3 = partes_conjuntas[5];
                String msg_valor_4 = partes_conjuntas[7];
                String msg_valor_5 = partes_conjuntas[9];

                Intent intent = new Intent(PARKING_USUARIOS_P2.this, PERFIL_USUARIO.class);
                intent.putExtra("num_documento", msg_valor);
                intent.putExtra("usuario",msg_valor_2);
                intent.putExtra("contrasena",msg_valor_3);
                intent.putExtra("matricula_principal",msg_valor_4);
                intent.putExtra("matricula_secundaria",msg_valor_5);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error"+ error.getMessage() ,Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void obtenerEstadoPlazas() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.227.1/bbdd_tfg/verificar_reserva.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int numPlaza = jsonObject.getInt("num_plaza");
                        boolean disponible = jsonObject.getBoolean("disponible");

                        updateUIForPlaza(numPlaza, disponible);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error al parsear JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("numero_planta", "1");
                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


    public void obtenerPlazaPlanta(View view) {
        int id = view.getId();

        String nombreRecurso = getResources().getResourceEntryName(id);

        plaza = nombreRecurso.substring(nombreRecurso.length() - 4, nombreRecurso.length() - 2);
        planta = nombreRecurso.substring(nombreRecurso.length() - 1);

        System.out.println("Plaza: " + plaza + ", Planta: " + planta);
    }

    private void updateUIForPlaza(int numPlaza, boolean disponible) {
        int plazaButtonId = getPlazaButtonId(numPlaza);
        Button plazaButton = findViewById(plazaButtonId);

        if (plazaButton != null) {
            if (disponible) {
                // Plaza disponible
                plazaButton.setBackgroundColor(Color.GREEN);
                plazaButton.setClickable(true);
            } else {
                // Plaza no disponible
                plazaButton.setBackgroundColor(Color.RED);
                plazaButton.setClickable(false);
            }
        } else {
            Log.e("UI Update", "Button ID not found for plaza number: " + numPlaza);
        }
    }


    private int getPlazaButtonId(int numPlaza) {
        // Genera el nombre del ID del botón basado en el número de plaza
        String buttonIdName = "usuariosBTNdispo" + String.format("%02d", numPlaza) + "P1";
        return getResources().getIdentifier(buttonIdName, "id", getPackageName());
    }
}