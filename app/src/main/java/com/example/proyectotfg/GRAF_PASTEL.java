package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class GRAF_PASTEL extends AppCompatActivity {

    PieChart pieChart;
    int plazasVacias = 0;
    int plazasOcupadas = 0;
    RequestQueue requestQueue;
    ArrayList<Integer> colores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graf_pastel);

        pieChart = findViewById(R.id.pieChart);
        grafico_pastel();
        rellenar_grafico();
    }

    public void grafico_pastel(){

        //CREAMOS LOS CAMPOS DEL GRÁFICO
        ArrayList<PieEntry> entradas = new ArrayList<>();
        entradas.add(new PieEntry(plazasOcupadas,"Plazas Ocupadas"));
        entradas.add(new PieEntry(plazasVacias, "Plazas Vacias"));

        //COLORES DE LOS CAMPOS
        PieDataSet dataSet = new PieDataSet(entradas,"");
        int color_verde = getResources().getColor(R.color.green);
        int color_rojo = getResources().getColor(R.color.red);
        int color_negro = getResources().getColor(R.color.black);

        colores.add(color_rojo);
        colores.add(color_verde);
        dataSet.setColors(colores);

        //AÑADIR LOS DATOS Y COLORES AL GRÁFICO
        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);

        //CENTRO DEL GRÁFICO TRANSPARENTE
        int color_transparente = getResources().getColor(R.color.transparente);
        pieChart.setHoleColor(color_transparente);

        //TAMAÑO DEL CIRCULO
        ViewGroup.LayoutParams layoutParams = pieChart.getLayoutParams();
        layoutParams.width = 1000;
        layoutParams.height = 1000;
        pieChart.setLayoutParams(layoutParams);

        //TAMAÑO DEL CIRCULO TRANSPARENTE
        pieChart.setHoleRadius(40f);

        //CONFIGURAR EL VALOR Y TEXTO DE LOS CAMPOS
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(color_negro);

        pieChart.setEntryLabelTextSize(12f);
        pieChart.setEntryLabelColor(color_negro);

        //CONFIGURACIÓN DE LA LEYENDA
        int color_blanco = getResources().getColor(R.color.white);
        Legend legend = pieChart.getLegend();
        legend.setTextSize(17f);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setXEntrySpace(20f);
        legend.setDrawInside(false);
        legend.setTextColor(color_blanco);

        pieChart.getDescription().setEnabled(false);
        pieChart.invalidate();
    }

    public void rellenar_grafico() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.168.1.41/bbdd_tfg/grafico_pastel.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String[] partes_conjuntas = response.split("\\:");

                plazasVacias = Integer.parseInt(partes_conjuntas[1]);
                System.out.println("Las plazas vacias: "+plazasVacias);
                plazasOcupadas = Integer.parseInt(partes_conjuntas[3]);
                System.out.println("Las plazas ocupadas: "+plazasOcupadas);

                //ESTO LO HAGO PARA ATUALIZAR CON LOS NUEVOS DATOS
                grafico_pastel();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error "+ error.getMessage() ,Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}