package com.example.energymapp.view.fragments.info;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.energymapp.R;
import com.example.energymapp.adapters.ExercisesAdapter;
import com.example.energymapp.databinding.FragmentExercisesBinding;
import com.example.energymapp.model.Ejercicio;
import com.example.energymapp.model.NombreEjercicio;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExercisesFragment extends Fragment {

    private FragmentExercisesBinding binding;
    private ExercisesAdapter adapter;
    private DatabaseReference databaseReference;
    private List<NombreEjercicio> nombreEjercicioList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExercisesBinding.inflate(getLayoutInflater());
        databaseReference = FirebaseDatabase.getInstance().getReference();

        nombreEjercicioList = new ArrayList<>();

        adapter = new ExercisesAdapter(nombreEjercicioList);

        binding.rvEjercicios.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvEjercicios.setAdapter(adapter);

        //Hace una consulta en la base de datos de los nombres de los ejercicios que hay guardados
        databaseReference.child("Nombre Ejercicio").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    NombreEjercicio ejercicio = dataSnapshot.getValue(NombreEjercicio.class);
                    String nombreEjercicio = ejercicio.getNombre();

                    //Añade los ejercicios a la lista y la muestra en pantalla
                    nombreEjercicioList.add(new NombreEjercicio(nombreEjercicio));
                    adapter.updateList(nombreEjercicioList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return binding.getRoot();
    }
}