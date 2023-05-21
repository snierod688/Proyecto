package com.example.energymapp.view.fragments.stats;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.energymapp.databinding.FragmentCreateRoutineBinding;
import com.example.energymapp.databinding.FragmentStatsBinding;
import com.example.energymapp.model.MedidasUsuario;
import com.example.energymapp.view.fragments.routines.ExercisesFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StatsFragment extends Fragment {

    private FragmentStatsBinding binding;
    private DatabaseReference databaseReference;

    private String idUsuario;
    private String altura;
    private String peso;
    private String biceps;
    private String cintura;
    private String pecho;
    private String muslo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStatsBinding.inflate(getLayoutInflater());

        binding.btnCalcularMetabolismo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MetabolismFragment fragment = new MetabolismFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, fragment).commit();
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference();

        obtenerIdUsuario();
        Log.i("RES", idUsuario);
        guardarMedidas();
        obtenerMedidas(idUsuario);

        return binding.getRoot();
    }

    //Obtenemos el ID del usuario que se ha registrado
    private void obtenerIdUsuario() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        idUsuario = sharedPreferences.getString("IdUsuario", String.valueOf(Context.MODE_PRIVATE));
    }

    private void guardarMedidas(){

        binding.btnGuardarMedidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String altura = binding.etAltura.getText().toString().trim();
                String peso = binding.etPeso.getText().toString().trim();
                String biceps = binding.etBiceps.getText().toString().trim();
                String cintura = binding.etCintura.getText().toString().trim();
                String pecho = binding.etPecho.getText().toString().trim();
                String muslo = binding.etMuslo.getText().toString().trim();

                if (altura.isEmpty() || peso.isEmpty() || biceps.isEmpty() || cintura.isEmpty() ||
                        pecho.isEmpty() || muslo.isEmpty()){
                    Snackbar.make(binding.containerProgreso, "Hay campos vacíos", Snackbar.LENGTH_LONG).show();
                }else{
                    guardarEnBaseDatos(altura, peso, biceps, cintura, pecho, muslo);
                }

            }
        });

    }

    private void guardarEnBaseDatos(String altura, String peso, String biceps, String cintura, String pecho, String muslo) {

        MedidasUsuario medidasUsuario = new MedidasUsuario(altura, peso, biceps, cintura, pecho, muslo);
        databaseReference.child("Medidas").child(idUsuario).setValue(medidasUsuario);
    }

    private void obtenerMedidas(String idUsuario){
        databaseReference.child("Medidas").child(idUsuario).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    altura = snapshot.child("altura").getValue().toString();
                    binding.etAltura.setText(altura);

                    peso = snapshot.child("peso").getValue().toString();
                    binding.etPeso.setText(peso);

                    biceps = snapshot.child("biceps").getValue().toString();
                    binding.etBiceps.setText(biceps);

                    cintura = snapshot.child("cintura").getValue().toString();
                    binding.etCintura.setText(cintura);

                    pecho = snapshot.child("pecho").getValue().toString();
                    binding.etPecho.setText(pecho);

                    muslo = snapshot.child("muslo").getValue().toString();
                    binding.etMuslo.setText(muslo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}