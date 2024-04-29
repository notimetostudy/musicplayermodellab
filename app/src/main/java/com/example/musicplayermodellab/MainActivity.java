package com.example.musicplayermodellab;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button buttonPlay;
    private Button buttonPause;
    private Button buttonResume;
    private Button buttonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        buttonPlay = findViewById(R.id.button_play);
        buttonPause = findViewById(R.id.button_pause);
        buttonResume = findViewById(R.id.button_resume);
        buttonStop = findViewById(R.id.button_stop);

        // Initialize MediaPlayer with audio file
        mediaPlayer = MediaPlayer.create(this, R.raw.audio);

        // Set listeners for playback controls
        mediaPlayer.setOnCompletionListener(mp -> {
            buttonPlay.setEnabled(true);
            buttonPause.setEnabled(false);
            buttonResume.setEnabled(false);
            buttonStop.setEnabled(false);
        });
    }

    public void play(View view) {
        mediaPlayer.start();
        buttonPlay.setEnabled(false);
        buttonPause.setEnabled(true);
        buttonResume.setEnabled(false);
        buttonStop.setEnabled(true);
    }

    public void pause(View view) {
        mediaPlayer.pause();
        buttonPlay.setEnabled(false);
        buttonPause.setEnabled(false);
        buttonResume.setEnabled(true);
        buttonStop.setEnabled(true);
    }

    public void resume(View view) {
        mediaPlayer.start();
        buttonPlay.setEnabled(false);
        buttonPause.setEnabled(true);
        buttonResume.setEnabled(false);
        buttonStop.setEnabled(true);
    }

    public void stop(View view) {
        mediaPlayer.stop();
        mediaPlayer.prepareAsync();
        mediaPlayer.seekTo(0);
        buttonPlay.setEnabled(true);
        buttonPause.setEnabled(false);
        buttonResume.setEnabled(false);
        buttonStop.setEnabled(false);
        Toast.makeText(this, "Music has been stopped", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}


