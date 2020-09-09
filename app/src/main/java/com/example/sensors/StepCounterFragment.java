package com.example.sensors;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class StepCounterFragment extends Fragment implements SensorEventListener {

 private SensorManager sensorManager;
 private Sensor stepCounterSensor;
  private boolean run;
  private TextView countTV;
 private boolean isStepCounterAvaliable ;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {

    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_step_counter, container, false);

    countTV = view.findViewById(R.id.count_tv);
    // Inflate the layout for this fragment
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
    stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

    if(ContextCompat.checkSelfPermission(requireContext(),
            Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
      //ask for permission
      requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 10);
    }

    checkSensor();
  }

  public void checkSensor() {
    if (stepCounterSensor != null) {
      stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
      isStepCounterAvaliable = true;
    } else {
      Toast.makeText(requireContext(), "Step Counter Sensor is not Availiable", Toast.LENGTH_SHORT).show();
      isStepCounterAvaliable = false;

    }
  }

  @Override
  public void onSensorChanged(SensorEvent sensorEvent) {
    if (run) {
      if (countTV != null) {
        countTV.setText(String.valueOf(sensorEvent.values[0]));
      }
    }
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int i) {
  }

  @Override
  public void onResume() {
    super.onResume();
    run = true;
    PackageManager pm = getContext().getPackageManager();
    if (pm.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER)) {
      if (stepCounterSensor == null) {
        Toast.makeText(requireContext(), " Count Sensor Not Avaliable", Toast.LENGTH_SHORT).show();
      } else {
        sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);
      }
    }


  }

  @Override
  public void onPause() {
    super.onPause();
    run = false;
    //  sensorManager.unregisterListener(this);

  }
}