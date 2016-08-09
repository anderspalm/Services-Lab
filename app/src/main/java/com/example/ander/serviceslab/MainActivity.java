package com.example.ander.serviceslab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ander.serviceslab.service.MusicService;

public class MainActivity extends AppCompatActivity {

    ImageButton mPlayButton;
    ImageButton mPauseButton;
    ImageButton mStopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayButton = (ImageButton) findViewById(R.id.playButton);
        mPauseButton = (ImageButton) findViewById(R.id.pauseButton);
        mStopButton = (ImageButton) findViewById(R.id.stopButton);

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,  MusicService.class);
                startService(intent);
            }
        });

        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicService.pausePlayer();
            }
        });

        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, MusicService.class);
//                stopService(intent);
                MusicService.stopPlayer();
            }
        });

    }
}
