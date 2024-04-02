package com.example.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;

import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.my_framework.StaticTextFW;
import com.example.spacecleaner.R;
import com.example.spacecleaner.utilities.Save;

public class MainMenu extends SceneFW
{
    private final StaticTextFW Title = new StaticTextFW(coreFW.getString(R.string.txtMainMenuNameGame), new Point(50, 100), Color.BLUE, 100);
    private final StaticTextFW MenuStart = new StaticTextFW(coreFW.getString(R.string.txtMainMenuStartGame), new Point(50, 300), Color.BLUE, 60);
    private final StaticTextFW MenuSettings = new StaticTextFW(coreFW.getString(R.string.txtMainMenuMenuSettings), new Point(50, 400), Color.BLUE, 60);
    private final StaticTextFW MenuResults = new StaticTextFW(coreFW.getString(R.string.txtMainMenuResult), new Point(50, 500), Color.BLUE, 60);
    private final StaticTextFW MenuExit = new StaticTextFW(coreFW.getString(R.string.txtMainMenuExitGame), new Point(50, 600), Color.BLUE, 60);
    private Save save;

    public MainMenu(CoreFW coreFW, Save save)
    {
        super(coreFW);
        this.save = save;

        coreFW.getBackgroundAudioFW().setTrack(com.example.my_framework.R.raw.menu);
        coreFW.getBackgroundAudioFW().start();
    }

    @Override
    public void update()
    {
        if (coreFW.getTouchListenerFW().getTouchUp(MenuStart.getTouchArea(graphicsFW)))
        {
            coreFW.getBackgroundAudioFW().stop();
            coreFW.getSoundFW().start(R.raw.tap);
            coreFW.setScene(new GameScene(coreFW, save));

        }

        if (coreFW.getTouchListenerFW().getTouchUp(MenuResults.getTouchArea(graphicsFW)))
        {
            coreFW.getSoundFW().start(R.raw.tap);
            coreFW.setScene(new Results(coreFW, save));
        }

        if (coreFW.getTouchListenerFW().getTouchUp(MenuExit.getTouchArea(graphicsFW)))
        {
            coreFW.getSoundFW().start(R.raw.tap);
            coreFW.getBackgroundAudioFW().stop();
            coreFW.onBackPressed();
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
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}
}
