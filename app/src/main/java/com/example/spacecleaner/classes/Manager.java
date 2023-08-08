package com.example.spacecleaner.classes;

import android.graphics.Point;

import com.example.my_framework.CollisionDetector;
import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.IDrawable;
import com.example.my_framework.TimerDelay;
import com.example.spacecleaner.generation.Background;
import com.example.spacecleaner.objects.Asteroid;
import com.example.spacecleaner.objects.Hud;
import com.example.spacecleaner.objects.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Manager
{
    public final Point maxScreen;
    public Background background;
    public Player player;
    public final Hud hud;
    public ArrayList<Asteroid> asteroids = new ArrayList<>();
    public int HUD_HEIGHT = 100;
    TimerDelay gameOverDelay = new TimerDelay();
    public boolean gameOver = false;

    public List<IDrawable> zOrder = new ArrayList<>();

    public Manager(CoreFW coreFW, Point displaySize)
    {
        int ASTEROIDS_COUNT = 2;
        this.maxScreen = displaySize;

        background = new Background(displaySize, HUD_HEIGHT);
        player = new Player(coreFW, maxScreen, HUD_HEIGHT);
        hud = new Hud(coreFW, player, HUD_HEIGHT);

        for (int i = 0; i < ASTEROIDS_COUNT; ++i)
            asteroids.add(new Asteroid(displaySize, HUD_HEIGHT));

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

        if (gameOverDelay.isElapsed(1))
            gameOver = true;
    }

    private void checkHit()
    {
        Optional<Asteroid> optional = asteroids.stream()
                                               .filter(asteroid -> CollisionDetector.detect(player, asteroid))
                                               .findFirst();

        optional.ifPresent(asteroid -> {
            player.hitAsteroid();
            asteroid.restartFromInitialPosition();
            asteroids.forEach(object -> --object.speed);

            if (!player.isAlive())
                gameOverDelay.start();
        });

        /* Old fashion
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
        */
    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW)
    {
        for (IDrawable drawable: zOrder)
            drawable.drawing(graphicsFW);
    }
}
