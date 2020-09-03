package com.example.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Gyroscope implements SensorEventListener {


    public interface Listner
    {
        void onRotation(float rx, float ry, float rz);
    }
    private Listner listner ;

    public void setListner(Listner l)
    {
        listner = l ;
    }

     SensorManager sensorManager;
     Sensor sensorGyroscope;

     Gyroscope(Context context){
         sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
         sensorGyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
     }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
         if (listner != null){
             listner.onRotation(sensorEvent.values[0],sensorEvent.values[1],sensorEvent.values[2]);
         }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    public void register(){
        sensorManager.registerListener(this, sensorGyroscope, SensorManager.SENSOR_DELAY_NORMAL);

    }
    public void unregister(){
        sensorManager.unregisterListener(this);

    }

}
