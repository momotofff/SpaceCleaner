package com.example.my_framework;

import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.ArrayList;

public class AnimationFW
{
    int frameIndex;
    ArrayList<Bitmap> sprite;
    Thread thread  = new Thread();

    public AnimationFW(ArrayList<Bitmap> sprite)
    {
        this.sprite = sprite;
    }

    public void runAnimation()
    {
        if (++frameIndex >= sprite.size())
        {
            frameIndex = 0;
        }
    }

    public void drawingAnimation(GraphicsFW graphicsFW, Point position)
    {
       graphicsFW.drawTexture(sprite.get(frameIndex), position);
    }
}
