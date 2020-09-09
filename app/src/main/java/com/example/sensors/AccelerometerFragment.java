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


public class AccelerometerFragment extends Fragment implements SensorEventListener {

   private SensorManager sensorManager;
   private Sensor accelerometerSensor;
   private TextView accelerometerXTV ;
   private TextView accelerometerYTV ;
   private TextView accelerometerZTV ;
   private boolean isAccelerometerAvaliable ;



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
            accelerometerXTV = view.findViewById(R.id.accelerate_x_tv);
        accelerometerYTV = view.findViewById(R.id.accelerate_y_tv);
        accelerometerZTV = view.findViewById(R.id.accelerate_z_tv);

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
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        checkSensor();

    }
   public void checkSensor(){
       if (accelerometerSensor != null){
           accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
           isAccelerometerAvaliable=true ;
       }
       else {
           Toast.makeText(requireContext(), "Accelerometer Sensor is not Availiable", Toast.LENGTH_SHORT).show();
           isAccelerometerAvaliable=false;

       }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {


        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            accelerometerXTV.setText("X : " +new DecimalFormat("##.#").format(sensorEvent.values[0]));
            accelerometerYTV.setText("Y : "+new DecimalFormat("##.#").format(sensorEvent.values[1]));
            accelerometerZTV.setText("Z : "+new DecimalFormat("##.#").format(sensorEvent.values[2]));

        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);

    }


}