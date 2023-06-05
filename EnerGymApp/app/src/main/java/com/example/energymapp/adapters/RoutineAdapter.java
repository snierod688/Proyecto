package com.example.energymapp.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.energymapp.R;
import com.example.energymapp.model.Ejercicio;
import com.example.energymapp.model.Rutina;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.ViewHolder>{

    public List<Rutina> listaRutinas;

    public RoutineAdapter(List<Rutina> lista){
        this.listaRutinas = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_routines, parent, false);
        RoutineAdapter.ViewHolder viewHolder = new RoutineAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombreRutina.setText(listaRutinas.get(position).getNombreRutina());
    }

    @Override
    public int getItemCount() {
        return listaRutinas.size();
    }

    public void updateList(List<Rutina> rutinaList){
        listaRutinas = rutinaList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nombreRutina;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreRutina = itemView.findViewById(R.id.txtRoutine);
        }
    }
}
