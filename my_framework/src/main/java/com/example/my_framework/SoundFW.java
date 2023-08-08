package com.example.my_framework;

import android.content.Context;

import android.media.AudioManager;
import android.media.SoundPool;

public class SoundFW
{
    private final SoundPool soundPool;
    private final Context context;
    int tap, damage, level_up, destroy;

    public SoundFW(CoreFW coreFW)
    {
        context = coreFW.getBaseContext();
        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        tap = soundPool.load(context, R.raw.tap,1);
        damage = soundPool.load(context, R.raw.damage,1);
        level_up = soundPool.load(context, R.raw.level_up,1);
        destroy = soundPool.load(context, R.raw.destroy,1);
    }

    public void start(String name)
    {
        switch (name)
        {
            case "tap" : soundPool.play(tap, 1, 1, 0, 0, 1); break;
            case "damage" : soundPool.play(damage, 1, 1, 0, 0, 1); break;
            case "level_up" : soundPool.play(level_up, 1, 1, 0, 0, 1); break;
            case "destroy" : soundPool.play(destroy, 1, 1, 0, 0, 1); break;
        }

    }
}

