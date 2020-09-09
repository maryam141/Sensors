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

public class GyroscopeFragment extends Fragment implements SensorEventListener {

   private SensorManager sensorManager;
    private Sensor sensorGyroscope;
   private TextView gyroscoperXTV ;
   private TextView gyroscopeYTV ;
   private TextView gyroscopeZTV ;
   private boolean isGyrscopeAvaliable ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gyroscope, container, false);
        gyroscoperXTV=view.findViewById(R.id.gravity_x_tv);
        gyroscopeYTV=view.findViewById(R.id.gravity_y_tv);
        gyroscopeZTV=view.findViewById(R.id.gravity_z_tv);

        // Inflate the layout for this fragment
        return view ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensorGyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        checkSensor();

    }

    public void checkSensor() {
        if (sensorGyroscope != null) {
            sensorGyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            isGyrscopeAvaliable = true;
        } else {
            Toast.makeText(requireContext(), "Gyrscope Sensor is not Availiable", Toast.LENGTH_SHORT).show();
            isGyrscopeAvaliable = false;

        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE)
        {
            gyroscoperXTV.setText("X : " +new DecimalFormat("##.#").format(sensorEvent.values[0]));
            gyroscopeYTV.setText("Y : "+new DecimalFormat("##.#").format(sensorEvent.values[1]));
            gyroscopeZTV.setText("Z : "+new DecimalFormat("##.#").format(sensorEvent.values[2]));


        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorGyroscope, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);

    }

}