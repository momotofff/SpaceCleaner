package com.example.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;

import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.my_framework.StaticTextFW;
import com.example.spacecleaner.R;
import com.example.spacecleaner.classes.Manager;

public class GameOver extends SceneFW
{
    private final StaticTextFW GameOver = new StaticTextFW(coreFW.getString(R.string.txtGameOver), new Point(300,200), Color.WHITE, 100, null);
    private final StaticTextFW Restart = new StaticTextFW(coreFW.getString(R.string.txtRestart), new Point(300,350), Color.WHITE, 50, null);
    private final StaticTextFW ExitMenu = new StaticTextFW(coreFW.getString(R.string.txtExitMenu), new Point(300,450), Color.WHITE, 50, null);
    Manager manager;

    public GameOver(CoreFW coreFW, Manager manager)
    {
        super(coreFW);
        this.manager = manager;
    }

    @Override
    public void update()
    {
        if (coreFW.getTouchListenerFW().getTouchUp(Restart.getTouchArea(graphicsFW)))
            coreFW.setScene(new GameScene(coreFW));

        if (coreFW.getTouchListenerFW().getTouchUp(ExitMenu.getTouchArea(graphicsFW)))
            coreFW.setScene(new MainMenu(coreFW));
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
    public void dispose() {

    }
}
