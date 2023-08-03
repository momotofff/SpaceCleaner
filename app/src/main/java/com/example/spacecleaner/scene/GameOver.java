package com.example.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;

import com.example.my_framework.AudioFW;
import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.my_framework.StaticTextFW;
import com.example.spacecleaner.R;
import com.example.spacecleaner.classes.Manager;
import com.example.spacecleaner.utilities.Save;

public class GameOver extends SceneFW
{
    private final StaticTextFW GameOver = new StaticTextFW(coreFW.getString(R.string.txtGameOver), new Point(300,200), Color.WHITE, 100, null);
    private final StaticTextFW Restart = new StaticTextFW(coreFW.getString(R.string.txtRestart), new Point(300,350), Color.WHITE, 50, null);
    private final StaticTextFW ExitMenu = new StaticTextFW(coreFW.getString(R.string.txtExitMenu), new Point(300,450), Color.WHITE, 50, null);

    Manager manager;
    Save save;
    AudioFW audioFW;

    public GameOver(CoreFW coreFW, Manager manager, Save save)
    {
        super(coreFW);
        this.manager = manager;
        this.save = save;
        audioFW = new AudioFW(coreFW, com.example.my_framework.R.raw.game_over);
    }

    @Override
    public void update()
    {
        if (coreFW.getTouchListenerFW().getTouchUp(Restart.getTouchArea(graphicsFW)))
        {
            coreFW.setScene(new GameScene(coreFW, save));
            audioFW.mediaPlayer.release();
        }

        if (coreFW.getTouchListenerFW().getTouchUp(ExitMenu.getTouchArea(graphicsFW)))

        {
            coreFW.setScene(new MainMenu(coreFW, save));
            audioFW.mediaPlayer.release();
        }
    }

    @Override
    public void drawing()
    {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(GameOver);
        graphicsFW.drawText(ExitMenu);
        graphicsFW.drawText(Restart);

        StaticTextFW result = new StaticTextFW(manager.player.getTxtPassedDistance(), new Point(300,550), Color.WHITE, 50, null);
        graphicsFW.drawText(result);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {}
}
