package com.mounica.safetyapp;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.TextView;
import android.widget.Toast;

public class Speed extends AppCompatActivity {

    public Double lat1=0.0;
    public Double lon1=0.0;
    public int cnt=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed);



    try
    {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener()
        {

            public void onLocationChanged(Location location)
            {
                cnt++;
                Double lat=location.getLatitude();
                Double lon=location.getLongitude();
                TextView dis=(TextView)findViewById(R.id.t4);
                TextView sp=(TextView)findViewById(R.id.t5);

                if(cnt==1)
                {
                    lat1 = lat;
                    lon1 = lon;
                }
                if(cnt==2)
                {
                    double r = 6371.00;
                    double dlat = Math.toRadians(lat1 - lat);
                    double dlon = Math.toRadians(lon1 - lon);
                    double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) + Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(lat1)) * Math.sin(dlon / 2) * Math.sin(dlon / 2);
                    double c = 2 * Math.asin(Math.sqrt(a));
                    double dist = r * c;
                    double speed = dist / 60;
                    Toast.makeText(getApplicationContext(), "Lat :"+lat+"\nLog :"+lon+"\nLat :"+lat+"\nLog :"+lon, Toast.LENGTH_LONG).show();

                    dis.setText("Distance:" + dist);
                    sp.setText("Speed:" + speed);
                    cnt=0;
                }
                //Toast.makeText(getApplicationContext(), "Distance:"+dist, Toast.LENGTH_LONG).show();
               // Toast.makeText(getApplicationContext(), "Speed:"+speed, Toast.LENGTH_LONG).show();

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        Toast.makeText(getApplicationContext(), "innnn", Toast.LENGTH_LONG).show();
        Location loc=locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        Double lat=loc.getLatitude();
        Double lon=loc.getLongitude();
        Toast.makeText(getApplicationContext(), "Latitude : "+lat+"\nLongitude : "+lon, Toast.LENGTH_LONG).show();

       // Toast.makeText(getApplicationContext(),"s", Toast.LENGTH_LONG).show();

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,6000, 0, locationListener);

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
