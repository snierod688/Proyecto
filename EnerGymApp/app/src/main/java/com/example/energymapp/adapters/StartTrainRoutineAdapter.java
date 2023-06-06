package com.example.energymapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.energymapp.R;
import com.example.energymapp.model.Ejercicio;

import java.util.List;

public class StartTrainRoutineAdapter extends RecyclerView.Adapter<StartTrainRoutineAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nombreEjercicio;
        private EditText etReps1, etReps2, etReps3, etReps4, etReps5;
        private EditText etPeso1, etPeso2, etPeso3, etPeso4, etPeso5;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreEjercicio = itemView.findViewById(R.id.txtExercise);

            etReps1 = itemView.findViewById(R.id.etReps);
            etReps2 = itemView.findViewById(R.id.etReps2);
            etReps3 = itemView.findViewById(R.id.etReps3);
            etReps4 = itemView.findViewById(R.id.etReps4);
            etReps5 = itemView.findViewById(R.id.etReps5);

            etPeso1 = itemView.findViewById(R.id.etWeight);
            etPeso2 = itemView.findViewById(R.id.etWeight2);
            etPeso3 = itemView.findViewById(R.id.etWeight3);
            etPeso4 = itemView.findViewById(R.id.etWeight4);
            etPeso5 = itemView.findViewById(R.id.etWeight5);
        }
    }

    public List<Ejercicio> ejercicio;

    public void updateList(List<Ejercicio> ejercicioList){
        ejercicio = ejercicioList;
        notifyDataSetChanged();
    }

    public StartTrainRoutineAdapter(List<Ejercicio> ejercicio){
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
