package com.example.energymapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.energymapp.R;
import com.example.energymapp.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    private static int TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("autoLogin", 0);
                boolean logueado = sharedPreferences.getBoolean("logueado", false);

                if (logueado){
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
            }
        },TIME_OUT);
    }
}