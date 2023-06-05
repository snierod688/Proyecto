package com.example.energymapp.view.fragments.routines;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.energymapp.adapters.ExercisesAdapter;
import com.example.energymapp.databinding.FragmentExercisesBinding;
import com.example.energymapp.model.Ejercicio;
import com.example.energymapp.model.NombreEjercicio;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ExercisesFragment extends Fragment {

    private FragmentExercisesBinding binding;
    private ExercisesAdapter adapter;
    private DatabaseReference databaseReference;
    private List<NombreEjercicio> nombreEjercicioList;
    private List<Ejercicio> listaEjerciciosRutina;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExercisesBinding.inflate(getLayoutInflater());
        databaseReference = FirebaseDatabase.getInstance().getReference();

        String idUsuario = obtenerIdUsuario();

        crearLista();
        nombreEjercicioList = new ArrayList<>();
        listaEjerciciosRutina = new ArrayList<>();
        obtenerEjercicios();

        adapter = new ExercisesAdapter(nombreEjercicioList);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ejercicio = nombreEjercicioList.get(binding.rvEjercicios.getChildAdapterPosition(v)).getNombre();

                Log.i("ej", ejercicio);
                guardarEjercicioSeleccionado(ejercicio);

                //CreateRoutineFragment fragment = new CreateRoutineFragment();

            }
        });

        binding.rvEjercicios.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvEjercicios.setAdapter(adapter);

        return binding.getRoot();
    }


    private void guardarEjercicioSeleccionado(String nombreEjercicio) {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("nombreEjercicio", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ejercicio", nombreEjercicio);
        editor.commit();
    }

    private void crearLista() {

        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Press de banca"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Press de banca inclinado"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Press de banca declinado"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Press de banca con mancuernas"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Aperturas en máquina"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Cruce de cables"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Elevaciones laterales de hombro"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Press militar"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Fondos"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Press francés"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Extensión de tríceps"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Remo sentado con cable"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Remo en T"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Jalón al pecho"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Pulldown"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Extensión de espalda"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Vuelos posteriores"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Curl predicador"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Curl martillo"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Curl de bíceps"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Sentadilla"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Extensión de cuádriceps"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Curl de piernas tumbado"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Curl de piernas sentado"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Prensa"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Hip trust"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Abducción de piernas"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Adducción de piernas"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Gemelos"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Sit up"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Plancha"));
        databaseReference.child("Nombre Ejercicio").push().setValue(new NombreEjercicio("Russian Twist"));
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

    private List<NombreEjercicio> obtenerEjercicios() {

        nombreEjercicioList = new ArrayList<>();

        nombreEjercicioList.add(new NombreEjercicio("Press de banca"));
        nombreEjercicioList.add(new NombreEjercicio("Press de banca inclinado"));
        nombreEjercicioList.add(new NombreEjercicio("Press de banca declinado"));
        nombreEjercicioList.add(new NombreEjercicio("Press de banca con mancuernas"));
        nombreEjercicioList.add(new NombreEjercicio("Aperturas en máquina"));
        nombreEjercicioList.add(new NombreEjercicio("Cruce de cables"));
        nombreEjercicioList.add(new NombreEjercicio("Elevaciones laterales de hombro"));
        nombreEjercicioList.add(new NombreEjercicio("Press militar"));
        nombreEjercicioList.add(new NombreEjercicio("Fondos"));
        nombreEjercicioList.add(new NombreEjercicio("Press francés"));
        nombreEjercicioList.add(new NombreEjercicio("Extensión de tríceps"));
        nombreEjercicioList.add(new NombreEjercicio("Remo sentado con cable"));
        nombreEjercicioList.add(new NombreEjercicio("Remo en T"));
        nombreEjercicioList.add(new NombreEjercicio("Jalón al pecho"));
        nombreEjercicioList.add(new NombreEjercicio("Pulldown"));
        nombreEjercicioList.add(new NombreEjercicio("Extensión de espalda"));
        nombreEjercicioList.add(new NombreEjercicio("Vuelos posteriores"));
        nombreEjercicioList.add(new NombreEjercicio("Curl predicador"));
        nombreEjercicioList.add(new NombreEjercicio("Curl martillo"));
        nombreEjercicioList.add(new NombreEjercicio("Curl de bíceps"));
        nombreEjercicioList.add(new NombreEjercicio("Sentadilla"));
        nombreEjercicioList.add(new NombreEjercicio("Extensión de cuádriceps"));
        nombreEjercicioList.add(new NombreEjercicio("Curl de piernas tumbado"));
        nombreEjercicioList.add(new NombreEjercicio("Curl de piernas sentado"));
        nombreEjercicioList.add(new NombreEjercicio("Prensa"));
        nombreEjercicioList.add(new NombreEjercicio("Hip trust"));
        nombreEjercicioList.add(new NombreEjercicio("Abducción de piernas"));
        nombreEjercicioList.add(new NombreEjercicio("Adducción de piernas"));
        nombreEjercicioList.add(new NombreEjercicio("Gemelos"));
        nombreEjercicioList.add(new NombreEjercicio("Sit up"));
        nombreEjercicioList.add(new NombreEjercicio("Plancha"));
        nombreEjercicioList.add(new NombreEjercicio("Russian Twist"));

        return nombreEjercicioList;
    }

    private String obtenerIdUsuario(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        String idUsuario = sharedPreferences.getString("IdUsuario", "");


        Log.i("iduser", idUsuario);

        return idUsuario;
    }
}