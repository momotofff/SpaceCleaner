package com.example.my_framework;

import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.ArrayList;

public class AnimationFW
{
    double speed;
    int frameIndex;
    ArrayList<Bitmap> sprite;

    public AnimationFW(double speed, ArrayList<Bitmap> sprite)
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
