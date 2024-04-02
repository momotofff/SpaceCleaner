package com.example.my_framework;

import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;


public class StaticTextFW
{
    public String text;
    public Point position;
    public int color;
    public int size;
    public final Typeface font = null;

    public StaticTextFW(String text, Point position, int color, int size)
    {
        this.text = text;
        this.position = position;
        this.color = color;
        this.size = size;
    }

    public Rect getTouchArea(GraphicsFW graphics)
    {
        return new Rect(position.x,position.y - size,position.x + graphics.measureText(this), position.y);
    }
}
