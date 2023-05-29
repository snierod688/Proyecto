package com.example.energymapp.view.fragments.routines;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExercisesFragment extends Fragment {

    private FragmentExercisesBinding binding;
    private ExercisesAdapter adapter;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExercisesBinding.inflate(getLayoutInflater());
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //crearLista();

        adapter = new ExercisesAdapter(obtenerEjercicios());

        binding.rvEjercicios.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvEjercicios.setAdapter(adapter);

        return binding.getRoot();
    }

    private void crearLista() {

        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Press de banca"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Press de banca inclinado"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Press de banca declinado"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Press de banca con mancuernas"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Aperturas en máquina"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Cruce de cables"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Elevaciones laterales de hombro"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Press militar"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Fondos"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Press francés"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Extensión de tríceps"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Remo sentado con cable"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Remo en T"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Jalón al pecho"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Pulldown"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Extensión de espalda"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Vuelos posteriores"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Curl predicador"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Curl martillo"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Curl de bíceps"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Sentadilla"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Extensión de cuádriceps"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Curl de piernas tumbado"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Curl de piernas sentado"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Prensa"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Hip trust"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Abducción de piernas"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Adducción de piernas"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Gemelos"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Sit up"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Plancha"));
        databaseReference.child("Ejercicio").push().setValue(new Ejercicio("Russian Twist"));
    }

    /*private List<Ejercicio> obtenerLista(){
         List<Ejercicio> ejercicioList = new ArrayList();

         databaseReference.child("Ejercicio").orderByChild("nombre").addValueEventListener(new ValueEventListener(){
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                     Ejercicio e = dataSnapshot.getValue(Ejercicio.class);
                     String nombre = e.getNombre();
                     Ejercicio ejercicio = new Ejercicio(nombre);
                     ejercicioList.add(ejercicio);

                     Log.i("RESULTADOS", nombre);
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });

         return ejercicioList;
    }*/

    private List<Ejercicio> obtenerEjercicios() {

        List<Ejercicio> ejercicioList = new ArrayList<>();

        ejercicioList.add(new Ejercicio("Press de banca"));
        ejercicioList.add(new Ejercicio("Press de banca inclinado"));
        ejercicioList.add(new Ejercicio("Press de banca declinado"));
        ejercicioList.add(new Ejercicio("Press de banca con mancuernas"));
        ejercicioList.add(new Ejercicio("Aperturas en máquina"));
        ejercicioList.add(new Ejercicio("Cruce de cables"));
        ejercicioList.add(new Ejercicio("Elevaciones laterales de hombro"));
        ejercicioList.add(new Ejercicio("Press militar"));
        ejercicioList.add(new Ejercicio("Fondos"));
        ejercicioList.add(new Ejercicio("Press francés"));
        ejercicioList.add(new Ejercicio("Extensión de tríceps"));
        ejercicioList.add(new Ejercicio("Remo sentado con cable"));
        ejercicioList.add(new Ejercicio("Remo en T"));
        ejercicioList.add(new Ejercicio("Jalón al pecho"));
        ejercicioList.add(new Ejercicio("Pulldown"));
        ejercicioList.add(new Ejercicio("Extensión de espalda"));
        ejercicioList.add(new Ejercicio("Vuelos posteriores"));
        ejercicioList.add(new Ejercicio("Curl predicador"));
        ejercicioList.add(new Ejercicio("Curl martillo"));
        ejercicioList.add(new Ejercicio("Curl de bíceps"));
        ejercicioList.add(new Ejercicio("Sentadilla"));
        ejercicioList.add(new Ejercicio("Extensión de cuádriceps"));
        ejercicioList.add(new Ejercicio("Curl de piernas tumbado"));
        ejercicioList.add(new Ejercicio("Curl de piernas sentado"));
        ejercicioList.add(new Ejercicio("Prensa"));
        ejercicioList.add(new Ejercicio("Hip trust"));
        ejercicioList.add(new Ejercicio("Abducción de piernas"));
        ejercicioList.add(new Ejercicio("Adducción de piernas"));
        ejercicioList.add(new Ejercicio("Gemelos"));
        ejercicioList.add(new Ejercicio("Sit up"));
        ejercicioList.add(new Ejercicio("Plancha"));
        ejercicioList.add(new Ejercicio("Russian Twist"));

        return ejercicioList;
    }
}