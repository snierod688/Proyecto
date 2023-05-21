package com.example.energymapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.energymapp.databinding.ActivityRegisterBinding;
import com.example.energymapp.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Se inicializan la instancias de Firebase
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        comprobarCampos();
    }

    private void comprobarCampos() {

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = binding.etUsername.getText().toString().trim();
                String nombre = binding.etName.getText().toString();
                String correo = binding.etEmail.getText().toString().trim();
                String password = binding.etPassword.getText().toString().trim();

                if (username.isEmpty() || nombre.isEmpty() || correo.isEmpty() || password.isEmpty()){
                    Snackbar.make(binding.registerActivity, "Hay campos vacíos", Snackbar.LENGTH_LONG).show();
                }else{
                    registro(username, nombre, correo, password);
                }
            }
        });
    }

    private void registro(String username, String nombre, String correo, String password) {
        mAuth.createUserWithEmailAndPassword(correo, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    String id = databaseReference.push().getKey();
                    Usuario usuario = new Usuario(id, username, nombre, correo, password);
                    databaseReference.child("Usuario").child(id).setValue(usuario);

                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    Snackbar.make(binding.registerActivity ,"Usuario registrado correctamente", Snackbar.LENGTH_LONG).show();

                }else{

                    if (task.getException()instanceof FirebaseAuthUserCollisionException){
                        Snackbar.make(binding.registerActivity ,"El usuario ya existe", Snackbar.LENGTH_LONG).show();

                    }else{
                        Snackbar.make(binding.registerActivity ,"Error al guardar", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}