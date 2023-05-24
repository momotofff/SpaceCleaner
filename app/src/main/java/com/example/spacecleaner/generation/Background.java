package com.example.spacecleaner.generation;

import android.graphics.Color;
import android.graphics.Point;
import com.example.my_framework.GraphicsFW;
import com.example.spacecleaner.objects.Star;
import java.util.ArrayList;

public class Background
{
    public ArrayList<Star> stars = new ArrayList<Star>();

    public Background(Point sizeDisplay)
    {
        final int STARS_COUNT = 50;

        for (int i = 0; i < STARS_COUNT; ++i)
        {
            stars.add(new Star(sizeDisplay));
        }
    }

    public void update()
    {
        for (int i = 0; i < stars.size(); ++i)
        {
             stars.get(i).update();
        }
    }

    public void drawing(GraphicsFW graphicsFW)
    {
        for (int i = 0; i < stars.size(); ++i)
        {
            graphicsFW.drawPixel(stars.get(i).getPosition(), Color.WHITE);
        }
    }
}
