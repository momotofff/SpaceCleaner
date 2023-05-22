package com.example.my_framework;

import android.graphics.Point;
import android.graphics.Typeface;

public class StaticText
{
    public String text;
    public Point position;
    public int color;
    public int size;
    public Typeface font;

    public StaticText(String text, Point position, int color, int size, Typeface font)
    {
        this.text = text;
        this.position = position;
        this.color = color;
        this.size = size;
        this.font = font;
    }
}
