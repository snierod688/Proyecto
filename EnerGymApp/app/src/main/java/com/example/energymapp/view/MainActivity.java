package com.example.energymapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.energymapp.R;
import com.example.energymapp.databinding.ActivityMainBinding;
import com.example.energymapp.view.fragments.chronometer.ChronometerFragment;
import com.example.energymapp.view.fragments.routines.RoutinesFragment;
import com.example.energymapp.view.fragments.stats.StatsFragment;
import com.example.energymapp.view.fragments.train.TrainFragment;

public class MainActivity extends AppCompatActivity{

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

        getSupportFragmentManager().beginTransaction().replace(R.id.container, routinesFragment).commit();

        binding.bottonNavigationBar.setOnItemSelectedListener(item ->  {

                if (item.getItemId() == R.id.routine){
                    replaceFragment(new RoutinesFragment());
                    return true;
                }
                if (item.getItemId() == R.id.train){
                    replaceFragment(new TrainFragment());
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

                return true;

        });
    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}