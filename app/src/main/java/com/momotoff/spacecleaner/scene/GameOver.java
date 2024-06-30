package com.momotoff.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;

import com.momotoff.my_framework.CoreFW;
import com.momotoff.my_framework.SceneFW;
import com.momotoff.my_framework.StaticTextFW;
import com.momotoff.spacecleaner.Main;
import com.momotoff.spacecleaner.R;
import com.momotoff.spacecleaner.classes.Manager;
import com.momotoff.spacecleaner.utilities.Save;

public class GameOver extends SceneFW
{
    private final StaticTextFW GameOver = new StaticTextFW(coreFW.getString(R.string.txtGameOver), new Point(300,200), Color.WHITE, 100);
    private final StaticTextFW Restart = new StaticTextFW(coreFW.getString(R.string.txtRestart), new Point(300,350), Color.WHITE, 50);
    private final StaticTextFW ExitMenu = new StaticTextFW(coreFW.getString(R.string.txtExitMenu), new Point(300,450), Color.WHITE, 50);

    private Manager manager;
    private Save save;

    public GameOver(CoreFW coreFW, Manager manager, Save save)
    {
        super(coreFW);
        this.manager = manager;
        this.save = save;

        coreFW.getBackgroundAudioFW().setTrack(com.momotoff.my_framework.R.raw.game_over);
        coreFW.getBackgroundAudioFW().start();
    }

    @Override
    public void update()
    {
        if (coreFW.getTouchListenerFW().getTouchUp(Restart.getTouchArea(graphicsFW)))
        {
            coreFW.getSoundFW().start(R.raw.tap);
            coreFW.getBackgroundAudioFW().stop();
            coreFW.setScene(new GameScene(coreFW, save));

        }

        if (coreFW.getTouchListenerFW().getTouchUp(ExitMenu.getTouchArea(graphicsFW)))
        {
            coreFW.getSoundFW().start(R.raw.tap);
            coreFW.getBackgroundAudioFW().stop();
            coreFW.setScene(MainMenu.getInstance());
        }
    }

    @Override
    public void drawing()
    {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(GameOver);
        graphicsFW.drawText(ExitMenu);
        graphicsFW.drawText(Restart);

        StaticTextFW result = new StaticTextFW(manager.player.getTxtPassedDistance(), new Point(300,550), Color.WHITE, 50);
        graphicsFW.drawText(result);
    }
}
