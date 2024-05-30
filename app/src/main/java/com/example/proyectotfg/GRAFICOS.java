package com.example.proyectotfg;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class GRAFICOS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos);

        int plazasVacias = 50;
        int plazasOcupadas = 50;

        PieChart pieChart = findViewById(R.id.pieChart);

        ArrayList<PieEntry> entradas = new ArrayList<>();
        entradas.add(new PieEntry(plazasOcupadas,"Plazas Ocupada"));
        entradas.add(new PieEntry(plazasVacias, "Plazas Vacias"));

        PieDataSet dataSet = new PieDataSet(entradas,"");
        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);

        int color_verde = getResources().getColor(R.color.green);
        int color_rojo = getResources().getColor(R.color.red);
        int color_negro = getResources().getColor(R.color.black);
        //int color_transparente = getResources().getColor(R.color.transparente);

        ArrayList<Integer> colores = new ArrayList<>();
        colores.add(color_rojo);
        colores.add(color_verde);

        //dataSet.setColor(colores);
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(color_negro);

        pieChart.setEntryLabelTextSize(12f);
        pieChart.setEntryLabelColor(color_negro);

        pieChart.getDescription().setEnabled(true);
        pieChart.invalidate();

    }
}