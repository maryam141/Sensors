package com.example.sensors;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AccelerometerFragment extends Fragment {

Accelerometer accelerometer ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accelerometer, container, false) ;


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        accelerometer = new Accelerometer(requireContext());
        accelerometer.setListner(new Accelerometer.Listner() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                if (tx>1.0f)
                {
                    AccelerometerFragment.this.getView().setBackgroundColor(Color.CYAN);

                }
                else if (tx<-1.0f){
                    AccelerometerFragment.this.getView().setBackgroundColor(Color.BLUE);

                }
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        accelerometer.register();

    }

    @Override
    public void onPause() {
        super.onPause();
        accelerometer.unregister();

    }
}