package pmdm.t4.ener_gym.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import pmdm.t4.ener_gym.MainActivity;
import pmdm.t4.ener_gym.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Se inicializa la instancia de Firebase
        mAuth = FirebaseAuth.getInstance();

        iniciarSesion();
        registro();
    }

    private void iniciarSesion() {

        String username = binding.etUsername.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (username.isEmpty() || password.isEmpty()) {
                    Snackbar.make(binding.loginActivity, "Hay campos vacíos", Snackbar.LENGTH_LONG).show();
                } else {
                    login(username, password);

                }
            }
        });
    }

    private void login(String username, String password) {
        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Snackbar.make(binding.loginActivity, "BIENVENIDO " + username, Snackbar.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Snackbar.make(binding.loginActivity, "El usuario no existe\n¡REGISTRESE!", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void registro(){
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}