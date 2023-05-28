package com.example.my_framework;

import android.graphics.Point;
import android.graphics.Rect;

public abstract class ObjectFW
{
    protected Rect screen = new Rect();
    protected Point position = new Point();
    protected int speed;
    protected Rect hitBox;
    protected int radius;

    public Point getPosition() {
        return position;
    }
}
