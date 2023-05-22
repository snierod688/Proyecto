package com.example.energymapp.view.fragments.chronometer;

import android.animation.ObjectAnimator;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.energymapp.R;
import com.example.energymapp.databinding.FragmentChronometerBinding;
import com.example.energymapp.databinding.FragmentStatsBinding;
import com.google.android.material.snackbar.Snackbar;

public class ChronometerFragment extends Fragment {

    private FragmentChronometerBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChronometerBinding.inflate(getLayoutInflater());

        comprobarCampos();

        return binding.getRoot();

    }

    private void comprobarCampos() {
        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tiempo = binding.etTiempo.getText().toString().trim();
                String repeticiones = binding.etReps.getText().toString().trim();
                String descanso = binding.etDescanso.getText().toString().trim();

                if (tiempo.isEmpty() || repeticiones.isEmpty() || descanso.isEmpty()){
                    Snackbar.make(binding.containerCronometro, "Hay campos vacíos", Snackbar.LENGTH_LONG).show();
                }else{
                    iniciarCronomentro(tiempo, repeticiones, descanso);
                }
            }
        });


    }

    private void iniciarCronomentro(String tiempo, String repeticiones, String descanso) {

        int time = Integer.parseInt(tiempo);
        final int reps = Integer.parseInt(repeticiones);
        final int rest = Integer.parseInt(descanso);

        final int[] duracionTotal = {(time * reps) + (rest * reps - 1)};
        final int maxProgress = 100;

        binding.progressBar.setMax(maxProgress);
        binding.progressBar.setProgress(0);
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.txtTimer.setVisibility(View.VISIBLE);
        binding.btnStart.setEnabled(false);

        new CountDownTimer(duracionTotal[0] * 1000L, 1000){

            int repeticionActual = 1;
            int duracionActual = time;
            int progresoActual = 0;

            @Override
            public void onTick(long millisUntilFinished) {

                if (duracionActual > 0){
                    binding.txtTimer.setText("Repetición: " + repeticionActual + "\nTiempo: " + duracionActual + " s");
                    duracionActual--;
                    progresoActual += maxProgress / (time * reps);
                    binding.progressBar.setProgress(progresoActual);

                }else{
                    binding.txtTimer.setText("Descanso: " + duracionActual);
                    duracionActual = time;
                    repeticionActual++;
                    progresoActual += maxProgress / (time * reps);
                    binding.progressBar.setProgress(progresoActual);

                    if (repeticionActual <= reps){
                        duracionTotal[0] += rest;
                    }
                }
            }

            @Override
            public void onFinish() {

                binding.txtTimer.setText("Fin de la serie");
                binding.progressBar.setProgress(100);
                Snackbar.make(binding.containerCronometro, "Temporizador terminado", Snackbar.LENGTH_LONG).show();

                binding.btnStart.setEnabled(true);

            }
        }.start();



    }



}