package com.example.spacecleaner.objects;

import android.graphics.Point;
import android.graphics.Rect;

import com.example.my_framework.AnimationFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.IDrawable;
import com.example.my_framework.ObjectFW;
import com.example.spacecleaner.utilities.Resource;

public class BonusShield extends ObjectFW implements IDrawable
{
    AnimationFW BonusShieldAnim;

    public BonusShield(Point sceneSize, int height)
    {
        this.screen = new Rect(0, height, sceneSize.x, sceneSize.y);
        this.BonusShieldAnim = new AnimationFW(Resource.bonusShieldSprite);

        Point position = new Point((int) (screen.right * Math.random()), (int) (Math.random() * screen.bottom) + Resource.bonusShieldSprite.get(0).getHeight() + height);
        updatePosition(position, Resource.bonusShieldSprite.get(0));

        radius = Resource.bonusShieldSprite.get(0).getHeight() / 2;
    }

    public void restartFromInitialPosition()
    {
        position.x = screen.right;
        position.y = (int) (screen.bottom * Math.random()) + Resource.bonusShieldSprite.get(0).getHeight() * 2;
    }

    @Override
    public void update()
    {
        position.x -= speed;

        if (position.x + Resource.bonusShieldSprite.get(0).getWidth() < 0)
        {
            position.x = screen.right;
            position.y = (int) (Math.random() * screen.bottom) + screen.top;
        }

        updatePosition(position, Resource.bonusShieldSprite.get(0));
        BonusShieldAnim.runAnimation();
    }

    @Override
    public void drawing(GraphicsFW graphicsFW)
    {
        BonusShieldAnim.drawingAnimation(graphicsFW, getPosition());
    }

    public Rect getHitBox()
    {
        return hitBox;
    }
}