package com.mounica.safetyapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by raju on 28-Oct-17.
 */
public class FirstScreen extends AppCompatActivity {

    int flg=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main2);
    }

    MediaPlayer mp;
    public void ring(View v)
    {

        if(flg==0)
        {
            mp= MediaPlayer.create(this, R.raw.sound);
            Toast.makeText(getApplication(),"Start",Toast.LENGTH_SHORT).show();
            mp.start();
            flg=1;
        }
        else
        {
            mp.stop();
            Toast.makeText(getApplication(),"Stop",Toast.LENGTH_SHORT).show();
            flg=0;
        }
    }

    public void home(View v)
    {
        startActivity(new Intent(this,Main2Activity.class));
    }
    public void reg(View v)
    {
        startActivity(new Intent(this,MainActivity.class));
    }
}