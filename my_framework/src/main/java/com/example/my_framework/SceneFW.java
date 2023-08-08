package com.example.my_framework;

import android.graphics.Point;

public abstract class SceneFW
{
    public CoreFW coreFW;
    public Point sceneSize;
    public GraphicsFW graphicsFW;

    public final String TAP = "tap";
    public final String DAMAGE = "damage";
    public final String LEVEL_UP = "level_up";
    public final String DESTROY = "destroy";

    public SceneFW(CoreFW coreFW)
    {
        this.coreFW = coreFW;
        sceneSize = new Point(coreFW.getGraphicsFW().getFrameBufferWidth(), coreFW.getGraphicsFW().getFrameBufferHeight());
        graphicsFW = coreFW.getGraphicsFW();
    }

    public abstract void update();
    public abstract void drawing();
    public abstract void pause();
    public abstract void resume();
    public abstract void dispose();
}
