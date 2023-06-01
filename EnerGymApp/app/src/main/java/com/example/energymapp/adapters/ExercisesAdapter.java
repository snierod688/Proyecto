package com.example.energymapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.energymapp.ExerciseRecyclerViewInterface;
import com.example.energymapp.R;
import com.example.energymapp.databinding.ItemExerciseBinding;
import com.example.energymapp.model.Ejercicio;
import com.example.energymapp.model.Serie;
import com.example.energymapp.view.fragments.routines.CreateRoutineFragment;
import com.example.energymapp.view.fragments.routines.ExercisesFragment;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ViewHolder> implements View.OnClickListener {

    public List<Ejercicio> ejerciciosList;
    private View.OnClickListener listener;

    public ExercisesAdapter(List<Ejercicio> ejerciciosList){
        this.ejerciciosList = ejerciciosList;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nombreEjercicio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreEjercicio = itemView.findViewById(R.id.txtExerciseName);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise, parent, false);
        ExercisesAdapter.ViewHolder viewHolder = new ExercisesAdapter.ViewHolder(view);

        view.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombreEjercicio.setText(ejerciciosList.get(position).getNombre());
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return ejerciciosList.size();
    }

}
