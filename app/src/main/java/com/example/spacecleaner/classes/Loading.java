package com.example.spacecleaner.classes;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.spacecleaner.R;
import com.example.spacecleaner.utilities.Resource;

import java.util.ArrayList;

public class Loading
{
    private final Point SPRITE_SIZE = new Point(128, 64);

    public Loading(CoreFW coreFW, GraphicsFW graphicsFW) {
        loadTexture(graphicsFW);
        loadSprite(graphicsFW);
        loadAsteroidSprite(graphicsFW);
        loadSounds(coreFW);
        loadSettings();
    }

    private void loadSprite(GraphicsFW graphicsFW)
    {
        readerSprite(graphicsFW, 0, Resource.playerSprite);
        readerSprite(graphicsFW, 1, Resource.playerSpriteUp);
        readerSprite(graphicsFW, 3, Resource.asteroidSprite);
        readerSprite(graphicsFW, 4, Resource.playerSpriteDestruction);
        readerSprite(graphicsFW, 5, Resource.playerSpriteDamage);
        readerSprite(graphicsFW, 6, Resource.playerSpriteUpDamage);
        readerSprite(graphicsFW, 7, Resource.playerSpriteShield);
        readerSprite(graphicsFW, 8, Resource.bonusSpeedSprite);
        readerSprite(graphicsFW, 9, Resource.bonusShieldSprite);
    }

    private void loadSounds(CoreFW coreFW)
    {
        coreFW.getSoundFW().load(R.raw.tap);
        coreFW.getSoundFW().load(R.raw.damage);
        coreFW.getSoundFW().load(R.raw.destroy);
        coreFW.getSoundFW().load(R.raw.level_up);
    }

    private void readerSprite(GraphicsFW graphicsFW, int top, ArrayList<Bitmap> sprite)
    {
        Rect rect = new Rect(0,SPRITE_SIZE.y * top, SPRITE_SIZE.x, SPRITE_SIZE.y);
        final int FRAMES_COUNT = 4;

        for (int i = 0; i < FRAMES_COUNT; ++i)
        {
            sprite.add(graphicsFW.newSprite(Resource.textureAtlas, rect));
            rect.left += SPRITE_SIZE.x;
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

    private void loadSettings()
    {

    }
}