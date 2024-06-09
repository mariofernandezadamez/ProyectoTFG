package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class GRAF_LINEAL_2 extends AppCompatActivity {

    RequestQueue requestQueue;
    LineChart lineChart;
    List<String> valores;
    List<Entry> linea = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graf_lineal2);

        lineChart = findViewById(R.id.chart);

        rellenar_grafico_linear_2();
    }
    public void atrasgraf4(View view){
        Intent i = new Intent(this, MENU_GRAFICOS.class);
        startActivity(i);
    }
    public void grafico_lineal_2(){
        // COLORES PARA APLICAR
        int color_negro = getResources().getColor(R.color.black);
        int color_blanco = getResources().getColor(R.color.white);

        // EJE X
        XAxis xAxis = lineChart.getXAxis();
        // QUE SE PONGAN LOS VALORES ABAJO
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(valores));
        // SEPARACION ENTRE LOS VALORES
        xAxis.setGranularity(1f);
        xAxis.setGridColor(color_blanco);
        // CAMBIAR EL COLOR DE LOS VALORES X
        xAxis.setTextColor(color_blanco);
        // LE PONEMOS UN TAMAÑO DE 12 LABELS
        xAxis.setLabelCount(12);

        // EJE Y
        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(30f);
        yAxis.setAxisLineColor(color_blanco);
        // CAMBIAR EL COLOR DE LOS VALORES DE Y
        yAxis.setTextColor(color_blanco);
        // QUITAR LOS VALORES DE LA DCH
        YAxis yAxisdch = lineChart.getAxisRight();
        yAxisdch.setDrawLabels(false);
        yAxis.setGridColor(color_blanco);


        // CONFIGURACIÓN DE LA LINEA, CIRCULO Y TXT DEL CIRUCLO
        LineDataSet dataSet = new LineDataSet(linea, "");
        dataSet.setColors(color_blanco);
        dataSet.setValueTextColor(color_blanco);
        dataSet.setValueTextSize(10f);
        dataSet.setCircleSize(5f);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

        // CON ESTO SE QUITA LA DESCRIPCIÓN
        lineChart.getDescription().setEnabled(false);

        // CON ESTO SE QUITA LA LEYENDA
        lineChart.getLegend().setEnabled(false);

        // DARLE TAMAÑO A LA GRÁFICA
        ViewGroup.LayoutParams layoutParams = lineChart.getLayoutParams();
        layoutParams.width = 1075;
        layoutParams.height = 1000;
        lineChart.setLayoutParams(layoutParams);

        lineChart.invalidate();
    }

    public void rellenar_grafico_linear_2() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.168.1.41/bbdd_tfg/grafico_lineal_2.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String[] partes_conjuntas = response.split("&");
                List<String> horas = new ArrayList<>();
                // Empieza desde el 1 porque el 0 es "Conexión exitosa" y hace de 2 en dos porque tiene que ser impar
                for (int i = 1; i < partes_conjuntas.length; i += 2) {
                    horas.add(partes_conjuntas[i]);
                }
                valores = horas;

                List<Integer> cantidad_horas = new ArrayList<>();
                for (int i=2;i<partes_conjuntas.length; i +=2){
                    cantidad_horas.add(Integer.parseInt(partes_conjuntas[i]));
                }
                for (int j=0; j< cantidad_horas.size();j++){
                    linea.add(new Entry(j,cantidad_horas.get(j)));
                }
                System.out.println(linea);
                grafico_lineal_2();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void cambiar_graf_lineal(View view){
        Intent i = new Intent(this, GRAF_LINEAL.class);
        startActivity(i);
    }

}