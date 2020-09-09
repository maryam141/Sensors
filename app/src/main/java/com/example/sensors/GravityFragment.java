package com.example.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


    public class GravityFragment extends Fragment implements SensorEventListener {

        SensorManager sensorManager;
        Sensor sensorGravity;
        TextView gravityXTV ;
        TextView gravityYTV ;
        TextView gravityZTV ;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {

            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_gravity, container, false) ;
            gravityXTV = view.findViewById(R.id.gravity_x_tv);
            gravityYTV = view.findViewById(R.id.gravity_y_tv);
            gravityZTV = view.findViewById(R.id.gravity_z_tv);

            // Inflate the layout for this fragment
            return view;
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);


        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
            sensorGravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        }

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {


            if (sensorEvent.sensor.getType() == Sensor.TYPE_GRAVITY)
            {
                gravityXTV.setText("X : " +sensorEvent.values[0]);
                gravityYTV.setText("Y : "+sensorEvent.values[1]);
                gravityZTV.setText("Z : "+sensorEvent.values[2]);

            }



        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }


        @Override
        public void onResume() {
            super.onResume();
            sensorManager.registerListener(this, sensorGravity, SensorManager.SENSOR_DELAY_NORMAL);


        }

        @Override
        public void onPause() {
            super.onPause();
            sensorManager.unregisterListener(this);

        }



    }