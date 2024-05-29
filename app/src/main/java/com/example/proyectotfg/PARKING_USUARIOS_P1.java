package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PARKING_USUARIOS_P1 extends AppCompatActivity {

    TextView txt_dni_p1;
    EditText etxt_usuario;
    EditText etxt_contraseña;
    EditText etxt_matricula_principal;
    EditText etxt_matricula_secundaria;
    RequestQueue requestQueue;

    //PARKING_USUARIO_P1
    Button btn_perfil, btn_planta1, btn_planta2, btn_planta3, btn_disponible01, btn_disponible02, btn_disponible03, btn_disponible04, btn_disponible05, btn_disponible06, btn_disponible07, btn_disponible08, btn_disponible09,
            btn_disponible10, btn_disponible11, btn_disponible12, btn_disponible13, btn_disponible14, btn_disponible15, btn_disponible16;

    String num_documento = "";
    String planta= "";
    String plaza = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_usuarios_p1);
        btn_perfil = findViewById(R.id.Perfil_P1);
        btn_planta1 = findViewById(R.id.Usuarios_P1_p1);
        btn_planta2 = findViewById(R.id.Usuarios_P1_p2);
        btn_planta3 = findViewById(R.id.Usuarios_P1_p3);
        btn_disponible01 = findViewById(R.id.usuariosBTNdispo01P1);
        btn_disponible02 = findViewById(R.id.usuariosBTNdispo02P1);
        btn_disponible03 = findViewById(R.id.usuariosBTNdispo03P1);
        btn_disponible04 = findViewById(R.id.usuariosBTNdispo04P1);
        btn_disponible05 = findViewById(R.id.usuariosBTNdispo05P1);
        btn_disponible06 = findViewById(R.id.usuariosBTNdispo06P1);
        btn_disponible07 = findViewById(R.id.usuariosBTNdispo07P1);
        btn_disponible08 = findViewById(R.id.usuariosBTNdispo08P1);
        btn_disponible09 = findViewById(R.id.usuariosBTNdispo09P1);
        btn_disponible10 = findViewById(R.id.usuariosBTNdispo10P1);
        btn_disponible11 = findViewById(R.id.usuariosBTNdispo11P1);
        btn_disponible12 = findViewById(R.id.usuariosBTNdispo12P1);
        btn_disponible13 = findViewById(R.id.usuariosBTNdispo13P1);
        btn_disponible14 = findViewById(R.id.usuariosBTNdispo14P1);
        btn_disponible15 = findViewById(R.id.usuariosBTNdispo15P1);
        btn_disponible16 = findViewById(R.id.usuariosBTNdispo16P1);

        //btn_disponible1.setBackgroundColor(Color.RED);

        StringRequest reservaRequest = new StringRequest(Request.Method.POST, "http://192.168.227.1/bbdd_tfg/verificar_reserva.php?=" + 1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // Asumiendo que la respuesta contiene un array JSON
                    String jsonArrayString = response.substring(response.indexOf("["), response.lastIndexOf("]") + 1);
                    JSONArray jsonArray = new JSONArray(jsonArrayString);

                    // Recorre cada objeto dentro del array
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int numPlaza = jsonObject.getInt("num_plaza");
                        boolean disponible = jsonObject.getBoolean("disponible");

                        // Actualiza la interfaz de usuario según la disponibilidad
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
        });

        //PERFIL USUARIO
        PERFIL_USUARIO perfilUsuario = new PERFIL_USUARIO();
        txt_dni_p1 = perfilUsuario.txt_dni;
        etxt_usuario = perfilUsuario.etxt_usuario;
        etxt_contraseña = perfilUsuario.etxt_contraseña;
        etxt_matricula_principal = perfilUsuario.etxt_matricula_principal;
        etxt_matricula_secundaria = perfilUsuario.Etxt_matricula_secundaria;

        requestQueue = Volley.newRequestQueue(this);
        System.out.println("Número de documento: " + num_documento);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("num_documento")) {
            num_documento = intent.getStringExtra("num_documento");
        }
        System.out.println("Número de documento: " + num_documento);
    }
    public void perfil (View view){
        Intent i = new Intent(this, PERFIL_USUARIO.class);
        rellenar_perfil();
        Toast.makeText(PARKING_USUARIOS_P1.this, "Usuario o constraseña incorrecto" ,Toast.LENGTH_SHORT).show();
        i.putExtra("num_documento", num_documento);
        startActivity(i);
    }
    public void planta1 (View view){
        Intent i = new Intent(this, PARKING_USUARIOS_P1.class);
        i.putExtra("num_documento", num_documento);
        startActivity(i);
    }
    public void planta2 (View view){
        Intent i = new Intent(this, PARKING_USUARIOS_P2.class);
        i.putExtra("num_documento", num_documento);
        startActivity(i);
    }
    public void planta3 (View view){
        Intent i = new Intent(this, PARKING_USUARIOS_P3.class);
        i.putExtra("num_documento", num_documento);
        startActivity(i);
    }
    public void reservas (View view){
        Intent i = new Intent(this, RESERVA_PLAZA.class);
        i.putExtra("num_documento", num_documento);
        obtenerPlazaPlanta(view);
        i.putExtra("plaza", plaza);
        i.putExtra("numero_planta", planta);
        startActivity(i);
    }

    public void rellenar_perfil(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://192.168.1.41/bbdd_tfg/mostrar_datos.php",null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String num_documento = response.getString("num_documento");
                    String nombre = response.getString("nombre");
                    String contrasena = response.getString("contrasena");
                    String matricula_primaria = response.getString("matricula_primaria");
                    String matricula_secundaria = response.getString("matricula_secundaria");

                    txt_dni_p1.setText(num_documento);
                    etxt_usuario.setText(nombre);
                    etxt_contraseña.setText(contrasena);
                    etxt_matricula_principal.setText(matricula_primaria);
                    etxt_matricula_secundaria.setText(matricula_secundaria);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
        }
    public void obtenerPlazaPlanta(View view) {
        int id = view.getId();

        String nombreRecurso = getResources().getResourceEntryName(id);

        plaza = nombreRecurso.substring(nombreRecurso.length() - 4, nombreRecurso.length() - 2);
        planta = nombreRecurso.substring(nombreRecurso.length() - 1);

        System.out.println("Plaza: " + plaza + ", Planta: " + planta);
    }
    private void updateUIForPlaza(int numPlaza, boolean disponible) {
        // Encuentra el botón correspondiente a la plaza
        int plazaButtonId = getPlazaButtonId(numPlaza);
        Button plazaButton = findViewById(plazaButtonId);

        if (plazaButton != null) {
            // Cambia el color y el estado del botón según la disponibilidad
            if (disponible) {
                plazaButton.setBackgroundColor(Color.GREEN);
                plazaButton.setClickable(true);
            } else {
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
