package com.example.energymapp.view.fragments.routines;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.energymapp.R;

public class RoutinesFragment extends Fragment {

    public RoutinesFragment() {
        // Required empty public constructor
    }

    public static RoutinesFragment newInstance(String param1, String param2) {
        RoutinesFragment fragment = new RoutinesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_routines, container, false);
    }
}