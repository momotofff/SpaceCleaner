package com.example.spacecleaner.classes;

import android.graphics.Point;
import android.graphics.Rect;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.spacecleaner.utilits.Resource;

public class Loading
{
    private final Point SPRITE_SIZE = new Point(128, 64);

    public Loading(CoreFW coreFW, GraphicsFW graphicsFW)
    {
        loadTexture(graphicsFW);
        loadPlayerSprite(graphicsFW);
        loadAsteroidSprite(graphicsFW);
    }

    private void loadPlayerSprite(GraphicsFW graphicsFW)
    {
        Rect sprite = new Rect(0,0, SPRITE_SIZE.x, SPRITE_SIZE.y);
        final int FRAMES_COUNT = 4;

        for (int i = 0; i < FRAMES_COUNT; ++i)
        {
            Resource.playerSprite.add(graphicsFW.newSprite(Resource.textureAtlas, sprite));
            sprite.left += SPRITE_SIZE.x;
        }

        sprite.top += SPRITE_SIZE.y;
        sprite.left = 0;
        for (int i = 0; i < FRAMES_COUNT; ++i)
        {
            Resource.playerSpriteUp.add(graphicsFW.newSprite(Resource.textureAtlas, sprite));
            sprite.left += SPRITE_SIZE.x;
        }
    }

    private void loadAsteroidSprite(GraphicsFW graphicsFW)
    {
        Rect sprite = new Rect(0,3 * SPRITE_SIZE.y, SPRITE_SIZE.x, SPRITE_SIZE.y);
        final int FRAMES_COUNT = 4;

        for (int i = 0; i < FRAMES_COUNT; ++i)
        {
            Resource.asteroidSprite.add(graphicsFW.newSprite(Resource.textureAtlas, sprite));
            sprite.left += SPRITE_SIZE.x;
        }
    }

    private void loadTexture(GraphicsFW graphicsFW)
    {
        Resource.textureAtlas = graphicsFW.newTexture("textureAtlas.png");
    }
}
