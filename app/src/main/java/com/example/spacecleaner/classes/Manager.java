package com.example.spacecleaner.classes;

import android.graphics.Point;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.spacecleaner.objects.Player;

public class Manager
{
    private Point maxScreen;
    private final Point minScreen = new Point(0, 0);

    Player player;

    public Manager(CoreFW coreFW, Point sceneSize)
    {
        this.maxScreen.x = sceneSize.x;
        this.maxScreen.y = sceneSize.y;
        player = new Player(maxScreen, minScreen.y);
    }

    public void update()
    {
        player.update();
    }

    public  void drawind(CoreFW coreFW, GraphicsFW graphicsFW)
    {
        player.drawing(graphicsFW);
    }
}
