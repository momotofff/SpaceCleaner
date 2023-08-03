package com.example.spacecleaner.objects;

import android.graphics.Point;
import android.graphics.Rect;

import com.example.my_framework.AnimationFW;
import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.IDrawable;
import com.example.my_framework.ObjectFW;
import com.example.my_framework.TimerDelay;
import com.example.spacecleaner.R;
import com.example.spacecleaner.utilities.Resource;

import java.util.Locale;

public class Player extends ObjectFW implements IDrawable
{
    public int gravity = 10;
    AnimationFW spritePlayer;
    AnimationFW spritePlayerUp;
    AnimationFW spritePlayerActionShield;
    AnimationFW spritePlayerUpActionShield;
    AnimationFW spritePlayerDestruction;
    CoreFW coreFW;

    public int shields = 3;
    private int passedDistance;
    public int speed;
    private boolean hitAsteroid = false;
    private boolean isGameOver = false;
    private boolean isUp = false;

    TimerDelay shieldEnabled = new TimerDelay();

    public Player(CoreFW coreFW, Point maxScreen, int height)
    {
        radius = Resource.playerSprite.get(0).getHeight() / 2;
        shields = 3;

        position.x = 32;
        position.y = maxScreen.y / 2;
        speed = 20;
        hitBox = new Rect(position.x, position.y, Resource.playerSprite.get(0).getHeight() + position.x, Resource.playerSprite.get(0).getWidth() + position.y);

        this.coreFW = coreFW;
        this.screen.right = maxScreen.x;
        this.screen.bottom = maxScreen.y - Resource.playerSprite.get(0).getHeight();
        this.screen.top = height;
        this.spritePlayer = new AnimationFW(Resource.playerSprite);
        this.spritePlayerUp = new AnimationFW(Resource.playerSpriteUp);
        this.spritePlayerUpActionShield = new AnimationFW(Resource.playerSpriteUpActionShield);
        this.spritePlayerActionShield = new AnimationFW(Resource.playerSpriteActionShield);
        this.spritePlayerDestruction = new AnimationFW(Resource.playerSpriteDestruction);
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
            position.y += gravity;

        if (position.y < screen.top)
            position.y = screen.top;

        if (position.y > screen.bottom)
            position.y = screen.bottom;

        if (!hitAsteroid)
        {
            if (isUp)
                spritePlayerUp.runAnimation();
            else
                spritePlayer.runAnimation();
        }
        else
        {
            if (isUp)
                spritePlayerUpActionShield.runAnimation();
            else
                spritePlayerActionShield.runAnimation();
        }

        hitBox.top = position.x;
        hitBox.left = position.y;
        hitBox.bottom = Resource.playerSprite.get(0).getHeight() + position.x;
        hitBox.right = Resource.playerSprite.get(0).getWidth() + position.y;

        passedDistance += speed;

        if (isGameOver)
            spritePlayerDestruction.runAnimation();

        if (shieldEnabled.isElapsed(1))
            hitAsteroid = false;
    }

    private void stop() { isUp = false; }

    private void start() { isUp = true; }

    @Override
    public void drawing(GraphicsFW graphicsFW)
    {
        if (isGameOver)
        {
            spritePlayerDestruction.drawingAnimation(graphicsFW, position);
            return;
        }

        if (!hitAsteroid)
        {
            if (isUp)
                spritePlayerUp.drawingAnimation(graphicsFW, position);
            else
                spritePlayer.drawingAnimation(graphicsFW, position);
        }
        else
        {
            if (isUp)
                spritePlayerUpActionShield.drawingAnimation(graphicsFW, position);
            else
                spritePlayerActionShield.drawingAnimation(graphicsFW, position);
        }
    }

    public String getShields()
    {
        return String.format(Locale.getDefault(), "%s: %d", coreFW.getString(R.string.txtHudCurrentShieldsPlayer), shields);
    }

    public String getTxtPassedDistance()
    {
        return String.format(Locale.getDefault(), "%s: %d", coreFW.getString(R.string.txtHudPassedDistance), passedDistance);
    }

    public int getPassedDistance()
    {
        return passedDistance;
    }

    public String getSpeed()
    {
        return String.format(Locale.getDefault(), "%s: %d", coreFW.getString(R.string.txtHudCurrentSpeedPlayer), speed);
    }

    public void hitAsteroid()
    {
        --speed;
        --shields;
        --gravity;

        if (!isAlive())
            isGameOver = true;

        hitAsteroid = true;
        shieldEnabled.start();
    }

    public boolean isAlive()
    {
        return shields >= 0;
    }

}
