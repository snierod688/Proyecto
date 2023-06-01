package com.example.energymapp.view.fragments.routines;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.energymapp.R;
import com.example.energymapp.adapters.CreateRoutineAdapter;
import com.example.energymapp.databinding.FragmentCreateRoutineBinding;
import com.example.energymapp.model.Serie;

import java.util.ArrayList;
import java.util.List;

public class CreateRoutineFragment extends Fragment {

    private FragmentCreateRoutineBinding binding;
    private CreateRoutineAdapter adapter;
    private String nombreEjercicio;

    public CreateRoutineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateRoutineBinding.inflate(getLayoutInflater());

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExercisesFragment fragment = new ExercisesFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, fragment).commit();
            }
        });

        nombreEjercicio = obtenerNombreEjercicio();

        adapter = new CreateRoutineAdapter(rellenarLista());

        binding.rvRutina.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvRutina.setAdapter(adapter);



        return binding.getRoot();
    }

    private String obtenerNombreEjercicio(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("nombreEjercicio", Context.MODE_PRIVATE);
        String ejercicio = sharedPreferences.getString("ejercicio", "");

        //Log.i("name", nombreEjercicio);

        return ejercicio;
    }

    private List<Serie> rellenarLista(){
        List<Serie> ejercicioList = new ArrayList<>();
        ejercicioList.add(new Serie(nombreEjercicio));

        Log.i("list", ejercicioList.toString());

        return ejercicioList;
    }
}