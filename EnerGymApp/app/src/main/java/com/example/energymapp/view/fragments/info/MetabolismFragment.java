package com.example.energymapp.view.fragments.info;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.energymapp.databinding.FragmentMetabolismBinding;
import com.google.android.material.snackbar.Snackbar;

public class MetabolismFragment extends Fragment {

    private FragmentMetabolismBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMetabolismBinding.inflate(getLayoutInflater());

        comprobarCampos();

        return binding.getRoot();
    }

    private void comprobarCampos(){

        binding.btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sexo = binding.etSex.getText().toString().trim();
                String edad = binding.etAge.getText().toString().trim();
                String peso = binding.etWeight.getText().toString().trim();
                String altura = binding.etHeight.getText().toString().trim();

                if (sexo.isEmpty() || edad.isEmpty() || peso.isEmpty() || altura.isEmpty()){
                    Snackbar.make(binding.containerMetabolismo, "Hay campos vacíos", Snackbar.LENGTH_LONG).show();
                    Log.i("Campos", edad);
                }else{
                    calcularMetabolismo(sexo, edad, peso, altura);
                }

            }
        });
    }

    private void calcularMetabolismo(String sexo, String edad, String peso, String altura) {

        double metabolismoFormula = (10 * Integer.parseInt(peso)) + (6.25 * Integer.parseInt(altura)) - (5 * Integer.parseInt(edad));
        double metabolismo = 0;
        double metabolismoActividad = 0;

        if (!(sexo.equalsIgnoreCase("H") || sexo.equalsIgnoreCase("M"))){
            Snackbar.make(binding.containerMetabolismo, "Introduce un parámetro válido", Snackbar.LENGTH_LONG).show();
        }else if (sexo.equalsIgnoreCase("H")){
            metabolismo = metabolismoFormula + 5;
        }else {
            metabolismo = metabolismoFormula - 161;
        }

        if (!(binding.btnSedentaria.isChecked() || binding.btnIntensa.isChecked() || binding.btnModerada.isChecked() || binding.btnIntensa.isChecked())){
            Snackbar.make(binding.containerMetabolismo, "Selecciona un nivel de actividad", Snackbar.LENGTH_LONG).show();
        }else{

            if (binding.btnSedentaria.isChecked()){
                metabolismoActividad = metabolismo * 1.2;
            }else if (binding.btnLigera.isChecked()){
                metabolismoActividad = metabolismo * 1.375;
            } else if (binding.btnModerada.isChecked()) {
                metabolismoActividad = metabolismo * 1.55;
            }else {
                metabolismoActividad = metabolismo * 1.725;
            }
        }

        metabolismo = (int) metabolismo;
        metabolismoActividad = (int) metabolismoActividad;

        binding.txtResults.setText("Metabolimo basal: " + metabolismo + "\n" +
                "Necesitas " + metabolismoActividad + " calorías para mantener el peso");
    }
}