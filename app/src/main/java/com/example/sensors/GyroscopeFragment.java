package com.example.sensors;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GyroscopeFragment extends Fragment  {
    Gyroscope gyroscope ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gyroscope, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gyroscope = new Gyroscope(requireContext());
        gyroscope.setListner(new Gyroscope.Listner() {
            @Override
            public void onRotation(float rx, float ry, float rz) {
                if (rz > 1.0f){
                    GyroscopeFragment.this.getView().setBackgroundColor(Color.YELLOW);
                }else if (rz<-1.0f){
                    GyroscopeFragment.this.getView().setBackgroundColor(Color.MAGENTA);
                }

            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        gyroscope.register();
    }

    @Override
    public void onPause() {
        super.onPause();
        gyroscope.unregister();
    }

}