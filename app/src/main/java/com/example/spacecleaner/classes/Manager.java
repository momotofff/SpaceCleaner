package com.example.spacecleaner.classes;

import android.graphics.Point;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.spacecleaner.generation.Background;
import com.example.spacecleaner.objects.Asteroid;
import com.example.spacecleaner.objects.Hud;
import com.example.spacecleaner.objects.Player;

public class Manager
{
    private Point maxScreen;
    Background background;
    Asteroid asteroid;
    Player player;
    Hud hud;

    public Manager(CoreFW coreFW, Point sizeDisplay)
    {
        this.maxScreen = sizeDisplay;
        background = new Background(sizeDisplay);
        asteroid = new Asteroid(sizeDisplay);
        player = new Player(coreFW, maxScreen, 0);
        hud = new Hud(coreFW);
    }

    public void update()
    {
        background.update();
        asteroid.update();
        player.update();
    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW)
    {
        background.drawing(graphicsFW);
        asteroid.drawing(graphicsFW);
        player.drawing(graphicsFW);
    }
}
