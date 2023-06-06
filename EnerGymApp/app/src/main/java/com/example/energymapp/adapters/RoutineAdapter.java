package com.example.energymapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.energymapp.R;
import com.example.energymapp.model.Rutina;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.ViewHolder> implements View.OnClickListener{

    public List<Rutina> listaRutinas;
    public DatabaseReference databaseReference;
    private String idUsuario;
    private String idRutina;
    private View.OnClickListener listener;

    public RoutineAdapter(List<Rutina> lista) {

        this.listaRutinas = lista;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nombreRutina;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreRutina = itemView.findViewById(R.id.txtNombreRutina);
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_routines, parent, false);
        RoutineAdapter.ViewHolder viewHolder = new RoutineAdapter.ViewHolder(view);

        view.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.nombreRutina.setText(listaRutinas.get(position).getNombreRutina());

       /* holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialogUpdate dialogUpdate = new ViewDialogUpdate();
                dialogUpdate.showDialog(v.getContext(), rutina.getIdUsuario(), rutina.getIdRutina());
            }
        });*/
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return listaRutinas.size();
    }

    public void updateList(List<Rutina> rutinaList) {
        listaRutinas = rutinaList;
        notifyDataSetChanged();
    }
}