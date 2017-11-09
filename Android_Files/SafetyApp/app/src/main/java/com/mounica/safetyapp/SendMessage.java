package com.mounica.safetyapp;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class SendMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
    }
    public void sendmessage(View v)
    {
        //   TextView tv=(TextView) findViewById(R.id.tv);
        // tv.setText("Second");
        try
        {
            LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            LocationListener locationListener = new LocationListener()
            {
                public void onLocationChanged(Location location)
                {
                    Double lat=location.getLatitude();
                    Double lon=location.getLongitude();
                    Toast.makeText(getApplicationContext(), "Lat :"+lat+"\nLog :"+lon, Toast.LENGTH_LONG).show();
                }

                public void onStatusChanged(String provider, int status, Bundle extras) {}

                public void onProviderEnabled(String provider) {}

                public void onProviderDisabled(String provider) {}
            };

            Location loc=locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            Double lat=loc.getLatitude();
            Double lon=loc.getLongitude();
            //  Toast.makeText(getApplicationContext(), "Latitude : "+lat+"\nLongitude : "+lon, Toast.LENGTH_LONG).show();

            Toast.makeText(getApplicationContext(),"", Toast.LENGTH_LONG).show();

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,60000, 0, locationListener);


            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("9566952655", null,"This is test hope it works \nLat : "+lat+"\nLog : "+lon, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        }
        catch (SecurityException e)
        {
            Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }


}
