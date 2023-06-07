package com.example.energymapp.view.fragments.train;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.energymapp.R;
import com.example.energymapp.adapters.StartTrainRoutineAdapter;
import com.example.energymapp.databinding.FragmentTrainBinding;
import com.example.energymapp.model.Ejercicio;
import com.google.android.material.snackbar.Snackbar;
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

    private List<Ejercicio> muestraEjerciciosList;
    private List<Ejercicio> ejercicioListBD;

    private String  reps1, reps2, reps3, reps4;
    private String peso1, peso2, peso3, peso4;

    private int repsTotal, pesoTotal;
    private String nombreEjercicio;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTrainBinding.inflate(getLayoutInflater());
        databaseReference = FirebaseDatabase.getInstance().getReference();

        muestraEjerciciosList = new ArrayList<>();
        ejercicioListBD = new ArrayList<>();
        adapter = new StartTrainRoutineAdapter(muestraEjerciciosList);
        idUsuario = obtenerIdUsuario();
        binding.rvRutinaTrain.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvRutinaTrain.setAdapter(adapter);

        repsTotal = 0;
        pesoTotal = 0;

        binding.txtNombreRutina.setText(obtenerNombreRutina());

        //Consulta en la Base de datos para obtener el ID de la rutina
        databaseReference.child("Rutina").child(idUsuario).orderByChild("nombreRutina").equalTo(obtenerNombreRutina()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    idRutina = dataSnapshot.getKey();

                    Log.i("idRutina", idRutina);
                }

                //Muestra el nombre de los ejercicios
                databaseReference.child("Ejercicios").child(idUsuario).child(idRutina).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                            Ejercicio ejercicio = dataSnapshot.getValue(Ejercicio.class);
                            nombreEjercicio = ejercicio.getNombreEjercicio();

                            Log.i("ejercicio", nombreEjercicio);

                            muestraEjerciciosList.add(new Ejercicio(nombreEjercicio, repsTotal, pesoTotal));
                            adapter.updateList(muestraEjerciciosList);
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

        binding.btnGuardarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Snackbar.make(binding.containerTrain, "HOLA", Snackbar.LENGTH_LONG).show();

                //ejercicioList.clear();

                for (int i = 0; i< muestraEjerciciosList.size(); i++){


                    int repsInt1 = 0;
                    int repsInt2 = 0;
                    int repsInt3 = 0;
                    int repsInt4 = 0;

                    int pesoInt1 = 0;
                    int pesoInt2 = 0;
                    int pesoInt3 = 0;
                    int pesoInt4 = 0;

                    reps1 = StartTrainRoutineAdapter.ejercicio.get(i).getReps1();
                    reps2 = StartTrainRoutineAdapter.ejercicio.get(i).getReps2();
                    reps3 = StartTrainRoutineAdapter.ejercicio.get(i).getReps3();
                    reps4 = StartTrainRoutineAdapter.ejercicio.get(i).getReps3();

                    peso1 = StartTrainRoutineAdapter.ejercicio.get(i).getPeso1();
                    peso2 = StartTrainRoutineAdapter.ejercicio.get(i).getPeso2();
                    peso3 = StartTrainRoutineAdapter.ejercicio.get(i).getPeso3();
                    peso4 = StartTrainRoutineAdapter.ejercicio.get(i).getPeso4();


                    Log.i("reps", reps1);

                    if (!reps1.isEmpty()){
                        repsInt1 = Integer.parseInt(reps1);

                    }

                    if (!reps2.isEmpty()){
                        repsInt2 = Integer.parseInt(reps2);

                    }

                    if (!reps3.isEmpty()){
                        repsInt3 = Integer.parseInt(reps3);

                    }

                    if (!reps4.isEmpty()){
                        repsInt4 = Integer.parseInt(reps4);

                    }

                    if (!peso1.isEmpty()){
                        pesoInt1 = Integer.parseInt(peso1);
                    }

                    if (!peso2.isEmpty()){
                        pesoInt2 = Integer.parseInt(peso2);
                    }

                    if (!peso3.isEmpty()){
                        pesoInt3 = Integer.parseInt(peso3);
                    }

                    if (!peso4.isEmpty()){
                        pesoInt4 = Integer.parseInt(peso4);
                    }

                    repsTotal = repsInt1 + repsInt2 + repsInt3 + repsInt4;
                    pesoTotal = pesoInt1 + pesoInt2 + pesoInt3 + pesoInt4;

                    ejercicioListBD.add(new Ejercicio(muestraEjerciciosList.get(i).getNombreEjercicio(), repsTotal, pesoTotal));

                }

                databaseReference.child("Ejercicios").child(idUsuario).child(idRutina).setValue(ejercicioListBD);
                adapter.updateList(ejercicioListBD);

                //Vuelve al fragment donde se encuentran las rutinas para iniciar los entrenamientos
                RoutineTrainFragment fragment = new RoutineTrainFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, fragment).commit();

                Snackbar.make(binding.containerTrain, "Rutina actualizada correctamente", Snackbar.LENGTH_LONG).show();

            }
        });

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