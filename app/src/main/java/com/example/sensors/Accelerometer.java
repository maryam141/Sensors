package com.example.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Accelerometer implements SensorEventListener {


    public interface Listner
    {
        void onTranslation(float tx , float ty , float tz);
    }
    private Listner listner ;

    public void setListner(Listner l){
        listner = l ;
    }

     SensorManager sensorManager;
     Sensor sensor;

     Accelerometer(Context context){
         sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
         sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
     }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
         if (listner != null){
             listner.onTranslation(sensorEvent.values[0],sensorEvent.values[1],sensorEvent.values[2]);
         }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    public void register(){
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }
    public void unregister(){
        sensorManager.unregisterListener(this);

    }
}
