package com.example.spacecleaner.objects;

import android.graphics.Color;
import android.graphics.Point;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.IDrawable;
import com.example.my_framework.ObjectFW;
import com.example.my_framework.StaticTextFW;

public class Hud extends ObjectFW implements IDrawable
{
    private final StaticTextFW txtPassedDistance;
    private final StaticTextFW txtCurrentSpeedPlayer;
    private final StaticTextFW txtCurrentShieldsPlayer;

    private final CoreFW coreFW;
    private final Player player;

    private final int height;

    public Hud(CoreFW coreFW, Player player, int height)
    {
        this.coreFW = coreFW;
        this.player = player;
        this.height = height;

        txtPassedDistance = new StaticTextFW(player.getPassedDistance(), new Point(10, 70), Color.WHITE, 40, null);
        txtCurrentSpeedPlayer = new StaticTextFW(player.getSpeed(), new Point(400, 70), Color.WHITE, 40, null);
        txtCurrentShieldsPlayer = new StaticTextFW(player.getShields(), new Point(700, 70), Color.WHITE, 40, null);
    }

    @Override
    public void update()
    {
        txtPassedDistance.text = player.getPassedDistance();
        txtCurrentSpeedPlayer.text = player.getSpeed();
        txtCurrentShieldsPlayer.text = player.getShields();
    }

    @Override
    public void drawing(GraphicsFW graphicsFW)
    {
        graphicsFW.drawLine(0, height, graphicsFW.getFrameBufferWidth(), height, Color.WHITE);
        graphicsFW.drawText(txtPassedDistance);
        graphicsFW.drawText(txtCurrentSpeedPlayer);
        graphicsFW.drawText(txtCurrentShieldsPlayer);
    }
}
