package com.example.spacecleaner.objects;

import android.graphics.Point;
import com.example.my_framework.ObjectFW;

public class Star extends ObjectFW
{
    public Star(Point sceneSize)
    {
        this.maxScreen = new Point(sceneSize.x, sceneSize.y);
        this.minScreen = new Point(0, 0);
        this.speed = 4;
        this.position = new Point( (int) (Math.random() * maxScreen.x), (int) (Math.random() * maxScreen.y));
    }

    public void update()
    {
        position.x -= speed;
        if (position.x < 0)
        {
            position.x = maxScreen.x;
            position.y = (int) (Math.random() * maxScreen.y);
        }
    }

    public Point getPosition()
    {
        return position;
    }


}
