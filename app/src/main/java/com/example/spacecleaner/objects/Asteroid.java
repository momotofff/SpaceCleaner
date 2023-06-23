package com.example.spacecleaner.objects;

import android.graphics.Point;
import android.graphics.Rect;

import com.example.my_framework.AnimationFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.ObjectFW;
import com.example.spacecleaner.utilits.Resource;

public class Asteroid extends ObjectFW
{
    AnimationFW asteroidAnim;

    public Asteroid(Point sceneSize)
    {
        this.screen = new Rect(0, 0, sceneSize.x, sceneSize.y);
        this.speed = 5;
        this.position = new Point((int) (screen.right * Math.random()), (int) (Math.random() * screen.bottom) - Resource.asteroidSprite.get(0).getHeight());
        this.asteroidAnim = new AnimationFW(1, Resource.asteroidSprite);
    }

    public void update()
    {
        position.x -= speed;

        if (position.x + Resource.asteroidSprite.get(0).getHeight() < 0)
        {
            position.x = screen.right;
            position.y = (int) (Math.random() * screen.bottom);
        }

        asteroidAnim.runAnimation();
    }

    public void drawing(GraphicsFW graphicsFW)
    {
        asteroidAnim.drawingAnimation(graphicsFW, getPosition());
    }

}
