package com.example.spacecleaner.classes;

import android.graphics.Point;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.IDrawable;
import com.example.spacecleaner.generation.Background;
import com.example.spacecleaner.objects.Asteroid;
import com.example.spacecleaner.objects.Hud;
import com.example.spacecleaner.objects.Player;
import java.util.ArrayList;
import java.util.List;

public class Manager
{
    private Point maxScreen;
    Background background;
    Asteroid asteroid;
    Player player;
    Hud hud;

    private int passedDistance;
    private int currentSpeedPlayer;
    private int currentShieldsPlayer;

    // If addition of entities will be needed, use TreeMap instead
    List<IDrawable> zOrder = new ArrayList<>();

    public Manager(CoreFW coreFW, Point sizeDisplay)
    {
        this.maxScreen = sizeDisplay;
        hud = new Hud(coreFW);
        background = new Background(sizeDisplay, hud.getHeight());
        asteroid = new Asteroid(sizeDisplay, hud.getHeight());
        player = new Player(coreFW, maxScreen, hud.getHeight());
        hud = new Hud(coreFW);

        zOrder.add(background);
        zOrder.add(asteroid);
        zOrder.add(player);
    }

    public void update()
    {
        passedDistance += player.speed;
        currentSpeedPlayer = player.speed;
        currentShieldsPlayer = player.getShieldsPlayer();

        for (IDrawable drawable: zOrder)
            drawable.update();

        hud.update(passedDistance, currentSpeedPlayer, currentShieldsPlayer);    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW)
    {
        for (IDrawable drawable: zOrder)
            drawable.drawing(graphicsFW);

        hud.drawing(graphicsFW);
    }
}
