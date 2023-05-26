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

    ArrayList<Bitmap> spritePlayer;

    public Animation(double speed, ArrayList<Bitmap> spritePlayer)
    {

        this.spritePlayer = spritePlayer;
        this.speed = speed;
        frames = spritePlayer.size();
    }

    public void runAnimation()
    {
        ++delayIndex;


        if (countFrames == 0)
        {
            sprite = sprite1;
        }
    }

    public void gravingAnimation(GraphicsFW graphicsFW, Point position)
    {
        for (int i = 0; i < frames; ++i)
        {
            graphicsFW.drawTexture(spritePlayer.get(i),position);
        }
    }




}
