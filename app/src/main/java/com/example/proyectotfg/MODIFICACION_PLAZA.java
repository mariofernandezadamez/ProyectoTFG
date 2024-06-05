package com.example.proyectotfg;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MODIFICACION_PLAZA extends AppCompatActivity {


    Button BtnHoraEntrada;
    Button BtnHoraSalida;
    TextView horaEntrada;
    TextView horaSalida;
    EditText usuarioTexto;
    EditText costeTotal;
    String plaza = "";
    String plantaSeleccionada;
    String plazaSeleccionada;
    String planta = "";
    String horaEntradaSeleccionada;
    String horaSalidaSeleccionada;
    EditText coche;
    String idBoton = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificacion_plaza);

        BtnHoraEntrada = findViewById(R.id.btnHoraEntrada1);
        BtnHoraSalida = findViewById(R.id.btnHoraSalida1);
        horaEntrada = findViewById(R.id.txtHoraEntrada);
        horaSalida = findViewById(R.id.txtHoraSalida);
        usuarioTexto = findViewById(R.id.PlainTextUsuario);
        costeTotal = findViewById(R.id.PlainTextCosteTotal);
        coche = findViewById(R.id.PlainTextCOCHE);

        Intent intente = getIntent();
        plaza = intente.getStringExtra("plaza");
        planta = intente.getStringExtra("numero_planta");
        idBoton = intente.getStringExtra("idBoton");

        Intent intent1 = getIntent();
        if (intent1.hasExtra("usuarios_num_documento")) {
            String num_documento = intent1.getStringExtra("usuarios_num_documento");
            String hora_entrada = intent1.getStringExtra("hora_entrada");
            String hora_salida = intent1.getStringExtra("hora_salida");
            String matricula = intent1.getStringExtra("matricula");
            String costeTotall = intent1.getStringExtra("coste_tiempo");

            usuarioTexto.setText(num_documento);
            horaEntrada.setText(hora_entrada);
            horaSalida.setText(hora_salida);
            coche.setText(matricula);
            costeTotal.setText(costeTotall);

            Toast.makeText(getApplicationContext(), "Número de documento: " + num_documento, Toast.LENGTH_SHORT).show();
        }

        BtnHoraEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarTimePicker(true);
            }
        });

        BtnHoraSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarTimePicker(false);
            }
        });
    }

    public String obtenerPlazaMODIFICACION(String idBoton){
        plazaSeleccionada = idBoton.substring(idBoton.length() - 4, idBoton.length() - 2);
        plazaSeleccionada = String.format("%02d", Integer.parseInt(plazaSeleccionada));
        return plazaSeleccionada;
    }


    public String obtenerPlantaMODIFICACION(String idBoton) {
        plantaSeleccionada = idBoton.substring(idBoton.length() - 1);
        return plantaSeleccionada;
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
                    horaEntrada.setText(horaSeleccionada);
                } else {
                    horaSalidaSeleccionada = horaSeleccionada;
                    horaSalida.setText(horaSeleccionada);
                }
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }

    private String formatTime(int hourOfDay, int minute) {
        return String.format("%02d:%02d:00", hourOfDay, minute);
    }

    public void modificacion_reserva(View view){

        String plazaSeleccionada = obtenerPlazaMODIFICACION(idBoton);
        String plantaSeleccionada = obtenerPlantaMODIFICACION(idBoton);

        String Usuario = usuarioTexto.getText().toString();
        String EntradaHora = horaEntrada.getText().toString();
        String EntradaSalida = horaSalida.getText().toString();
        String matricula = coche.getText().toString();
        String CosteTotal = costeTotal.getText().toString();

        System.out.println("DNI:"+Usuario+ " Hora de Entrada:"+EntradaHora+ " Hora de salida:"+ EntradaSalida+"Matricula" + matricula + " Coste total:"+ CosteTotal);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.73.1/bbdd_tfg/ModificarReserva.php?plaza=" + plazaSeleccionada + "&numero_planta=" + plantaSeleccionada, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("Reserva modificada")){
                    Toast.makeText(MODIFICACION_PLAZA.this, response, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MODIFICACION_PLAZA.this, response , Toast.LENGTH_SHORT).show();
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
                parametros.put("usuarios_num_documento",Usuario);
                parametros.put("hora_entrada",EntradaHora);
                parametros.put("hora_salida",EntradaSalida);
                parametros.put("matricula", matricula);
                parametros.put("coste_tiempo",CosteTotal);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MODIFICACION_PLAZA.this);
        requestQueue.add(stringRequest);
    }

}