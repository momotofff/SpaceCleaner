package com.example.spacecleaner.generation;

import android.graphics.Point;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.IDrawable;
import com.example.my_framework.ObjectFW;
import com.example.spacecleaner.objects.Asteroid;
import com.example.spacecleaner.objects.Star;
import java.util.ArrayList;

public class Background implements IDrawable
{
    final public ArrayList<Star> stars = new ArrayList<>();
    final public ArrayList<Asteroid> asteroids = new ArrayList<>();

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

    @Override
    public void update()
    {
        for (Star star: stars)
             star.update();

        for (Asteroid asteroid: asteroids)
            asteroid.update();
    }

    @Override
    public void drawing(GraphicsFW graphicsFW)
    {
        for (Star star: stars)
            star.drawing(graphicsFW);

        for (Asteroid asteroid: asteroids)
            asteroid.drawing(graphicsFW);
    }
}
