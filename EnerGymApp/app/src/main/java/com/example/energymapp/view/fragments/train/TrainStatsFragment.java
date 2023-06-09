package com.example.energymapp.view.fragments.train;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.energymapp.R;
import com.example.energymapp.databinding.FragmentTrainBinding;
import com.example.energymapp.databinding.FragmentTrainStatsBinding;
import com.example.energymapp.model.Ejercicio;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TrainStatsFragment extends Fragment {

    private FragmentTrainStatsBinding binding;
    private DatabaseReference databaseReference;
    private String idUsuario;
    private String idRutina;
    private List<BarEntry> ejercicioList;
    private List<String> nombreEjercicios;
    private float i;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTrainStatsBinding.inflate(getLayoutInflater());
        databaseReference = FirebaseDatabase.getInstance().getReference();

        ejercicioList = new ArrayList<>();
        nombreEjercicios = new ArrayList<>();
        idUsuario = obtenerIdUsuario();
        idRutina = obtenerIdRutina();

        getTrainData();

        backToRoutineTrain();


        return binding.getRoot();
    }

    //Regresa al usuario a la pantalla de las rutinas
    private void backToRoutineTrain() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Vuelve al fragment donde se encuentran las rutinas para iniciar los entrenamientos
                RoutineTrainFragment fragment = new RoutineTrainFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, fragment).commit();
            }
        });
    }

    //Obtiene los registros del entrenamiento realizado
    private void getTrainData() {
        databaseReference.child("Ejercicios").child(idUsuario).child(idRutina).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                    i++;

                    Ejercicio ejercicio = dataSnapshot.getValue(Ejercicio.class);
                    String nombreEjercicio = ejercicio.getNombreEjercicio();
                    int repsTotal = ejercicio.getNumRepsTotal();
                    int pesoTotal = ejercicio.getPesoTotal();

                    Log.i("datos", "Nombre: " + nombreEjercicio + " - Reps: " + repsTotal + " - Peso: " + pesoTotal);

                    int totalLevantado = repsTotal*pesoTotal;

                    nombreEjercicios.add(nombreEjercicio);
                    BarEntry barEntry = new BarEntry(i, totalLevantado);

                    ejercicioList.add(barEntry);
                    Log.i("lista", ejercicioList.toString());
                }

                printChart();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Se muestra el gráfico de barras
    private void printChart() {
        BarDataSet barDataSet = new BarDataSet(ejercicioList, "Rutina");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        binding.barChart.setData(barData);
        binding.barChart.animateY(2000);
        binding.barChart.getDescription().setEnabled(false);

        XAxis axis = binding.barChart.getXAxis();
        axis.setValueFormatter(new IndexAxisValueFormatter(nombreEjercicios));
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setCenterAxisLabels(true);
    }

    //Obtiene el ID de la rutina realizada
    private String obtenerIdRutina(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("rutinaID", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("idRutina", "");


        Log.i("idRutina", id);

        return id;
    }

    //Obtiene el ID del usuario logueado
    private String obtenerIdUsuario() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        String idUsuario = sharedPreferences.getString("IdUsuario", "");


        Log.i("iduser", idUsuario);

        return idUsuario;
    }
}