package com.example.energymapp.view.fragments.routines;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.Toast;

import com.example.energymapp.R;
import com.example.energymapp.adapters.CreateRoutineAdapter;
import com.example.energymapp.databinding.FragmentCreateRoutineBinding;
import com.example.energymapp.model.Ejercicio;
import com.example.energymapp.model.Rutina;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CreateRoutineFragment extends Fragment {

    private FragmentCreateRoutineBinding binding;
    private CreateRoutineAdapter adapter;
    private DatabaseReference databaseReference;
    private String idUsuario;
    private List<Ejercicio> ejercicioList;
    private String nombreEjercicio;
    private String idRutina;

    public CreateRoutineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateRoutineBinding.inflate(getLayoutInflater());
        databaseReference = FirebaseDatabase.getInstance().getReference();

        ejercicioList = new ArrayList<>();
        adapter = new CreateRoutineAdapter(ejercicioList);
        idUsuario = obtenerIdUsuario();
        binding.rvRutina.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvRutina.setAdapter(adapter);

        //Añade ejercicios
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = requireActivity().getLayoutInflater();

                View view = inflater.inflate(R.layout.exercise_name_dialog, null);

                builder.setView(view)
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText ejercicio = view.findViewById(R.id.etNombreEjercicio);
                                nombreEjercicio = ejercicio.getText().toString().toUpperCase();

                                //Se guarda en una lista para mostrarlos en el RecyclerView de ejercicios
                                ejercicioList.add(new Ejercicio(nombreEjercicio));
                                adapter.updateList(ejercicioList);

                                //Rutina rutina = new Rutina(nombreRutina);

                                //databaseReference.child("Rutina").child(idUsuario).push().setValue(rutina);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });

                builder.show();

                /*ExercisesFragment fragment = new ExercisesFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, fragment).commit();


                ejercicioList = (List<Serie>) getArguments().getSerializable("listaEjercicios");
                adapter = new CreateRoutineAdapter(ejercicioList);
                ejercicioList.add(new Serie(obtenerNombreEjercicio()));
                adapter.updateList(ejercicioList);*/

            }


        });

        //Guarda la rutina
        binding.btnSaveRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*String idRutina = databaseReference.push().getKey();
                Rutina rutina = new Rutina(idUsuario, idRutina, nombreRutina);
                databaseReference.child("Rutinas").child(idUsuario).child(idRutina).setValue(rutina);*/

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = requireActivity().getLayoutInflater();

                View view = inflater.inflate(R.layout.routine_name_dialog, null);

                if (!nombreEjercicio.isEmpty()){
                    builder.setView(view)

                            .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {

                                    EditText nombre = view.findViewById(R.id.etNombreRutina);
                                    String nombreRutina = nombre.getText().toString();

                                    Rutina rutina = new Rutina(idUsuario, nombreRutina);

                                    //Guarda los datos de la rutina
                                    databaseReference.child("Rutina").child(idUsuario).push().setValue(rutina);

                                    databaseReference.child("Rutina").child(idUsuario).orderByChild("nombreRutina").equalTo(nombreRutina).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                                                idRutina = dataSnapshot.getKey();
                                                //guardarIdRutina(idRutina);



                                                Log.i("rutina", idRutina);
                                            }

                                            /*Ejercicio ejerciciosBD = new Ejercicio(idUsuario, idRutina, nombreEjercicio);
                                            ejercicioListBD.add(ejerciciosBD);*/

                                            //Guarda los datos de cada ejercicio de la rutina
                                            databaseReference.child("Ejercicios").child(idUsuario).child(idRutina).setValue(ejercicioList);

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });


                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });

                    builder.show();
                }else {
                    Snackbar.make(binding.containerCreateRoutine, "Debes añadir un ejercicio al menos", Snackbar.LENGTH_LONG).show();
                }

                RoutinesFragment fragment = new RoutinesFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, fragment).commit();

            }
        });

        return binding.getRoot();
    }

    private String obtenerNombreEjercicio() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("nombreEjercicio", Context.MODE_PRIVATE);
        String ejercicio = sharedPreferences.getString("ejercicio", "");


        //Log.i("name", nombreEjercicio);

        return ejercicio;
    }

    private void borrarPrefs() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("nombreEjercicio", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
    }

    /*private List<Serie> rellenarLista() {


        String nombreEjercicio = obtenerNombreEjercicio();
        ejercicioList.add(new Serie(nombreEjercicio));


        *//*databaseReference.child("Ejercicio").orderByChild("nombre").equalTo(nombreEjercicio).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Serie serie = new Serie(nombreEjercicio);
                    databaseReference.child("Rutina").child(idUsuario).push().setValue(serie);

                    Log.i("RESULTADOS", nombreEjercicio);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*//*

        //adapter.updateList(ejercicioList);

        Log.i("list", ejercicioList.toString());

        return ejercicioList;
    }*/

    private String obtenerIdUsuario() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        String idUsuario = sharedPreferences.getString("IdUsuario", "");


        Log.i("iduser", idUsuario);

        return idUsuario;
    }

    private void guardarIdRutina(String id) {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("rutina", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("IdRutina", id);
        editor.commit();

    }
    private String obtenerIdRutina() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("rutina", Context.MODE_PRIVATE);
        String idRutina = sharedPreferences.getString("IdRutina", "");


        Log.i("iduser", idRutina);

        return idRutina;
    }

}