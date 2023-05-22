package com.example.spacecleaner.scene;

import android.graphics.Color;

import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;

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
        graphicsFW.drawText("GAME_SCENE", 100, 150, Color.GREEN, 60, null);

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
