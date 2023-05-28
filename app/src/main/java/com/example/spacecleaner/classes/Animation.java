package com.example.spacecleaner.classes;

import android.graphics.Bitmap;
import android.graphics.Point;

import com.example.my_framework.GraphicsFW;

import java.util.ArrayList;

public class Animation
{
    double speed;
    int frameIndex;
    ArrayList<Bitmap> sprite;

    public Animation(double speed, ArrayList<Bitmap> sprite)
    {
        this.sprite = sprite;
    }

    public void runAnimation()
    {
        if (++frameIndex >= sprite.size())
            frameIndex = 0;
    }

    public void drawingAnimation(GraphicsFW graphicsFW, Point position)
    {
        graphicsFW.drawTexture(sprite.get(frameIndex), position);
    }
}
