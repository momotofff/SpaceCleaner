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

    public void setPosition(Point position) {
        this.position = position;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setHitBox(Rect hitBox) {
        this.hitBox = hitBox;
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }
    public Point getPosition() {
        return position;
    }
    public int getSpeed() {
        return speed;
    }
    public Rect getHitBox() {
        return hitBox;
    }
    public int getRadius() {
        return radius;
    }
}
