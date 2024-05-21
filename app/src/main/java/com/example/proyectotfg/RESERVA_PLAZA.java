package com.example.proyectotfg;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.app.TimePickerDialog;
import android.widget.Button;
import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RESERVA_PLAZA extends AppCompatActivity {
    TextView flecha;
    private Button btnHoraEntrada;
    private Button btnHoraSalida;
    private TextView tvHoraEntrada;
    private TextView tvHoraSalida;
    private String horaEntradaSeleccionada;
    private String horaSalidaSeleccionada;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_plaza);
        btnHoraEntrada = findViewById(R.id.btnHoraEntrada);
        btnHoraSalida = findViewById(R.id.btnHoraSalida);
        tvHoraEntrada = findViewById(R.id.tvHoraEntrada);
        tvHoraSalida = findViewById(R.id.tvHoraSalida);

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
    }

    private void mostrarTimePicker(final boolean esHoraEntrada) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
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

    private void ejecutar() {
        // Aquí puedes enviar las horas seleccionadas a la base de datos
        // Por ejemplo, utilizando Volley como lo hiciste anteriormente
        // Puedes usar horaEntradaSeleccionada y horaSalidaSeleccionada para obtener las horas seleccionadas
    }
}
