package com.example.spacecleaner.objects;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.IDrawable;
import com.example.my_framework.ObjectFW;

public class Star extends ObjectFW implements IDrawable
{
    public Star(Point sceneSize)
    {
        this.screen = new Rect(0, 0, sceneSize.x, sceneSize.y);
        this.speed = 4;
        this.position = new Point((int) (Math.random() * screen.right), (int) (Math.random() * screen.bottom));
    }

    public Point getPosition()
    {
        return position;
    }

    @Override
    public void update()
    {
        position.x -= speed;

        if (position.x < 0)
        {
            position.x = screen.right;
            position.y = (int) (Math.random() * screen.bottom);
        }
    }

    @Override
    public void drawing(GraphicsFW graphicsFW)
    {
        graphicsFW.drawPixel(getPosition(), Color.WHITE);
    }
}
