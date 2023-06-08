package com.example.energymapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.energymapp.R;
import com.example.energymapp.adapters.RoutineAdapter;
import com.example.energymapp.databinding.ActivityAdminBinding;
import com.example.energymapp.databinding.ActivityLoginBinding;
import com.example.energymapp.login.LoginActivity;
import com.example.energymapp.model.Rutina;
import com.example.energymapp.view.fragments.chronometer.ChronometerFragment;
import com.example.energymapp.view.fragments.info.StatsFragment;
import com.example.energymapp.view.fragments.routines.RoutinesFragment;
import com.example.energymapp.view.fragments.train.RoutineTrainFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private ActivityAdminBinding binding;
    private RoutineAdapter adapter;
    private DatabaseReference databaseReference;
    private List<Rutina> listaRutinas;
    private String idUsuario;
    private String idRutina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseReference = FirebaseDatabase.getInstance().getReference();

        Snackbar.make(binding.containerAdmin, "BIENVENIDO", Snackbar.LENGTH_LONG).show();


        listaRutinas = new ArrayList<>();
        adapter = new RoutineAdapter(listaRutinas);
        binding.rvDeleteRoutine.setLayoutManager(new LinearLayoutManager(this));
        binding.rvDeleteRoutine.setAdapter(adapter);


        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombreRutina = listaRutinas.get(binding.rvDeleteRoutine.getChildAdapterPosition(v)).getNombreRutina();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(AdminActivity.this);
                builder1.setMessage("¿Quieres borrar esta rutina?\n" +
                                "La acción será irreversible")

                        .setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                AlertDialog.Builder builder2 = new AlertDialog.Builder(AdminActivity.this);
                                builder2.setMessage("¿Quieres borrar esta rutina?\n" +
                                                "La acción será irreversible")

                                        .setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                databaseReference.child("Rutina").addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                                                            idUsuario = dataSnapshot.getKey();
                                                            Log.i("IdUsuario",idUsuario);

                                                            databaseReference.child("Rutina").child(idUsuario).addValueEventListener(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                                    databaseReference.child("Rutina").child(idUsuario).orderByChild("nombreRutina").equalTo(nombreRutina).addValueEventListener(new ValueEventListener() {
                                                                        @Override
                                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                            for (DataSnapshot dataSnapshot1: snapshot.getChildren()){
                                                                                String idRutina = dataSnapshot1.getKey();

                                                                                databaseReference.child("Rutina").child(idUsuario).child(idRutina).removeValue();
                                                                                databaseReference.child("Ejercicios").child(idUsuario).child(idRutina).removeValue();
                                                                                Snackbar.make(binding.containerAdmin, "Rutina eliminada", Snackbar.LENGTH_LONG).show();
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
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });
                                            }

                                        })
                                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                builder2.show();
                            }
                        })
                        .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder1.show();
            }
        });

        databaseReference.child("Rutina").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                    listaRutinas.clear();
                    idUsuario = dataSnapshot.getKey();
                    Log.i("IdUsuario",idUsuario);

                    databaseReference.child("Rutina").child(idUsuario).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for (DataSnapshot dataSnapshot1: snapshot.getChildren()){
                                listaRutinas.clear();

                                idRutina = dataSnapshot1.getKey();
                                Log.i("IdRutina", idRutina);

                                databaseReference.child("Rutina").child(idUsuario).child(idRutina).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        Rutina rutina = dataSnapshot1.getValue(Rutina.class);
                                        String nombreRutina = rutina.getNombreRutina();

                                        Log.i("nRutina", nombreRutina);

                                        listaRutinas.add(new Rutina(nombreRutina));
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.bottonNavigationBarAdmin.setOnItemSelectedListener(item ->  {

            if (item.getItemId() == R.id.closeAdmin){

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("¿Quieres cerrar sesión")
                        .setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(AdminActivity.this, LoginActivity.class);
                                startActivity(i);
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.show();
            }

            return true;
        });
    }
}