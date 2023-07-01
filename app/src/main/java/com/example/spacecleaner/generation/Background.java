package com.example.spacecleaner.generation;

import android.graphics.Point;

import com.example.my_framework.GraphicsFW;
import com.example.my_framework.IDrawable;
import com.example.spacecleaner.objects.Asteroid;
import com.example.spacecleaner.objects.Star;
import java.util.ArrayList;

public class Background implements IDrawable
{
    final public ArrayList<Star> stars = new ArrayList<>();

    public Background(Point displaySize, int height)
    {
        final int STARS_COUNT = 100;

        for (int i = 0; i < STARS_COUNT; ++i)
            stars.add(new Star(displaySize, height));
    }

    @Override
    public void update()
    {
        for (Star star: stars)
             star.update();
    }

    @Override
    public void drawing(GraphicsFW graphicsFW)
    {
        for (Star star: stars)
            star.drawing(graphicsFW);
    }

    public void hitPlayer(Asteroid asteroid)
    {
        asteroid.restartFromInitialPosition();
    }
}
