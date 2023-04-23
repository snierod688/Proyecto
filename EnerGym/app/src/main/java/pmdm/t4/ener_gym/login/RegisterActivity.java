package pmdm.t4.ener_gym.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import pmdm.t4.ener_gym.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Se inicializan la instancias de Firebase
        mFirebase = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        comprobarCampos();
    }

    private void comprobarCampos() {

        String username = binding.etUsername.getText().toString().trim();
        String nombre = binding.etName.getText().toString();
        String correo = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    String id = mAuth.getCurrentUser().getUid();
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", id);
                    map.put("nombre", nombre);
                    map.put("correo_electronico", correo);
                    map.put("contrasenia", password);
                    map.put("username", username);

                    mFirebase.collection("Usuarios").document(id).set(map);
                    Snackbar.make(binding.registerActivity ,"Usuario registrado correctamente", Snackbar.LENGTH_LONG);
                    finish();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }else{

                    if (task.getException()instanceof FirebaseAuthUserCollisionException){
                        Snackbar.make(binding.registerActivity ,"El usuario ya existe", Snackbar.LENGTH_LONG);

                    }else{
                        Snackbar.make(binding.registerActivity ,"Error al guardar", Snackbar.LENGTH_LONG);
                    }
                }
            }
        });
    }
}