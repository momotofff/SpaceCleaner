package com.example.spacecleaner.classes;

import android.graphics.Bitmap;
import android.graphics.Point;

import com.example.my_framework.GraphicsFW;

import java.util.ArrayList;

public class Animation
{
    double speed;
    int delayIndex;
    int countFrames;
    int frames;
    Bitmap sprite;
    ArrayList<Bitmap> spritePlayer;

    public Animation(double speed, ArrayList<Bitmap> spritePlayer)
    {
        this.sprite = spritePlayer.get(0);
        this.spritePlayer = spritePlayer;
        this.speed = speed;
        frames = spritePlayer.size();
    }

    public void runAnimation()
    {
        ++delayIndex;

        if (countFrames == 0)
            sprite = spritePlayer.get(countFrames);
        else if (countFrames == 1)
            sprite = spritePlayer.get(countFrames);
        else if (countFrames == 2)
            sprite = spritePlayer.get(countFrames);
        else if (countFrames == 3)
            sprite = spritePlayer.get(countFrames);

        ++countFrames;

        if (countFrames > frames)
            countFrames = 0;
    }

    public void drawingAnimation(GraphicsFW graphicsFW, Point position)
    {
        graphicsFW.drawTexture(sprite, position);
    }
}
