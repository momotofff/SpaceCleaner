package com.example.spacecleaner.generation;

import android.graphics.Color;
import android.graphics.Point;
import com.example.my_framework.GraphicsFW;
import com.example.spacecleaner.objects.Star;
import java.util.ArrayList;

public class Background
{
    public ArrayList<Star> stars = new ArrayList<>();

    public Background(Point displaySize)
    {
        final int STARS_COUNT = 50;

        for (int i = 0; i < STARS_COUNT; ++i)
            stars.add(new Star(displaySize));
    }

    public void update()
    {
        for (Star star: stars)
             star.update();
    }

    public void drawing(GraphicsFW graphicsFW)
    {
        for (Star star: stars)
            star.draw(graphicsFW);
    }
}
