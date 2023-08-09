package com.example.spacecleaner.objects;

import android.graphics.Point;
import android.graphics.Rect;

import com.example.my_framework.AnimationFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.IDrawable;
import com.example.my_framework.ObjectFW;
import com.example.spacecleaner.utilities.Resource;

public class BonusSpeed extends ObjectFW implements IDrawable
{
    AnimationFW bonusSpeedAnim;

    public BonusSpeed(Point sceneSize, int height)
    {
        this.screen = new Rect(0, height, sceneSize.x, sceneSize.y);
        this.position = new Point((int) (screen.right * Math.random()), (int) (Math.random() * screen.bottom) + Resource.bonusSpeedSprite.get(0).getHeight() + height);
        this.bonusSpeedAnim = new AnimationFW(Resource.bonusSpeedSprite);

        radius = Resource.bonusSpeedSprite.get(0).getHeight() / 2;
        hitBox = new Rect(position.x, position.y, Resource.bonusSpeedSprite.get(0).getHeight() + position.x, Resource.bonusSpeedSprite.get(0).getWidth() + position.y);
    }

    public void restartFromInitialPosition()
    {
        position.x = screen.right;
        position.y = (int) (screen.bottom * Math.random()) + Resource.bonusSpeedSprite.get(0).getHeight() * 2;
    }

    @Override
    public void update()
    {
        position.x -= speed;

        if (position.x + Resource.bonusSpeedSprite.get(0).getWidth() < 0)
        {
            position.x = screen.right;
            position.y = (int) (Math.random() * screen.bottom) + screen.top;
        }

        bonusSpeedAnim.runAnimation();
        hitBox.top = position.x;
        hitBox.left = position.y;
        hitBox.bottom = Resource.bonusSpeedSprite.get(0).getHeight() + position.x;
        hitBox.right = Resource.bonusSpeedSprite.get(0).getWidth() + position.y;
    }

    @Override
    public void drawing(GraphicsFW graphicsFW)
    {
        bonusSpeedAnim.drawingAnimation(graphicsFW, getPosition());
    }

    public Rect getHitBox()
    {
        return hitBox;
    }
}