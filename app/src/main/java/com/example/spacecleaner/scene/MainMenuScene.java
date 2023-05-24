package com.example.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;

import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.my_framework.StaticTextFW;
import com.example.spacecleaner.R;

public class MainMenuScene extends SceneFW
{
    private final StaticTextFW Title = new StaticTextFW(coreFW.getString(R.string.txtMainMenuNameGame), new Point(100, 100), Color.BLUE, 60, null);
    private final StaticTextFW MenuStart = new StaticTextFW(coreFW.getString(R.string.txtMainMenuStartGame), new Point(30, 300), Color.BLUE, 40, null);
    private final StaticTextFW MenuSettings = new StaticTextFW(coreFW.getString(R.string.txtMainMenuMenuSettings), new Point(30, 350), Color.BLUE, 40, null);
    private final StaticTextFW MenuResults = new StaticTextFW(coreFW.getString(R.string.txtMainMenuResult), new Point(30, 400), Color.BLUE, 40, null);
    private final StaticTextFW MenuExit = new StaticTextFW(coreFW.getString(R.string.txtMainMenuExitGame), new Point(30, 450), Color.BLUE, 40, null);

    public MainMenuScene(CoreFW coreFW) {
        super(coreFW);
    }

    @Override
    public void update()
    {
        if (coreFW.getTouchListenerFW().getTouchUp(MenuStart.getTouchArea(graphicsFW)))
        {
            coreFW.setScene(new GameScene(coreFW));
        }
    }

    @Override
    public void drawing()
    {
        graphicsFW.clearScene(Color.GREEN);
        graphicsFW.drawText(Title);
        graphicsFW.drawText(MenuStart);
        graphicsFW.drawText(MenuSettings);
        graphicsFW.drawText(MenuResults);
        graphicsFW.drawText(MenuExit);
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
