package com.example.energymapp.view.fragments.chronometer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.energymapp.databinding.FragmentChronometerBinding;
import com.google.android.material.snackbar.Snackbar;

public class ChronometerFragment extends Fragment {

    private FragmentChronometerBinding binding;
    private int repActual;
    private String tiempo, repeticiones, descanso;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChronometerBinding.inflate(getLayoutInflater());
        repActual = 1;

        comprobarCampos();

        return binding.getRoot();

    }

    //Se comprueban que los campos no estén vacíos
    private void comprobarCampos() {
        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tiempo = binding.etTiempo.getText().toString().trim();
                repeticiones = binding.etReps.getText().toString().trim();
                descanso = binding.etDescanso.getText().toString().trim();

                if (tiempo.isEmpty() || repeticiones.isEmpty() || descanso.isEmpty()){
                    Snackbar.make(binding.containerCronometro, "Hay campos vacíos", Snackbar.LENGTH_LONG).show();
                }else{
                    binding.progressBar.setVisibility(View.VISIBLE);
                    binding.txtTimer.setVisibility(View.VISIBLE);
                    //Se inicia el cronómetro
                    iniciarCronomentro();
                }
            }
        });
    }

    //Contiene la lógica del cronómetro
    private void iniciarCronomentro() {

        int time = Integer.parseInt(tiempo);
        int reps = Integer.parseInt(repeticiones);

        new CountDownTimer(time*1000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                binding.txtTimer.setText(millisUntilFinished/1000 + " segundos\nrestantes");
            }

            @Override
            public void onFinish() {
                if (repActual<reps){
                    //Comienza el cronómtro del descanso
                    iniciarDescanso();
                }else  {
                    binding.txtTimer.setText("Cronómetro\nFinalizado");
                }
            }
        }.start();

    }

    //Contiene la lógica del cronómetro del descanso
    private void iniciarDescanso() {

        int rest = Integer.parseInt(descanso);

        new CountDownTimer(rest*1000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                binding.txtTimer.setText("Descanso restante\n" + millisUntilFinished/1000 + " s");
            }

            @Override
            public void onFinish() {
                repActual++;
                iniciarCronomentro();
            }
        }.start();
    }


}