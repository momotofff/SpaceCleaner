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
    AnimationFW spritePlayer;
    AnimationFW spritePlayerUp;
    AnimationFW spritePlayerDamage;
    AnimationFW spritePlayerUpActionShield;
    AnimationFW spritePlayerDestruction;
    AnimationFW spritePlayerShield;
    CoreFW coreFW;

    private int passedDistance;
    public int speed;
    private boolean hitAsteroid = false;
    private boolean isGameOver = false;
    public boolean isSpeed = false;
    public boolean isShield = false;
    private boolean isUp = false;


    TimerDelay shieldEnabled = new TimerDelay();
    public TimerDelay speedDelay = new TimerDelay();
    public TimerDelay shieldDelay = new TimerDelay();

    public Player(CoreFW coreFW, Point maxScreen, int height)
    {
        radius = Resource.playerSprite.get(0).getHeight() / 2;

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
        this.spritePlayerUpActionShield = new AnimationFW(Resource.playerSpriteUpDamage);
        this.spritePlayerDamage = new AnimationFW(Resource.playerSpriteDamage);
        this.spritePlayerDestruction = new AnimationFW(Resource.playerSpriteDestruction);
        this.spritePlayerShield = new AnimationFW(Resource.playerSpriteShield);
    }

    @Override
    public void update()
    {
        if (coreFW.getTouchListenerFW().getTouchDown(screen))
            start();

        if (coreFW.getTouchListenerFW().getTouchUp(screen))
            stop();

        if (speedDelay.isElapsed(3))
        {
            isSpeed = false;
            speed -= 10;
            speedDelay.stop();
        }

        if (shieldDelay.isElapsed(3))
        {
            isShield = false;
            hitAsteroid = true;
        }

        if (isUp)
            position.y -= speed;
        else
            position.y += speed;

        if (position.y < screen.top)
            position.y = screen.top;

        if (position.y > screen.bottom)
            position.y = screen.bottom;

        if (isShield)
        {
            spritePlayerShield.runAnimation();
            hitAsteroid = false;
        }

        if (!hitAsteroid)
        {
            if (isUp)
                spritePlayerUpActionShield.runAnimation();
            else
                spritePlayerDamage.runAnimation();

        }
        else
        {
            if (isUp)
                spritePlayerUp.runAnimation();
            else
                spritePlayer.runAnimation();
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

    @Override
    public void drawing(GraphicsFW graphicsFW)
    {
        if (isGameOver)
        {
            spritePlayerDestruction.drawingAnimation(graphicsFW, position);
            return;
        }

        if (isShield)
        {
            spritePlayerShield.drawingAnimation(graphicsFW, position);
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
                spritePlayerDamage.drawingAnimation(graphicsFW, position);
        }
    }

    public void hitAsteroid()
    {
        if (!isShield)
        {
            --speed;
            --shields;
        }

        if (!isAlive())
        {
            isGameOver = true;
            coreFW.getSoundFW().start("destroy");
        }
        else
        {
            coreFW.getSoundFW().start("damage");
        }

        hitAsteroid = true;
        shieldEnabled.start();
    }

    public void hitBonusShield() {
    }

    public void hitBonusSpeed() {
    }

    public String getShields() { return String.format(Locale.getDefault(), "%s: %d", coreFW.getString(R.string.txtHudCurrentShieldsPlayer), shields); }
    public String getTxtPassedDistance() { return String.format(Locale.getDefault(), "%s: %d", coreFW.getString(R.string.txtHudPassedDistance), passedDistance); }
    public String getSpeed() { return String.format(Locale.getDefault(), "%s: %d", coreFW.getString(R.string.txtHudCurrentSpeedPlayer), speed); }
    public String getLevel() { return String.format(Locale.getDefault(), "%s: %d", coreFW.getString(R.string.txtLevel), level); }
    public int getPassedDistance()
    {
        return passedDistance;
    }
    public boolean isAlive() { return shields >= 0; }
    private void stop() { isUp = false; }
    private void start() { isUp = true; }


}
