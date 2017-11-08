package com.mounica.safetyapp;

import android.app.DownloadManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class NearestHospital extends AppCompatActivity implements SensorEventListener {

    Sensor accelerometer;
    SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest_hospital);

    }
    int count=0;
    int fcount=200;
    RequestQueue queue;





    int flgg=0;

    public void start(View v)
    {
        if(flgg==0) {
            sm = (SensorManager) getSystemService(SENSOR_SERVICE);
            accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            //acceleration=(TextView)findViewById(R.id.acceleration);
            Toast.makeText(this, "Started", Toast.LENGTH_SHORT);
            queue = Volley.newRequestQueue(this);
            flgg=1;
        }
        else
        {
            stop();
            flgg=0;
        }

    }

    public void stop()
    {
        sm.unregisterListener(this);
        Toast.makeText(this,"Stopped",Toast.LENGTH_SHORT).show();
    }
    String bf;
    DecimalFormat df2 = new DecimalFormat(".##");

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        final float x=sensorEvent.values[0];
        final float y=sensorEvent.values[1];
        final float z=sensorEvent.values[2];
        count++;
        double mag=x*x+y*y+z*z;
        double magr=Math.sqrt(mag);
        bf+=df2.format(magr)+",";
//        acceleration.setText("X:"+sensorEvent.values[0]+ "\nY:"+sensorEvent.values[1]+ "\nZ:"+sensorEvent.values[2]);
        //Toast.makeText(getApplicationContext(),"innn",Toast.LENGTH_SHORT).show();
        if(count==fcount&&count!=800&&count>1) {
            fcount=count+200;

            String url = "http://192.168.43.28:8000/api/";
            Toast.makeText(getApplicationContext(),"innn",Toast.LENGTH_SHORT).show();
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // response
                            Log.d("Response", response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            //Toast.makeText(getApplicationContext(),"innn"+error,Toast.LENGTH_SHORT).show();
                            Log.d("Error.Response", "" + error);
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("x",""+bf);
                    return params;
                }
            };
            queue.add(postRequest);
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }
}
