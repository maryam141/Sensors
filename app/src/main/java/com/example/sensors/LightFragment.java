package com.example.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LightFragment extends Fragment implements SensorEventListener {
TextView lightTV ;
  private SensorManager sensorManager;
  private Sensor lightSensor;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {

    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_light, container, false);
    lightTV = view.findViewById(R.id.light_tv);
    // Inflate the layout for this fragment
    return view ;
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    sensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
    lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
  }

  @Override public void onSensorChanged(SensorEvent sensorEvent) {

    if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT)
    {
      lightTV.setText(""+sensorEvent.values[0]);
    }

  }

  @Override public void onAccuracyChanged(Sensor sensor, int i) {

  }

  @Override public void onResume() {
    super.onResume();
    sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);

  }

  @Override public void onPause() {
    super.onPause();
    sensorManager.unregisterListener(this);

  }
}