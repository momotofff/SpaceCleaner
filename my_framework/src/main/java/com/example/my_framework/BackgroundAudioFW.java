package com.example.my_framework;


import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

public class BackgroundAudioFW
{
    private final MediaPlayer mediaPlayer;
    private final Context context;

    public BackgroundAudioFW(CoreFW coreFW)
    {
        context = coreFW.getBaseContext();
        mediaPlayer = new MediaPlayer();
    }

    public void setTrack(int track)
    {
        Uri mediaPath = Uri.parse("android.resource://" + context.getPackageName() + "/" + track);

        try
        {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(context.getApplicationContext(), mediaPath);
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
        }
        catch (Exception e)
        {
            Log.w(this.getClass().getSimpleName(), "Error loading audio track " + track + ". Exception says: " + e.getMessage());
        }
    }

    public void start()
    {
        mediaPlayer.start();
    }

    public void stop()
    {
        mediaPlayer.stop();
    }
}
