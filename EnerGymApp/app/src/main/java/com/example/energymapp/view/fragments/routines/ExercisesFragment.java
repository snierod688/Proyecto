package com.example.energymapp.view.fragments.routines;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.energymapp.adapters.ExercisesAdapter;
import com.example.energymapp.databinding.FragmentExercisesBinding;
import com.example.energymapp.model.Ejercicio;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.UUID;

public class ExercisesFragment extends Fragment {

    private FragmentExercisesBinding binding;
    private ExercisesAdapter adapter;
    private static final int numIds = 7;
    private DatabaseReference databaseReference;

    public ExercisesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExercisesBinding.inflate(getLayoutInflater());
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //crearLista();

        /*adapter = new ExercisesAdapter(cargarLista());
        binding.rvEjercicios.setAdapter(adapter);*/

        return binding.getRoot();
    }

    private void crearLista() {

        String id1 = UUID.randomUUID().toString();
        Ejercicio ejercicio1 = new Ejercicio("Press de banca");
        databaseReference.child("Ejercicio").child(id1).setValue(ejercicio1);

        String id2 = UUID.randomUUID().toString();
        Ejercicio ejercicio2 = new Ejercicio("Press de banca inclinado");
        databaseReference.child("Ejercicio").child(id2).setValue(ejercicio2);

        String id3 = UUID.randomUUID().toString();
        Ejercicio ejercicio3 = new Ejercicio("Press de banca declinado");
        databaseReference.child("Ejercicio").child(id3).setValue(ejercicio3);

        String id4 = UUID.randomUUID().toString();
        Ejercicio ejercicio4 = new Ejercicio("Aperturas en máquina");
        databaseReference.child("Ejercicio").child(id4).setValue(ejercicio4);

    }
}