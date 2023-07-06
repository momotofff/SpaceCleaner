package com.example.my_framework;

import android.graphics.Point;
import android.graphics.Rect;

public abstract class ObjectFW
{
    public Rect screen = new Rect();
    public Point position = new Point();
    public int speed;
    protected Rect hitBox;
    protected int radius;

    public ObjectFW() {
        super();
    }

    public Point getPosition() {
        return position;
    }
    public Rect getHitBox() {return hitBox;}
    public int getRadius() {return radius;}
}
