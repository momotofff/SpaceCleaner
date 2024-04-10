package com.example.my_framework;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

public class BackgroundAudioFW
{
    private final MediaPlayer mediaPlayer;
    private final Context context;
    private boolean isPlaying = false;
    private int currentPos = 0;

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
        if(!isPlaying)
        {
            try
            {
                mediaPlayer.seekTo(currentPos);
                mediaPlayer.start();
                isPlaying = true;
            }
            catch (Exception e)
            {
                System.out.println("ошибка");
            }

        }

    }

    public void stop()
    {
        mediaPlayer.stop();
    }


    public void pause()
    {
        if (isPlaying)
        {
            currentPos = mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
            isPlaying = false;
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
