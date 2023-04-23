package pmdm.t4.ener_gym.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

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
        String confPassword = binding.etPasswordConfirm.getText().toString().trim();


        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}