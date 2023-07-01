package com.example.spacecleaner.classes;

import android.graphics.Point;

import com.example.my_framework.CollisionDetect;
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
    public static Background background;
    Player player;
    Hud hud;

    // If addition of entities will be needed, use TreeMap instead
    List<IDrawable> zOrder = new ArrayList<>();

    public Manager(CoreFW coreFW, Point sizeDisplay)
    {
        // TODO: Use screen dimensions to calculate it
        final int HUD_HEIGHT = 100;

        this.maxScreen = sizeDisplay;

        background = new Background(sizeDisplay, HUD_HEIGHT);
        player = new Player(coreFW, maxScreen, HUD_HEIGHT);
        hud = new Hud(coreFW, player, HUD_HEIGHT);

        zOrder.add(background);
        zOrder.add(player);
        zOrder.add(hud);
    }

    public void update()
    {
        for (IDrawable drawable: zOrder)
            drawable.update();

        checkHit();
    }

    private void checkHit()
    {
        for (Asteroid asteroid: background.asteroids)
        {
            if (CollisionDetect.collisionDetect(player, asteroid))
            {
                player.hitAsteroid();
                background.hitPlayer(asteroid);
                break;
            }
        }
    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW)
    {
        for (IDrawable drawable: zOrder)
            drawable.drawing(graphicsFW);
    }
}
