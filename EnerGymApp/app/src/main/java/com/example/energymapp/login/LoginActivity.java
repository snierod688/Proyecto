package com.example.energymapp.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.energymapp.view.AdminActivity;
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
    private String emailIntroducido;


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
    }

    //Se guarda el ID del usuario que ha iniciado sesión
    private void guardarIdUsuario(String id) {
        SharedPreferences sharedPreferences = getSharedPreferences("usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("IdUsuario", id);
        editor.commit();

    }

    //Se comprueban los campos y si se ha seleccionado la opción "Recuérdame"
    private void iniciarSesion() {

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = binding.etEmail.getText().toString().trim();
                password = binding.etPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Snackbar.make(binding.loginActivity, "Hay campos vacíos", Snackbar.LENGTH_LONG).show();
                } else {

                    if (binding.cbRemember.isChecked()){
                        SharedPreferences sharedPreferences = getSharedPreferences("autoLogin", 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("logueado", true);
                        editor.commit();
                    }

                    login(email, password);
                    emailIntroducido = binding.etEmail.getText().toString().trim();
                    if (!emailIntroducido.isEmpty()){
                        //Si el email que se encuentra en la base de datos coincide con el introducido, se guarda el ID del usuario
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

    //Se llama a Firebase para permitir el inicio de sesión del usuario
    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    if (emailIntroducido.equals("admin@gmail.com")){
                        Intent i = new Intent(LoginActivity.this, AdminActivity.class);
                        startActivity(i);
                    }else {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }

                } else {
                    Snackbar.make(binding.loginActivity, "El usuario no existe\n¡REGISTRESE!", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    //Se muestra la pantalla para registrase
    private void goToRegistro(){
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }



}