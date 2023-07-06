package com.example.spacecleaner.classes;

import android.graphics.Point;

import com.example.my_framework.CollisionDetector;
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
    private final Point maxScreen;
    public static Background background;
    private final Player player;
    private final Hud hud;
    private final ArrayList<Asteroid> asteroids = new ArrayList<>();
    public static boolean gameOver;

    // If addition of entities will be needed, use TreeMap instead
    List<IDrawable> zOrder = new ArrayList<>();

    public Manager(CoreFW coreFW, Point displaySize)
    {
        // TODO: Use screen dimensions to calculate it
        final int HUD_HEIGHT = 100;
        final int ASTEROIDS_COUNT = 5;
        gameOver = false;
        this.maxScreen = displaySize;

        background = new Background(displaySize, HUD_HEIGHT);
        player = new Player(coreFW, maxScreen, HUD_HEIGHT);
        hud = new Hud(coreFW, player, HUD_HEIGHT);

        for (int i = 0; i < ASTEROIDS_COUNT; ++i)
            asteroids.add(new Asteroid(displaySize, HUD_HEIGHT, (int) (Math.random() * 10) + 10));

        zOrder.add(background);
        zOrder.addAll(asteroids);
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
        for (Asteroid asteroid: asteroids)
        {
            if (CollisionDetector.detect(player, asteroid))
            {
                player.hitAsteroid();
                asteroid.restartFromInitialPosition();

                for (Asteroid ast: asteroids)
                    ast.speed--;

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
