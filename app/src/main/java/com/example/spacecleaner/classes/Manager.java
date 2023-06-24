package com.example.spacecleaner.classes;

import android.graphics.Point;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.IDrawable;
import com.example.my_framework.ObjectFW;
import com.example.spacecleaner.generation.Background;
import com.example.spacecleaner.objects.Asteroid;
import com.example.spacecleaner.objects.Hud;
import com.example.spacecleaner.objects.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Manager
{
    private Point maxScreen;
    Background background;
    Asteroid asteroid;
    Player player;
    Hud hud;

    // If addition of entities will be needed, use TreeMap instead
    List<IDrawable> zOrder = new ArrayList<>();

    public Manager(CoreFW coreFW, Point sizeDisplay)
    {
        this.maxScreen = sizeDisplay;

        background = new Background(sizeDisplay);
        asteroid = new Asteroid(sizeDisplay);
        player = new Player(coreFW, maxScreen, 0);
        hud = new Hud(coreFW);

        zOrder.add(background);
        zOrder.add(asteroid);
        zOrder.add(player);
    }

    public void update()
    {
        for (IDrawable drawable: zOrder)
            drawable.update();
    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW)
    {
        for (IDrawable drawable: zOrder)
            drawable.drawing(graphicsFW);
    }
}
