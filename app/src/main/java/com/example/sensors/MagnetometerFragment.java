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




    public class MagnetometerFragment extends Fragment implements SensorEventListener {

        SensorManager sensorManager;
        Sensor magnetometerSensor;

        TextView magnetometerXTV ;
        TextView magnetometerYTV ;
        TextView magnetometerZTV ;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {

            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_magnetometer, container, false);
            magnetometerXTV = view.findViewById(R.id.gravity_x_tv);
            magnetometerYTV = view.findViewById(R.id.gravity_y_tv);
            magnetometerZTV = view.findViewById(R.id.gravity_z_tv);

            // Inflate the layout for this fragment
            return view;
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
            magnetometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);



        }

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            {
                magnetometerXTV.setText("X : " +sensorEvent.values[0]);
                magnetometerYTV.setText("Y : "+sensorEvent.values[1]);
                magnetometerZTV.setText("Z : "+sensorEvent.values[2]);

            }




//            float x =Math.round(sensorEvent.values[0]);
//            float y =Math.round(sensorEvent.values[1]);
//            float z =Math.round(sensorEvent.values[2]);
//
//            double tesla = Math.sqrt((x*x) + (y*y) + (z*z));
//
//            magnetometerTV.setText(tesla  + " μT");

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }

        @Override
        public void onResume() {
            super.onResume();
                sensorManager.registerListener(this, magnetometerSensor, SensorManager.SENSOR_DELAY_NORMAL);

        }

        @Override
        public void onPause() {
            super.onPause();
                sensorManager.unregisterListener(this);


        }
    }