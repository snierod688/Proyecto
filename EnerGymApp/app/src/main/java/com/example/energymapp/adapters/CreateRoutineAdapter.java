package com.example.energymapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.energymapp.R;
import com.example.energymapp.model.Ejercicio;

import java.util.List;

public class CreateRoutineAdapter extends RecyclerView.Adapter<CreateRoutineAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nombreEjercicio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreEjercicio = itemView.findViewById(R.id.txtExercise);
        }
    }

    public List<Ejercicio> ejercicio;

    public void updateList(List<Ejercicio> ejercicioList){
        ejercicio = ejercicioList;
        notifyDataSetChanged();
    }

    public CreateRoutineAdapter(List<Ejercicio> ejercicio){
        this.ejercicio = ejercicio;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_routine, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombreEjercicio.setText(ejercicio.get(position).getNombreEjercicio());

    }

    @Override
    public int getItemCount() {
        return ejercicio.size();
    }


}
