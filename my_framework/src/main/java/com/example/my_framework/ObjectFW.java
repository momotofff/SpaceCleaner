package com.example.my_framework;


import android.graphics.Point;
import android.graphics.Rect;

public abstract class ObjectFW
{
    protected Point maxScreen;
    protected Point minScreen;
    protected Point position;
    protected int speed;
    protected Rect hitBox;
    protected int radius;

    public void setMaxScreen(Point maxScreen) {
        this.maxScreen = maxScreen;
    }

    public void setMinScreen(Point minScreen) {
        this.minScreen = minScreen;
    }

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

    public Point getMaxScreen() {
        return maxScreen;
    }

    public Point getMinScreen() {
        return minScreen;
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
