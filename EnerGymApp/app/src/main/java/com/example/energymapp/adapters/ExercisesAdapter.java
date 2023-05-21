package com.example.energymapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.energymapp.R;
import com.example.energymapp.databinding.ItemExerciseBinding;
import com.example.energymapp.model.Ejercicio;
import com.example.energymapp.model.Serie;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ViewHolder> {

    public List<Ejercicio> ejerciciosList;

    public ExercisesAdapter(List<Ejercicio> ejerciciosList){
        this.ejerciciosList = ejerciciosList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_routine, parent, false);
        ExercisesAdapter.ViewHolder viewHolder = new ExercisesAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombreEjercicio.setText(ejerciciosList.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return ejerciciosList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nombreEjercicio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreEjercicio = itemView.findViewById(R.id.txtExerciseName);
        }
    }
}
