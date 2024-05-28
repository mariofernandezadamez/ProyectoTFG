package com.example.proyectotfg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.app.TimePickerDialog;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RESERVA_PLAZA extends AppCompatActivity {
    TextView Txtusuario;
    Spinner spmatriculas;
    Button btnHoraEntrada;
    Button btnHoraSalida;
    Button btnReservar;
    TextView tvHoraEntrada;
    TextView tvHoraSalida;
    String horaEntradaSeleccionada;
    String horaSalidaSeleccionada;
    ArrayAdapter<String> spinnerAdapter;
    ArrayList<String> matriculasList = new ArrayList<>();
    String num_documento;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_plaza);
        btnHoraEntrada = findViewById(R.id.btnHoraEntrada);
        btnHoraSalida = findViewById(R.id.btnHoraSalida);
        btnReservar = findViewById(R.id.btnReservar);
        tvHoraEntrada = findViewById(R.id.tvHoraEntrada);
        tvHoraSalida = findViewById(R.id.tvHoraSalida);
        Txtusuario = findViewById(R.id.TextVievUsuario);
        spmatriculas = findViewById(R.id.spinnermatriculas);

        Intent intent = getIntent();
        num_documento = intent.getStringExtra("num_documento");

        btnHoraEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarTimePicker(true);
            }
        });

        btnHoraSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarTimePicker(false);
            }
        });

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, matriculasList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spmatriculas.setAdapter(spinnerAdapter);
        System.out.println("Numero de documento paodkoawd= " + num_documento);
        cargarMatriculas(num_documento);

        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarReserva();
            }
        });
    }

    private void cargarMatriculas(String numDocumento) {
        System.out.println("Número de documento metodoo: " + numDocumento);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.227.1/bbdd_tfg/matriculas.php?num_documento=" + numDocumento, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    if (jsonObject.has("error")) {
                        Toast.makeText(getApplicationContext(), jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                    } else {
                        String matriculaPrimaria = jsonObject.getString("matricula_primaria");
                        String matriculaSecundaria = jsonObject.getString("matricula_secundaria");
                        String nombreUsuario = jsonObject.getString("nombre");

                        Txtusuario.setText(nombreUsuario);

                        if (!matriculaPrimaria.isEmpty()) {
                            matriculasList.add(matriculaPrimaria);
                        }
                        if (!matriculaSecundaria.isEmpty()) {
                            matriculasList.add(matriculaSecundaria);
                        }

                        spinnerAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error parsing JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("num_documento", numDocumento);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void mostrarTimePicker(final boolean esHoraEntrada) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String horaSeleccionada = formatTime(hourOfDay, minute);
                if (esHoraEntrada) {
                    horaEntradaSeleccionada = horaSeleccionada;
                    tvHoraEntrada.setText(horaSeleccionada);
                } else {
                    horaSalidaSeleccionada = horaSeleccionada;
                    tvHoraSalida.setText(horaSeleccionada);
                }
            }
        }, hour, minute, true); // El último argumento es para formato 24 horas
        timePickerDialog.show();
    }

    private String formatTime(int hourOfDay, int minute) {
        return String.format("%02d:%02d:00", hourOfDay, minute);
    }

    private void realizarReserva() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.227.1/bbdd_tfg/reservas.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("hora_entrada", horaEntradaSeleccionada);
                parametros.put("hora_salida", horaSalidaSeleccionada);
                parametros.put("matricula", spmatriculas.getSelectedItem().toString());
                parametros.put("coste_tiempo", "10"); // Deberías calcular el coste real
                parametros.put("plaza", "A1"); // Deberías obtener la plaza real
                parametros.put("numero_planta", "1"); // Deberías obtener la planta real
                parametros.put("usuarios_num_documento", num_documento);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
