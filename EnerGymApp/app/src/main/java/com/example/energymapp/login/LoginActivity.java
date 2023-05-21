package com.example.energymapp.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.energymapp.view.MainActivity;
import com.example.energymapp.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    private String email;
    private String password;

    private String idUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Se inicializan las instancias de Firebase
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Usuario");

        iniciarSesion();
        goToRegistro();
        
        /*if (binding.cbRemember.isChecked()){
            guardarIdUsuario();
        }*/
    }

    private void guardarIdUsuario(String id) {
        SharedPreferences sharedPreferences = getSharedPreferences("usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("IdUsuario", id);
        editor.commit();

    }

    private void iniciarSesion() {

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = binding.etEmail.getText().toString().trim();
                password = binding.etPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Snackbar.make(binding.loginActivity, "Hay campos vacíos", Snackbar.LENGTH_LONG).show();
                } else {
                    login(email, password);
                    String emailIntroducido = binding.etEmail.getText().toString().trim();
                    if (!emailIntroducido.isEmpty()){
                        databaseReference.orderByChild("email").equalTo(emailIntroducido).addValueEventListener(new ValueEventListener() {
                            //snapshot contiene los valores que coincidern con el email
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                                    idUsuario = dataSnapshot.getKey();
                                    guardarIdUsuario(idUsuario);

                                    Log.i("RESULTADOS", idUsuario);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }
        });
    }

    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    Snackbar.make(binding.loginActivity, "BIENVENIDO", Snackbar.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                } else {
                    Snackbar.make(binding.loginActivity, "El usuario no existe\n¡REGISTRESE!", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void goToRegistro(){
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    /*private String obtenerIdUsuario(){

        *//*String emailIntroducido = binding.etEmail.getText().toString().trim();

        if (email.equals(emailIntroducido)){

            databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot dataSnapshot: snapshot.get){
                        Usuario usuario = snapshot.getValue(Usuario.class);
                        String id = usuario.getId();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }*//*

        String emailIntroducido = binding.etEmail.getText().toString().trim();
        if (!emailIntroducido.isEmpty()){
            databaseReference.orderByChild("email").equalTo(emailIntroducido).addValueEventListener(new ValueEventListener() {
                //snapshot contiene los valores que coincidern con el email
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        id = dataSnapshot.getKey();
                        Log.i("RESULTADOS", id);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        return id;
    }*/


}