package com.example.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;

import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.my_framework.StaticText;

public class GameScene extends SceneFW
{
    public GameScene(CoreFW coreFW)
    {
        super(coreFW);
    }

    @Override
    public void update() {

    }

    @Override
    public void drawing()
    {
        graphicsFW.clearScene(Color.YELLOW);
        graphicsFW.drawText(new StaticText("GAME_SCENE", new Point(100, 150), Color.GREEN, 60, null));
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
