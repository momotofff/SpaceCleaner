package com.example.my_framework;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class AudioFW
{
    public MediaPlayer mediaPlayer;
    Context context;

    public AudioFW(CoreFW coreFW, int track)
    {
        context = coreFW.getBaseContext();
        mediaPlayer = MediaPlayer.create(context, track);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }
}
