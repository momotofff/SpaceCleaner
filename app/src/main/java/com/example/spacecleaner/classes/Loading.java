package com.example.spacecleaner.classes;

import android.graphics.Rect;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.spacecleaner.utilits.Resource;

public class Loading
{
    public Loading(CoreFW coreFW, GraphicsFW graphicsFW)
    {
        loadTexture(graphicsFW);
        loadPlayerSprite(graphicsFW);
    }

    private void loadPlayerSprite(GraphicsFW graphicsFW)
    {
        Rect sprite = new Rect(0,0,128,64);
        final int FramesCount = 4;

        for (int i = 0; i < FramesCount; ++i)
        {
            Resource.playerSprite.add(graphicsFW.newSprite(Resource.textureAtlas, sprite));
            sprite.left += 128;
        }

        sprite.top = 64;
        sprite.left = 0;
        for (int i = 0; i < FramesCount; ++i)
        {
            Resource.playerSpriteUp.add(graphicsFW.newSprite(Resource.textureAtlas, sprite));
            sprite.left += 128;
        }

        sprite.top = 128;
        sprite.left = 0;

        for (int i = 0; i < FramesCount; ++i)
        {
            Resource.playerSpriteDown.add(graphicsFW.newSprite(Resource.textureAtlas, sprite));
            sprite.left += 128;
        }
    }

    private void loadTexture(GraphicsFW graphicsFW)
    {
        Resource.textureAtlas = graphicsFW.newTexture("textureAtlas.png");
    }
}
