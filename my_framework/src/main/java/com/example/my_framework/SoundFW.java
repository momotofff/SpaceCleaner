package com.example.my_framework;

import android.media.AudioManager;
import android.media.SoundPool;

public class SoundFW
{
    int sound;
    SoundPool soundPool;

    public SoundFW(int sound)
    {
        soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        this.sound = sound;
    }

    public void play(float volume)
    {
        soundPool.play(sound, volume, volume, 0, 0, 1);
    }
}
