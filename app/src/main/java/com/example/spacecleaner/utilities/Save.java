package com.example.spacecleaner.utilities;

import java.io.Serializable;

public class Save implements Serializable
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

    public void save()
    {

    }

    public void load()
    {

    }

    public int[] getDistance() { return distance; }
}
