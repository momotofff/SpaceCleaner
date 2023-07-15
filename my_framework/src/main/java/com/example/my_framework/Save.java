package com.example.my_framework;

import android.content.SharedPreferences;

import com.example.my_framework.CoreFW;

public class Save
{
    // TODO: Think about PriorityQueue
    private int[] distance = {0, 0, 0, 0, 0};

    public Save(int[] distance)
    {
        this.distance = distance;
    }

    public Save(){}

    public void addDistance(int value)
    {
        for (int i = 0; i < distance.length; ++i)
        {
            int number = value;
            if (number > distance[i])
            {
                value = distance[i];
                distance[i] = number;
            }
        }
    }

    public void save(SharedPreferences preferences)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();

        // TODO: Use serialization
        for (int i = 0; i < distance.length; ++i)
            editor.putInt("PassedDistance" + i, distance[i]);

        editor.apply();
    }

    public void load(SharedPreferences preferences)
    {
        for (int i = 0; i < distance.length; ++i)
            distance[i] = preferences.getInt("PassedDistance" + i, 0 );
    }

    public int[] getDistance() { return distance; }
}
