package com.example.energymapp.view.fragments.routines;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.energymapp.R;
import com.example.energymapp.adapters.CreateRoutineAdapter;
import com.example.energymapp.adapters.RoutineAdapter;
import com.example.energymapp.databinding.FragmentRoutinesBinding;
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

public class RoutinesFragment extends Fragment {

    private FragmentRoutinesBinding binding;
    private RoutineAdapter adapter;
    private DatabaseReference databaseReference;
    private String idUsuario;
    private List<Rutina> listaRutinas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRoutinesBinding.inflate(getLayoutInflater());
        databaseReference = FirebaseDatabase.getInstance().getReference();

        listaRutinas = new ArrayList<>();
        adapter = new RoutineAdapter(listaRutinas);
        binding.rvAllRoutines.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvAllRoutines.setAdapter(adapter);
        idUsuario = obtenerIdUsuario();

        editDeleteRoutine();
        showRoutinesList();
        showCreateRoutineScreen();

        return binding.getRoot();
    }

    //Permite editar o borrar la rutina
    private void editDeleteRoutine() {
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombreRutina = listaRutinas.get(binding.rvAllRoutines.getChildAdapterPosition(v)).getNombreRutina();

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = requireActivity().getLayoutInflater();

                Log.i("nombre", nombreRutina);

                View view = inflater.inflate(R.layout.edit_routine_dialog, null);

                builder.setView(view)
                        .setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                editRoutine(view, nombreRutina);

                            }
                        })

                        .setNeutralButton("Borrar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("¿Quieres borrar esta rutina?\n" +
                                        "La acción será irreversible")
                                        .setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        deleteRoutine(nombreRutina);
                                                    }
                                                })
                                        .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                builder.show();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });

                builder.show();
            }
        });
    }

    //Se borra la rutina
    private void deleteRoutine(String nombreRutina) {
        databaseReference.child("Rutina").child(idUsuario).orderByChild("nombreRutina").equalTo(nombreRutina).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    //Obtengo el ID de la rutina
                    String idRutina = dataSnapshot.getKey();

                    databaseReference.child("Rutina").child(idUsuario).child(idRutina).removeValue();
                    databaseReference.child("Ejercicios").child(idUsuario).child(idRutina).removeValue();
                    Snackbar.make(binding.containerAllRoutines, "Rutina eliminada", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Se edita la rutina
    private void editRoutine(View view, String nombreRutina) {
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText nombre = view.findViewById(R.id.etNombreRutinaEdit);
        String nuevoNombreRutina = nombre.getText().toString();

        if (!nuevoNombreRutina.isEmpty()){
            databaseReference.child("Rutina").child(idUsuario).orderByChild("nombreRutina").equalTo(nombreRutina).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                        Rutina rutina = new Rutina(idUsuario, nuevoNombreRutina);
                        databaseReference.child("Rutina").child(idUsuario).child(dataSnapshot.getKey()).setValue(rutina);
                        Snackbar.make(binding.containerAllRoutines, "Rutina actualizada", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            Snackbar.make(binding.containerAllRoutines, "Debes introducir un nuevo nombre para la rutina", Snackbar.LENGTH_LONG).show();

        }
    }

    //Muestra la pantalla que permite añadir ejercicios a la rutina
    private void showCreateRoutineScreen() {
        binding.btnCreateRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateRoutineFragment fragment = new CreateRoutineFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, fragment).commit();
            }
        });
    }

    //Muestra la lista de rutinas
    private void showRoutinesList() {
        databaseReference.child("Rutina").child(idUsuario).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                    listaRutinas.clear();
                    databaseReference.child("Rutina").child(idUsuario).child(dataSnapshot.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Rutina rutina = dataSnapshot.getValue(Rutina.class);
                            String nombreRutina = rutina.getNombreRutina();

                            Log.i("nRutina", nombreRutina);

                            listaRutinas.add(new Rutina(idUsuario, nombreRutina));
                            adapter.updateList(listaRutinas);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Se obtiene el nombre del usuario logueado
    private String obtenerIdUsuario() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        String idUsuario = sharedPreferences.getString("IdUsuario", "");


        Log.i("iduser", idUsuario);

        return idUsuario;
    }
}