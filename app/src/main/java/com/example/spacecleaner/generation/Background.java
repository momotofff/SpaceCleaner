package com.example.spacecleaner.generation;

import android.graphics.Point;
import com.example.my_framework.GraphicsFW;
import com.example.spacecleaner.objects.Asteroid;
import com.example.spacecleaner.objects.Star;
import java.util.ArrayList;

public class Background
{
    public ArrayList<Star> stars = new ArrayList<>();
    public ArrayList<Asteroid> asteroids = new ArrayList<>();

    public Background(Point displaySize)
    {
        final int STARS_COUNT = 100;
        final int ASTEROIDS_COUNT = 5;

        for (int i = 0; i < STARS_COUNT; ++i)
            stars.add(new Star(displaySize));

        for (int i = 0; i < ASTEROIDS_COUNT; ++i)
            asteroids.add(new Asteroid(displaySize));

        for (Asteroid asteroid : asteroids)
            asteroid.speed = (int) (Math.random() * 10) + 10;

    }

    public void update()
    {
        for (Star star: stars)
             star.update();

        for (Asteroid asteroid: asteroids)
            asteroid.update();
    }

    public void drawing(GraphicsFW graphicsFW)
    {
        for (Star star: stars)
            star.drawing(graphicsFW);

        for (Asteroid asteroid: asteroids)
            asteroid.drawing(graphicsFW);
    }
}
