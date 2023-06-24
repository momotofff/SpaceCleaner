package com.example.spacecleaner.objects;

import android.graphics.Color;
import android.graphics.Point;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.IDrawable;
import com.example.my_framework.StaticTextFW;
import com.example.spacecleaner.R;

public class Hud implements IDrawable
{
    private int passedDistance;
    private final StaticTextFW txtPassedDistance;
    private final StaticTextFW txtCurrentSpeedPlayer;
    private final StaticTextFW txtCurrentShieldsPlayer;
    private int currentSpeedPlayer;
    private int currentShieldsPlayer;
    CoreFW coreFW;
    private final int HEIGHT = 50;

    public Hud(CoreFW coreFW)
    {
        this.coreFW = coreFW;
        txtPassedDistance = new StaticTextFW(coreFW.getString(R.string.txtHudPassedDistance)+ " : " + passedDistance, new Point(10, 30), Color.BLUE, 25, null);
        txtCurrentSpeedPlayer = new StaticTextFW(coreFW.getString(R.string.txtHudCurrentSpeedPlayer)+ " : " + currentSpeedPlayer, new Point(350, 30), Color.BLUE, 25, null);
        txtCurrentShieldsPlayer = new StaticTextFW(coreFW.getString(R.string.txtHudCurrentShieldsPlayer)+ " : " + currentShieldsPlayer, new Point(700, 30), Color.BLUE, 25, null);
    }

    // TODO: it's properies of Player. Move them to Player class, and pass reference to player in ctor
    public void update(int passedDistance, int currentSpeedPlayer, int currentShieldsPlayer)
    {
        this.passedDistance = passedDistance;
        this.currentSpeedPlayer = currentSpeedPlayer;
        this.currentShieldsPlayer = currentShieldsPlayer;
    }

    @Override
    public void update() {}

    @Override
    public void drawing(GraphicsFW graphicsFW)
    {
        graphicsFW.drawLine(0, HEIGHT, graphicsFW.getWidthFrameBuffer(), HEIGHT, Color.WHITE);
        graphicsFW.drawText(txtPassedDistance);
        graphicsFW.drawText(txtCurrentSpeedPlayer);
        graphicsFW.drawText(txtCurrentShieldsPlayer);
    }

    public int getHeight() {
        return HEIGHT;
    }
}
