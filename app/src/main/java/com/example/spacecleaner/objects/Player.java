package com.example.spacecleaner.objects;

import android.graphics.Point;

import com.example.my_framework.GraphicsFW;
import com.example.my_framework.ObjectFW;
import com.example.my_framework.utilits.Resource;
import com.example.spacecleaner.classes.Animation;

public class Player extends ObjectFW
{
    final int GRAVITY = 0;
    final int MAX_SPEED = 15;
    final int MIN_SPEED = 1;
    Animation spritePlayer;

    public Player(Point maxScreen, int minScreenY)
    {
        position.x = 32;
        position.y = maxScreen.y / 2;
        speed = 1;

        this.screen.right = maxScreen.x;
        this.screen.bottom = maxScreen.y - Resource.playerSprite.get(0).getHeight();
        this.spritePlayer = new Animation(speed, Resource.playerSprite);
    }

    public void update()
    {
        position.y += speed + GRAVITY;

        if (position.y < screen.top)
            position.y = screen.top;

        if (position.y > screen.bottom)
            position.y = screen.bottom;

        spritePlayer.runAnimation();
    }

    public void drawing(GraphicsFW graphicsFW)
    {
        spritePlayer.drawingAnimation(graphicsFW, position);
    }
}
