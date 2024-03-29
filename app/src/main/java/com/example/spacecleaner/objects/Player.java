package com.example.spacecleaner.objects;

import android.graphics.Point;

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
    AnimationFW playerSprite;
    AnimationFW playerSpriteUp;
    AnimationFW playerSpriteDamage;
    AnimationFW playerSpriteUpDamage;
    AnimationFW playerSpriteDestruction;
    AnimationFW playerSpriteShield;
    CoreFW coreFW;

    private int passedDistance;
    public int level = 1;
    public int shields = 3;
    public int dexterity = 10;

    public boolean hitAsteroid = false;
    private boolean isGameOver = false;
    public boolean isSpeed = false;
    public boolean hitShield = false;
    private boolean isUp = false;

    public TimerDelay damageDelay = new TimerDelay();
    public TimerDelay speedDelay = new TimerDelay();
    public TimerDelay shieldDelay = new TimerDelay();

    public Player(CoreFW coreFW, Point maxScreen, int height)
    {
        radius = Resource.playerSprite.get(0).getHeight() / 2;

        updatePosition(new Point(32, maxScreen.y / 2), Resource.playerSprite.get(0));
        this.coreFW = coreFW;
        this.screen.right = maxScreen.x;
        this.screen.bottom = maxScreen.y - Resource.playerSprite.get(0).getHeight();
        this.screen.top = height;
        this.playerSprite = new AnimationFW(Resource.playerSprite);
        this.playerSpriteUp = new AnimationFW(Resource.playerSpriteUp);
        this.playerSpriteUpDamage = new AnimationFW(Resource.playerSpriteUpDamage);
        this.playerSpriteDamage = new AnimationFW(Resource.playerSpriteDamage);
        this.playerSpriteDestruction = new AnimationFW(Resource.playerSpriteDestruction);
        this.playerSpriteShield = new AnimationFW(Resource.playerSpriteShield);
    }

    @Override
    public void update()
    {
        passedDistance += speed;
        updatePosition(position, Resource.playerSprite.get(0));

        if (coreFW.getTouchListenerFW().getTouchDown(screen))
            start();

        if (coreFW.getTouchListenerFW().getTouchUp(screen))
            stop();

        if (speedDelay.isElapsed(5))
        {
            isSpeed = false;
            speed -= 10;
            dexterity -= 2;
            speedDelay.stop();
        }

        if (shieldDelay.isElapsed(3))
        {
            hitShield = false;
        }

        if (damageDelay.isElapsed(1))
            hitAsteroid = false;

        if (isUp)
            position.y -= speed;
        else
            position.y += speed;

        if (position.y < screen.top)
            position.y = screen.top;

        if (position.y > screen.bottom)
            position.y = screen.bottom;

       if (hitAsteroid)
       {
           if (isUp)
               playerSpriteUpDamage.runAnimation();
           else
               playerSpriteDamage.runAnimation();
       }

       else
       {
           if (isUp)
               playerSpriteUp.runAnimation();
           else
               playerSprite.runAnimation();
       }

        if (hitShield)
        {
            if (isUp)
                playerSpriteShield.runAnimation();
            else
                playerSpriteShield.runAnimation();
        }

        if (isGameOver)
            playerSpriteDestruction.runAnimation();
    }

    @Override
    public void drawing(GraphicsFW graphicsFW)
    {
        if (hitShield)
        {
            if (isUp)
                playerSpriteShield.drawingAnimation(graphicsFW, position);

            else
                playerSpriteShield.drawingAnimation(graphicsFW, position);
            return;
        }

        if (hitAsteroid)
        {
            if (isUp)
                playerSpriteUpDamage.drawingAnimation(graphicsFW, position);
            else
                playerSpriteDamage.drawingAnimation(graphicsFW, position);
        }

        else
        {
            if (isUp)
                playerSpriteUp.drawingAnimation(graphicsFW, position);
            else
                playerSprite.drawingAnimation(graphicsFW, position);
        }

        if (isGameOver)
        {
            playerSpriteDestruction.drawingAnimation(graphicsFW, position);
        }
    }

    public void hitObject()
    {
        if (hitShield)
        {
            hitAsteroid = false;
            coreFW.getSoundFW().start(R.raw.tap);
            return;
        }

        else
        {
            --speed;
            --shields;
            hitAsteroid = true;
            damageDelay.start();
            coreFW.getSoundFW().start(R.raw.damage);
        }

        if (isDead())
        {
            isGameOver = true;
            coreFW.getSoundFW().start(R.raw.destroy);
        }
    }

    public String getShields() { return String.format(Locale.getDefault(), "%s: %d", coreFW.getString(R.string.txtHudCurrentShieldsPlayer), shields); }
    public String getTxtPassedDistance() { return String.format(Locale.getDefault(), "%s: %d", coreFW.getString(R.string.txtHudPassedDistance), passedDistance); }
    public String getSpeed() { return String.format(Locale.getDefault(), "%s: %d", coreFW.getString(R.string.txtHudCurrentSpeedPlayer), speed); }
    public String getLevel() { return String.format(Locale.getDefault(), "%s: %d", coreFW.getString(R.string.txtLevel), level); }
    public int getPassedDistance()
    {
        return passedDistance;
    }
    public boolean isDead() { return shields < 0; }
    private void stop() { isUp = false; }
    private void start() { isUp = true; }
}
