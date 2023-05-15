package com.example.energymapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.energymapp.R;
import com.example.energymapp.databinding.ActivityMainBinding;
import com.example.energymapp.view.fragments.ChronometerFragment;
import com.example.energymapp.view.fragments.routines.RoutinesFragment;
import com.example.energymapp.view.fragments.stats.StatsFragment;
import com.example.energymapp.view.fragments.train.TrainFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private RoutinesFragment routinesFragment = new RoutinesFragment();
    private TrainFragment trainFragment = new TrainFragment();
    private StatsFragment statsFragment = new StatsFragment();
    private ChronometerFragment chronometerFragment = new ChronometerFragment();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, routinesFragment).commit();

        binding.bottonNavigationBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.routine){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, routinesFragment).commit();
                    return true;
                }else if (item.getItemId() == R.id.train){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, trainFragment).commit();
                    return true;
                }else if (item.getItemId() == R.id.stats){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, statsFragment).commit();
                    return true;
                }else if (item.getItemId() == R.id.chronometer){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, chronometerFragment).commit();
                    return true;
                }else{
                    return false;
                }

            }
        });
    }
}