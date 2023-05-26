package com.example.spacecleaner.objects;

import android.graphics.Point;

import com.example.my_framework.GraphicsFW;
import com.example.my_framework.ObjectFW;
import com.example.my_framework.utilits.Resource;
import com.example.spacecleaner.classes.Animation;

public class Player extends ObjectFW
{
    final int GRAVITY = -3;
    final int MAX_SPEED = 15;
    final int MIN_SPEED = 1;
    Animation spritePlayer;

    public Player(Point maxScreen, int minScreenY)
    {
        position.x = 32;
        position.y = maxScreen.y / 2;
        speed = 1;
        this.maxScreen.x = maxScreen.x;
        this.maxScreen.y = maxScreen.y - Resource.spritePlayer.get(0).getHeight();
        this.spritePlayer = new Animation(speed, Resource.spritePlayer);
    }

    public void update()
    {
        position.y -= speed + GRAVITY;

        if (position.y < minScreen.y)
        {
            position.y = minScreen.y;
        }

        if (position.y < maxScreen.y)
        {
            position.y = maxScreen.y;
        }

        spritePlayer.runAnimation();

    }

    public  void drawing(GraphicsFW graphicsFW)
    {
        spritePlayer.gravingAnimation(graphicsFW, position);
    }
}
