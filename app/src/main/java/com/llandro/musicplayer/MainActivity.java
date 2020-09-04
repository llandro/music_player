package com.llandro.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.brg);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());

            }
        }, 0, 100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

        public void previous(View view) {
            goToPosition(0, true);
        }

        public void next(View view) {
            goToPosition(mediaPlayer.getDuration(), true);
        }

        public void goToPosition(int position, boolean setPause){
            mediaPlayer.seekTo(position);
            seekBar.setProgress(position);
            if(setPause){
                ImageView playButton = findViewById(R.id.PlayIconImageView);
                playButton.setImageResource(R.drawable.ic_play_arrow_orange_24);
                mediaPlayer.pause();
            }
    }

    public void play(View view) {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            ImageView playButton = findViewById(R.id.PlayIconImageView);
            playButton.setImageResource(R.drawable.ic_play_arrow_orange_24);
        }
        else {
            mediaPlayer.start();
            ImageView playButton = findViewById(R.id.PlayIconImageView);
            playButton.setImageResource(R.drawable.ic_pause_orange_24);

        }
    }


}