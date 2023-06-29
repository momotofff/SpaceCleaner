package com.example.spacecleaner.objects;

import android.graphics.Point;
import android.graphics.Rect;

import com.example.my_framework.AnimationFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.IDrawable;
import com.example.my_framework.ObjectFW;
import com.example.spacecleaner.utilits.Resource;

public class Asteroid extends ObjectFW implements IDrawable
{
    AnimationFW asteroidAnim;

    public Asteroid(Point sceneSize, int height)
    {
        this.screen = new Rect(0, height, sceneSize.x, sceneSize.y);
        this.speed = 10;
        this.position = new Point((int) (screen.right * Math.random()), (int) (Math.random() * screen.bottom) + Resource.asteroidSprite.get(0).getHeight() + height);
        this.asteroidAnim = new AnimationFW(1, Resource.asteroidSprite);
    }

    @Override
    public void update()
    {
        position.x -= speed;

        if (position.x + Resource.asteroidSprite.get(0).getWidth() < 0)
        {
            position.x = screen.right;
            position.y = (int) (Math.random() * screen.bottom) + screen.top;
        }

        asteroidAnim.runAnimation();
    }

    @Override
    public void drawing(GraphicsFW graphicsFW)
    {
        asteroidAnim.drawingAnimation(graphicsFW, getPosition());
    }

}
