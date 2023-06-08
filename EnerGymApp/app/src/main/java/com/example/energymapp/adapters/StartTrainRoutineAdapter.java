package com.example.energymapp.adapters;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
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

    public static List<Ejercicio> ejercicio;

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

        holder.etReps1.setText(ejercicio.get(position).getReps1());
        holder.etReps2.setText(ejercicio.get(position).getReps2());
        holder.etReps3.setText(ejercicio.get(position).getReps3());
        holder.etReps4.setText(ejercicio.get(position).getReps4());

        holder.etPeso1.setText(ejercicio.get(position).getPeso1());
        holder.etPeso2.setText(ejercicio.get(position).getPeso2());
        holder.etPeso3.setText(ejercicio.get(position).getPeso3());
        holder.etPeso4.setText(ejercicio.get(position).getPeso4());


        /*holder.txtAnterior1.setText(holder.etReps1 + "x" + holder.etPeso1);
        holder.txtAnterior2.setText(holder.etReps2 + "x" + holder.etPeso2);
        holder.txtAnterior3.setText(holder.etReps3 + "x" + holder.etPeso3);
        holder.txtAnterior4.setText(holder.etReps4 + "x" + holder.etPeso4);*/

    }

    @Override
    public int getItemCount() {
        return ejercicio.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nombreEjercicio;
        private EditText etReps1, etReps2, etReps3, etReps4;
        private EditText etPeso1, etPeso2, etPeso3, etPeso4;

        private TextView txtAnterior1, txtAnterior2, txtAnterior3, txtAnterior4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreEjercicio = itemView.findViewById(R.id.txtExerciseRoutine);

            etReps1 = itemView.findViewById(R.id.etReps);
            etReps2 = itemView.findViewById(R.id.etReps2);
            etReps3 = itemView.findViewById(R.id.etReps3);
            etReps4 = itemView.findViewById(R.id.etReps4);

            etPeso1 = itemView.findViewById(R.id.etWeight);
            etPeso2 = itemView.findViewById(R.id.etWeight2);
            etPeso3 = itemView.findViewById(R.id.etWeight3);
            etPeso4 = itemView.findViewById(R.id.etWeight4);

            //Reps1
            etReps1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    ejercicio.get(getAdapterPosition()).setReps1(etReps1.getText().toString());

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            //Reps2
            etReps2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    ejercicio.get(getAdapterPosition()).setReps2(etReps2.getText().toString());

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            //Reps3
            etReps3.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    ejercicio.get(getAdapterPosition()).setReps3(etReps3.getText().toString());

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            //Reps4
            etReps4.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    ejercicio.get(getAdapterPosition()).setReps4(etReps4.getText().toString());

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            //Peso1
            etPeso1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    ejercicio.get(getAdapterPosition()).setPeso1(etPeso1.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            //Peso2
            etPeso2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    ejercicio.get(getAdapterPosition()).setPeso2(etPeso2.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            //Peso3
            etPeso3.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    ejercicio.get(getAdapterPosition()).setPeso3(etPeso3.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            //Peso4
            etPeso4.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    ejercicio.get(getAdapterPosition()).setPeso4(etPeso4.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        }
    }


}
