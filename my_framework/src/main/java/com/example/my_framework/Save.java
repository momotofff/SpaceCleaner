package com.example.my_framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        try
        {
            FileOutputStream fos = new FileOutputStream("save");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(distance);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public Save load()
    {
        try
        {
            FileInputStream fis = new FileInputStream("save");
            ObjectInputStream ois = new ObjectInputStream(fis);

            Save save = (Save) ois.readObject();
            return save;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public int[] getDistance() { return distance; }
}
