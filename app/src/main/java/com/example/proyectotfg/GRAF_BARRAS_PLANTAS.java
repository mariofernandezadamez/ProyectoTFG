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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class GRAF_BARRAS_PLANTAS extends AppCompatActivity {

    RequestQueue requestQueue;
    int plazas_Ocupadas_P1 = 0;
    int plazas_Vacias_P1 = 0;
    int plazas_Ocupadas_P2 = 0;
    int plazas_Vacias_P2 = 0;
    int plazas_Ocupadas_P3 = 0;
    int plazas_Vacias_P3 = 0;
    BarChart grafico_Barras;
    ArrayList<Integer> colores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graf_barras_plantas);

        grafico_Barras = findViewById(R.id.graficaBarras);
        grafico_barras_plantas();
        rellenar_grafico_barras_plantas();

    }

    public void atrasgraf2(View view){
        Intent i = new Intent(this, MENU_GRAFICOS.class);
        startActivity(i);
    }

    public void grafico_barras_plantas(){

        //CREAMOS LAS BARRAS DEL GRÁFICO
        List<BarEntry> entradas = new ArrayList<>();
        entradas.add(new BarEntry(0, plazas_Vacias_P1));
        entradas.add(new BarEntry(1, plazas_Ocupadas_P1));
        entradas.add(new BarEntry(2, plazas_Vacias_P2));
        entradas.add(new BarEntry(3, plazas_Ocupadas_P2));
        entradas.add(new BarEntry(4, plazas_Vacias_P3));
        entradas.add(new BarEntry(5, plazas_Ocupadas_P3));

        //AÑADIMOS LOS COLORES A LAS BARRAS
        int color_verde = getResources().getColor(R.color.green);
        int color_rojo = getResources().getColor(R.color.red);
        int color_blanco = getResources().getColor(R.color.white);

        colores.add(color_verde);
        colores.add(color_rojo);
        colores.add(color_verde);
        colores.add(color_rojo);
        colores.add(color_verde);
        colores.add(color_rojo);

        BarDataSet dataSet = new BarDataSet(entradas, "Plazas");
        dataSet.setColors(colores);
        dataSet.setDrawValues(true);

        //AÑADIR LOS DATOS Y COLORES AL GRÁFICO
        BarData data = new BarData(dataSet);
        grafico_Barras.setData(data);

        //TAMAÑO DEL GRÁFICO
        ViewGroup.LayoutParams layoutParams = grafico_Barras.getLayoutParams();
        layoutParams.width = 1075;
        layoutParams.height = 1000;
        grafico_Barras.setLayoutParams(layoutParams);

        //CONFIGURAR EL VALOR DE LAS BARRAS
        dataSet.setValueTextSize(20f);
        dataSet.setValueTextColor(color_blanco);

        //QUITAS LAS CUADICULAS Y LABELS DEL FONDO
        grafico_Barras.getAxisLeft().setDrawLabels(false);
        grafico_Barras.getAxisRight().setDrawLabels(false);
        grafico_Barras.getXAxis().setDrawLabels(false);
        grafico_Barras.getAxisLeft().setDrawAxisLine(false);
        grafico_Barras.getAxisRight().setDrawAxisLine(false);
        grafico_Barras.getXAxis().setDrawAxisLine(false);
        grafico_Barras.getXAxis().setDrawGridLines(false);
        grafico_Barras.getAxisLeft().setDrawGridLines(false);
        grafico_Barras.getAxisRight().setDrawGridLines(false);
        grafico_Barras.setDrawGridBackground(false);

        //SE QUITA LA LEYENDA
        grafico_Barras.getLegend().setEnabled(false);

        //SE QUITA LA DESCRIPCION
        grafico_Barras.getDescription().setEnabled(false);

        grafico_Barras.invalidate();
    }

    public void rellenar_grafico_barras_plantas(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.168.1.41/bbdd_tfg/grafico_barras_plantas.php?num_planta=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String[] partes_conjuntas = response.split("\\:");
                System.out.println("Partes conjuntas"+ partes_conjuntas);
                plazas_Vacias_P1 = Integer.parseInt(partes_conjuntas[1]);
                System.out.println("Las plazas vacias: "+plazas_Vacias_P1);
                plazas_Ocupadas_P1 = Integer.parseInt(partes_conjuntas[3]);
                System.out.println("Las plazas ocupadas: "+plazas_Ocupadas_P1);

                grafico_barras_plantas();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error "+ error.getMessage() ,Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, "http://192.168.1.41/bbdd_tfg/grafico_barras_plantas.php?num_planta=2", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String[] partes_conjuntas = response.split("\\:");
                plazas_Vacias_P2 = Integer.parseInt(partes_conjuntas[1]);
                System.out.println("Las plazas vacias: "+plazas_Vacias_P2);
                plazas_Ocupadas_P2 = Integer.parseInt(partes_conjuntas[3]);
                System.out.println("Las plazas ocupadas: "+plazas_Ocupadas_P2);

                grafico_barras_plantas();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error "+ error.getMessage() ,Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest2);

        StringRequest stringRequest3 = new StringRequest(Request.Method.GET, "http://192.168.1.41/bbdd_tfg/grafico_barras_plantas.php?num_planta=3", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String[] partes_conjuntas = response.split("\\:");
                plazas_Vacias_P3 = Integer.parseInt(partes_conjuntas[1]);
                System.out.println("Las plazas vacias: "+plazas_Vacias_P3);
                plazas_Ocupadas_P3 = Integer.parseInt(partes_conjuntas[3]);
                System.out.println("Las plazas ocupadas: "+plazas_Ocupadas_P3);

                grafico_barras_plantas();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error "+ error.getMessage() ,Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest3);
    }

}