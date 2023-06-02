package com.example.spacecleaner.classes;

import android.graphics.Point;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.spacecleaner.objects.Player;

public class Manager
{
    private Point maxScreen;

    Player player;

    public Manager(CoreFW coreFW, Point sizeDisplay)
    {
        this.maxScreen = sizeDisplay;
        player = new Player(coreFW, maxScreen, 0);
    }

    public void update()
    {
        player.update();
    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW)
    {
        player.drawing(graphicsFW);
    }
}
