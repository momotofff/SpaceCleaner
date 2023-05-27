package com.example.spacecleaner.classes;

import android.graphics.Point;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.utilits.Resource;

import java.util.ArrayList;

public class Loading
{
    public Loading(CoreFW coreFW, GraphicsFW graphicsFW)
    {
        loadTexture(graphicsFW);
        loadSpritePlayer(graphicsFW);
    }

    private void loadSpritePlayer(GraphicsFW graphicsFW)
    {
        //Point oldPosition = new Point(1,1);
        //Point oldSpriteSize = new Point(128,64);
        //int countFrames = 4;
        Resource.spritePlayer.add(graphicsFW.newSprite(Resource.textureAtlas, 0,0, 128, 64));
        Resource.spritePlayer.add(graphicsFW.newSprite(Resource.textureAtlas, 128,0, 128, 64));
        Resource.spritePlayer.add(graphicsFW.newSprite(Resource.textureAtlas, 256,0, 128, 64));
        Resource.spritePlayer.add(graphicsFW.newSprite(Resource.textureAtlas, 384,0, 128, 64));

        /*for(int i = 0; i < countFrames; ++i)
        {
            Resource.spritePlayer.add(graphicsFW.newSprite(Resource.textureAtlas,oldPosition, oldSpriteSize));
            oldPosition.x = oldSpriteSize.x + 1;
            oldSpriteSize.x = oldSpriteSize.x + 128;
        }*/


    }

    private void loadTexture(GraphicsFW graphicsFW)
    {
        Resource.textureAtlas = graphicsFW.newTexture("qwerty.png");
    }
}
