package com.example.energymapp.view.fragments.stats;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.energymapp.R;
import com.example.energymapp.databinding.FragmentMetabolismBinding;
import com.example.energymapp.databinding.FragmentStatsBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MetabolismFragment extends Fragment {

    private FragmentMetabolismBinding binding;

    private String idUsuario;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMetabolismBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    private void calcularMetabolismo(){

        binding.btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sexo = binding.etSex.getText().toString().trim();
                String edad = binding.etAge.getText().toString().trim();
                String peso = binding.etWeight.getText().toString().trim();
                String altura = binding.etHeight.getText().toString().trim();

                if (sexo.isEmpty() || edad.isEmpty() || peso.isEmpty() || altura.isEmpty()){
                    Toast.makeText(getContext(), "Hay campos vacíos", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}