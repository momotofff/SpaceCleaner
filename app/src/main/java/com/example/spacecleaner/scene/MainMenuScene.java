package com.example.spacecleaner.scene;

import android.graphics.Color;

import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.spacecleaner.R;

public class MainMenuScene extends SceneFW
{

    public MainMenuScene(CoreFW coreFW) {
        super(coreFW);
    }

    @Override
    public void update() {

    }

    @Override
    public void drawing()
    {
        graphicsFW.clearScene(Color.GREEN);
        graphicsFW.drawText(coreFW.getString(R.string.txtMainMenuNameGame), 100, 100, Color.BLUE, 60, null);
        graphicsFW.drawText(coreFW.getString(R.string.txtMainMenuNameGame), 30, 300, Color.BLUE, 40 , null);
        graphicsFW.drawText(coreFW.getString(R.string.txtMainMenuMenuSettings), 30, 350, Color.BLUE, 40 , null);
        graphicsFW.drawText(coreFW.getString(R.string.txtMainMenuResult), 30, 400, Color.BLUE, 40 , null);
        graphicsFW.drawText(coreFW.getString(R.string.txtMainMenuExitGame), 30, 450, Color.BLUE, 40 , null);
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
