package com.example.spacecleaner.generation;

import android.graphics.Point;

import com.example.my_framework.GraphicsFW;
import com.example.my_framework.IDrawable;
import com.example.spacecleaner.objects.Star;
import com.example.spacecleaner.utilities.Resource;

import java.util.ArrayList;

public class Background implements IDrawable
{
    final public ArrayList<Star> stars = new ArrayList<>();
    final public ArrayList<Star> bigStars1 = new ArrayList<>();
    final public ArrayList<Star> bigStars2 = new ArrayList<>();
    final public ArrayList<Star> garbage1 = new ArrayList<>();
    final public ArrayList<Star> garbage2 = new ArrayList<>();

    public Background(Point displaySize, int height)
    {

        final int STARS_COUNT = 100;
        final int BIG_STARS_COUNT = 10;
        final int GARBAGE_COUNT = 20;

        for (int i = 0; i < STARS_COUNT; ++i)
            stars.add(new Star(displaySize, height));

        for (int i = 0; i < BIG_STARS_COUNT; ++i)
        {
            Star star = new Star(displaySize, height);
            Star star2 = new Star(displaySize, height);
            bigStars1.add(star);
            bigStars2.add(star2);
        }

        for (int i = 0; i < GARBAGE_COUNT; ++i)
        {
            Star star = new Star(displaySize, height);
            Star star2 = new Star(displaySize, height);
            garbage1.add(star);
            garbage2.add(star2);
        }

    }

    @Override
    public void update()
    {
        for (Star star: stars)
             star.update();

        for (Star star: bigStars1)
            star.update();

        for (Star star: bigStars2)
            star.update();

        for (Star star: garbage1)
            star.update();

        for (Star star: garbage2)
            star.update();
    }

    @Override
    public void drawing(GraphicsFW graphicsFW)
    {
        graphicsFW.drawTexture(Resource.starSky, new Point(0, 0));

        for (Star star: stars)
            star.drawing(graphicsFW);

        for (Star star: bigStars1)
            graphicsFW.drawTexture(Resource.bigStarSprite.get(0), star.position);

        for (Star star: bigStars2)
            graphicsFW.drawTexture(Resource.bigStarSprite.get(1), star.position);

        for (Star star: garbage1)
            graphicsFW.drawTexture(Resource.bigStarSprite.get(2), star.position);

        for (Star star: garbage2)
            graphicsFW.drawTexture(Resource.bigStarSprite.get(3), star.position);
    }
}
