package com.example.energymapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.energymapp.R;
import com.example.energymapp.databinding.ActivityMainBinding;
import com.example.energymapp.login.LoginActivity;
import com.example.energymapp.view.fragments.chronometer.ChronometerFragment;
import com.example.energymapp.view.fragments.routines.RoutinesFragment;
import com.example.energymapp.view.fragments.info.StatsFragment;
import com.example.energymapp.view.fragments.train.RoutineTrainFragment;
import com.example.energymapp.view.fragments.train.TrainFragment;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity{

    private RoutinesFragment routinesFragment = new RoutinesFragment();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Snackbar.make(binding.activityMain, "BIENVENIDO", Snackbar.LENGTH_LONG).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, routinesFragment).commit();

        binding.bottonNavigationBar.setOnItemSelectedListener(item ->  {

                if (item.getItemId() == R.id.routine){
                    replaceFragment(new RoutinesFragment());
                    return true;
                }
                if (item.getItemId() == R.id.train){
                    replaceFragment(new RoutineTrainFragment());
                    return true;
                }
                if (item.getItemId() == R.id.stats){
                    replaceFragment(new StatsFragment());
                    return true;
                }
                if (item.getItemId() == R.id.chronometer){
                    replaceFragment(new ChronometerFragment());
                    return true;
                }
                if (item.getItemId() == R.id.close){

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("¿Quieres cerrar sesión")
                            .setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    //Se eliminan las credenciales del usuario logueado
                                    SharedPreferences sharedPreferences = getSharedPreferences("autoLogin", 0);
                                    SharedPreferences.Editor editor = sharedPreferences.edit().clear();
                                    editor.putBoolean("logueado", false);
                                    editor.commit();

                                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                                    startActivity(i);
                                }
                            })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    builder.show();
                }

                return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}