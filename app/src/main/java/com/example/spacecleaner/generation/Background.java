package com.example.spacecleaner.generation;

import android.graphics.Bitmap;

import android.graphics.Point;

import com.example.my_framework.GraphicsFW;
import com.example.my_framework.IDrawable;
import com.example.spacecleaner.objects.Star;
import com.example.spacecleaner.utilities.Resource;

import java.util.ArrayList;

public class Background implements IDrawable
{
    final public ArrayList<Star> stars = new ArrayList<>();
    final public ArrayList<Star> bigStars = new ArrayList<>();
    final private Bitmap starSky;

    public Background(Point displaySize, int height)
    {
        starSky = Resource.starSky;

        final int STARS_COUNT = 100;
        final int BIG_STARS_COUNT = 20;

        for (int i = 0; i < STARS_COUNT; ++i)
            stars.add(new Star(displaySize, height));

        for (int i = 0; i < BIG_STARS_COUNT; ++i)
        {
            Star star = new Star(displaySize, height);
            star.speed = star.speed - (int)(Math.random( ) * 10);
            bigStars.add(star);
        }

    }

    @Override
    public void update()
    {
        for (Star star: stars)
             star.update();

        for (Star star: bigStars)
            star.update();
    }

    @Override
    public void drawing(GraphicsFW graphicsFW)
    {
        graphicsFW.drawTexture(starSky, new Point(0, 0));

        for (Star star: stars)
            star.drawing(graphicsFW);

        for (Star star: bigStars)
            graphicsFW.drawTexture(Resource.bigStarSprite.get(0), star.position);
    }
}
