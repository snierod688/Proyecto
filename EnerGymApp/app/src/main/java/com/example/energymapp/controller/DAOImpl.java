package com.example.energymapp.controller;

import androidx.annotation.NonNull;

import com.example.energymapp.databinding.ActivityLoginBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class DAOImpl implements DAO{

    private DatabaseReference databaseReference;
    private ActivityLoginBinding binding;


    @Override
    public String obtenerIdUsuario() {

        final String[] id = new String[1];
        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    String emailGuardado = snapshot.child("email").getValue().toString();

                    if (binding.etEmail.getText().toString().equals(emailGuardado)){
                        id[0] = snapshot.child("id").getValue().toString();
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return id[0];
    }
}
