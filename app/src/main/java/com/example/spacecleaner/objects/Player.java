package com.example.spacecleaner.objects;

import android.graphics.Point;
import android.graphics.Rect;

import com.example.my_framework.AnimationFW;
import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.IDrawable;
import com.example.my_framework.ObjectFW;
import com.example.spacecleaner.R;
import com.example.spacecleaner.utilits.Resource;

import java.util.Locale;

public class Player extends ObjectFW implements IDrawable
{
    final int GRAVITY = 10;
    AnimationFW spritePlayer;
    AnimationFW spritePlayerUp;
    CoreFW coreFW;

    private int shields;
    private int passedDistance;
    private int speed;

    boolean isUp = false;;

    public Player(CoreFW coreFW, Point maxScreen, int height)
    {
        radius = Resource.playerSprite.get(0).getHeight() / 2;
        shields = 3;
        this.coreFW = coreFW;
        position.x = 32;
        position.y = maxScreen.y / 2;
        speed = 10;
        hitBox = new Rect(position.x, position.y, Resource.playerSprite.get(0).getHeight() + position.x, Resource.playerSprite.get(0).getWidth() + position.y);
        this.screen.right = maxScreen.x;
        this.screen.bottom = maxScreen.y - Resource.playerSprite.get(0).getHeight();
        this.screen.top = height;
        this.spritePlayer = new AnimationFW(Resource.playerSprite);
        this.spritePlayerUp = new AnimationFW(Resource.playerSpriteUp);
    }

    @Override
    public void update()
    {
        if (coreFW.getTouchListenerFW().getTouchDown(screen))
            start();

        if (coreFW.getTouchListenerFW().getTouchUp(screen))
            stop();

        if (isUp)
            position.y -= speed;
        else
            position.y += GRAVITY;


        if (position.y < screen.top)
            position.y = screen.top;

        if (position.y > screen.bottom)
            position.y = screen.bottom;

        if (isUp)
            spritePlayerUp.runAnimation();
        else
            spritePlayer.runAnimation();

        hitBox.top = position.x;
        hitBox.left = position.y;
        hitBox.bottom = Resource.playerSprite.get(0).getHeight() + position.x;
        hitBox.right = Resource.playerSprite.get(0).getWidth() + position.y;

        passedDistance += speed;
    }

    private void stop() { isUp = false; }

    private void start() { isUp = true; }

    @Override
    public void drawing(GraphicsFW graphicsFW)
    {
        if (isUp)
            spritePlayerUp.drawingAnimation(graphicsFW, position);
        else
            spritePlayer.drawingAnimation(graphicsFW, position);
    }

    public String getShields()
    {
        return String.format(Locale.getDefault(), "%s: %d", coreFW.getString(R.string.txtHudCurrentShieldsPlayer), shields);
    }

    public String getPassedDistance()
    {
        return String.format(Locale.getDefault(), "%s: %d", coreFW.getString(R.string.txtHudPassedDistance), passedDistance);
    }

    public String getSpeed()
    {
        return String.format(Locale.getDefault(), "%s: %d", coreFW.getString(R.string.txtHudCurrentSpeedPlayer), speed);
    }

    public void hitAsteroid()
    {
        shields--;
    }
}
