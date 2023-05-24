package com.example.my_framework;

import android.graphics.Point;

public abstract class SceneFW
{
    public CoreFW coreFW;
    public Point sceneSize;
    public GraphicsFW graphicsFW;

    public SceneFW(CoreFW coreFW)
    {
        this.coreFW = coreFW;
        sceneSize = new Point(coreFW.getGraphicsFW().getWidthFrameBuffer(), coreFW.getGraphicsFW().getHeightFrameBuffer());
        graphicsFW = coreFW.getGraphicsFW();
    }

    public abstract void update();
    public abstract void drawing();
    public abstract void pause();
    public abstract void resume();
    public abstract void dispose();
}
