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
import android.widget.Toast;

import java.text.DecimalFormat;


public class TempreatureFragment extends Fragment implements SensorEventListener {

  private TextView tempTV;
  private SensorManager sensorManager ;
  private Sensor tempreatureSensor ;
  private boolean isTempSensorAvaliable ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_tempreature, container, false);
        tempTV=view.findViewById(R.id.temp_tv);
        // Inflate the layout for this fragment
        return view ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sensorManager = (SensorManager)getActivity(). getSystemService(Context.SENSOR_SERVICE);
      tempreatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);


checkSensor();
    }
    public void checkSensor() {

        if (tempreatureSensor != null) {
            tempreatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isTempSensorAvaliable = true;
        } else {
            Toast.makeText(requireContext(), "Temprature Sensor is not Availiable", Toast.LENGTH_SHORT).show();
            isTempSensorAvaliable = false;

        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        tempTV.setText(new DecimalFormat("##.#").format(sensorEvent.values[0])+"°C");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (isTempSensorAvaliable){
            sensorManager.registerListener(this, tempreatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if (isTempSensorAvaliable){
            sensorManager.unregisterListener(this);

        }
    }
}