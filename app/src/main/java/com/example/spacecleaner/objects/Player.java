package com.example.spacecleaner.objects;

import android.graphics.Point;
import android.graphics.Rect;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.ObjectFW;
import com.example.spacecleaner.utilits.Resource;
import com.example.my_framework.AnimationFW;

public class Player extends ObjectFW
{
    final int GRAVITY = 0;
    final int MAX_SPEED = 15;
    final int MIN_SPEED = -3;
    AnimationFW spritePlayer;
    AnimationFW spritePlayerUp;
    AnimationFW spritePlayerDown;
    CoreFW coreFW;

    boolean isUp = false;;
    boolean isDown = false;

    public Player(CoreFW coreFW, Point maxScreen, int minScreenY)
    {
        this.coreFW = coreFW;
        position.x = 32;
        position.y = maxScreen.y / 2;
        speed = 2;

        this.screen.right = maxScreen.x;
        this.screen.bottom = maxScreen.y - Resource.playerSprite.get(0).getHeight();
        this.spritePlayer = new AnimationFW(1, Resource.playerSprite);
        this.spritePlayerUp = new AnimationFW(1, Resource.playerSpriteUp);
        this.spritePlayerDown = new AnimationFW(1, Resource.playerSpriteDown);
    }

    public void update()
    {
        if (coreFW.getTouchListenerFW().getTouchDown(screen))
            startPlayerUp();
        if (coreFW.getTouchListenerFW().getTouchUp(screen))
            stopPlayerUp();

        if (isUp)
            speed += 5;
        else
            speed -= 2;

        if (speed > MAX_SPEED)
            speed = MAX_SPEED;

        if (speed < MIN_SPEED)
            speed = MIN_SPEED;

        position.y -= speed + GRAVITY;

        if (position.y < screen.top)
            position.y = screen.top;

        if (position.y > screen.bottom)
            position.y = screen.bottom;

        if (isUp)
            spritePlayerUp.runAnimation();
        else
            spritePlayer.runAnimation();
    }

    private void stopPlayerUp() {isUp = false;}

    private void startPlayerUp() { isUp = true;}

    public void drawing(GraphicsFW graphicsFW)
    {
        if (isUp)
        {
            spritePlayerUp.drawingAnimation(graphicsFW, position);
        }
        else
            spritePlayer.drawingAnimation(graphicsFW, position);
    }
}
