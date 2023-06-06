package com.example.energymapp.view.fragments.train;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.energymapp.R;
import com.example.energymapp.adapters.CreateRoutineAdapter;
import com.example.energymapp.adapters.RoutineAdapter;
import com.example.energymapp.adapters.StartTrainRoutineAdapter;
import com.example.energymapp.databinding.FragmentRoutineTrainBinding;
import com.example.energymapp.databinding.FragmentTrainBinding;
import com.example.energymapp.model.Ejercicio;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TrainFragment extends Fragment {

    private FragmentTrainBinding binding;
    private StartTrainRoutineAdapter adapter;
    private DatabaseReference databaseReference;
    private String idUsuario;
    private String idRutina;

    private List<Ejercicio> ejercicioList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTrainBinding.inflate(getLayoutInflater());
        databaseReference = FirebaseDatabase.getInstance().getReference();

        ejercicioList = new ArrayList<>();
        adapter = new StartTrainRoutineAdapter(ejercicioList);
        idUsuario = obtenerIdUsuario();
        binding.rvRutinaTrain.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvRutinaTrain.setAdapter(adapter);

        binding.txtNombreRutina.setText(obtenerNombreRutina());

        //Consulta en la Base de datos para obtener el ID de la rutina
        databaseReference.child("Rutina").child(idUsuario).orderByChild("nombreRutina").equalTo(obtenerNombreRutina()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    idRutina = dataSnapshot.getKey();

                    Log.i("idRutina", idRutina);
                }

                databaseReference.child("Ejercicios").child(idUsuario).child(idRutina).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                            Ejercicio ejercicio = dataSnapshot.getValue(Ejercicio.class);
                            String nombreEjercicio = ejercicio.getNombreEjercicio();

                            Log.i("ejercicio", nombreEjercicio);

                            ejercicioList.add(new Ejercicio(nombreEjercicio));
                            adapter.updateList(ejercicioList);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Con el ID de la rutina obtenido previamente, obtenemos los ejercicios que contiene
        /*databaseReference.child("Ejercicios").child(idUsuario).child(idRutina).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Ejercicio ejercicio = dataSnapshot.getValue(Ejercicio.class);
                    String nombreEjercicio = ejercicio.getNombreEjercicio();

                    ejercicioList.add(new Ejercicio(nombreEjercicio));
                    adapter.updateList(ejercicioList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        return binding.getRoot();
    }



    private String obtenerIdUsuario() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        String idUsuario = sharedPreferences.getString("IdUsuario", "");


        Log.i("iduser", idUsuario);

        return idUsuario;
    }

    private String obtenerNombreRutina() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("rutina", Context.MODE_PRIVATE);
        String nombreRutina = sharedPreferences.getString("nombreRutina", "");


        Log.i("iduser", nombreRutina);

        return nombreRutina;
    }
}