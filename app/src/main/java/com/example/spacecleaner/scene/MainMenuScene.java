package com.example.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.my_framework.StaticText;
import com.example.spacecleaner.R;

public class MainMenuScene extends SceneFW
{
    private final StaticText Title = new StaticText(coreFW.getString(R.string.txtMainMenuNameGame), new Point(100, 100), Color.BLUE, 60, null);
    private final StaticText MenuSpace = new StaticText(coreFW.getString(R.string.txtMainMenuNameGame), new Point(30, 300), Color.BLUE, 40, null);
    private final StaticText MenuSettings = new StaticText(coreFW.getString(R.string.txtMainMenuMenuSettings), new Point(30, 350), Color.BLUE, 40, null);
    private final StaticText MenuResults = new StaticText(coreFW.getString(R.string.txtMainMenuResult), new Point(30, 400), Color.BLUE, 40, null);
    private final StaticText MenuExit = new StaticText(coreFW.getString(R.string.txtMainMenuExitGame), new Point(30, 450), Color.BLUE, 40, null);

    public MainMenuScene(CoreFW coreFW) {
        super(coreFW);
    }

    @Override
    public void update()
    {
        if (coreFW.getTouchListenerFW().getTouchUp(new Rect(
            MenuSpace.position.x,MenuSpace.position.y - MenuSpace.size,MenuSpace.position.x + graphicsFW.measureText(MenuSpace), MenuSpace.position.y)))
        {
            coreFW.setScene(new GameScene(coreFW));
        }
    }

    @Override
    public void drawing()
    {
        graphicsFW.clearScene(Color.GREEN);
        graphicsFW.drawText(Title);
        graphicsFW.drawText(MenuSpace);
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
