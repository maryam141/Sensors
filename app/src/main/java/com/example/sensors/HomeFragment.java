package com.example.sensors;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment {

    Button proximityBtn ;
    Button accelerometerBtn;
    Button gyroscopeBtn ;
    Button stepCounterBtn;
    Button lightBtn ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home, container, false);

        proximityBtn = view.findViewById(R.id.proximity_btn);
        accelerometerBtn = view.findViewById(R.id.accelerometer_btn);
        gyroscopeBtn = view.findViewById(R.id.gyroscope_btn);
        stepCounterBtn =view.findViewById(R.id.stepCounter_btn);
        lightBtn = view.findViewById(R.id.light_btn);
        return view ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        proximityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_proximityFragment);

            }
        });
        accelerometerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_accelerometerFragment);

            }
        });
        gyroscopeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_gyroscopeFragment);

            }
        });
        stepCounterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_stepCounterFragment);
            }
        });
        lightBtn.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View view) {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_lightFragment);
          }
        });
    }
}