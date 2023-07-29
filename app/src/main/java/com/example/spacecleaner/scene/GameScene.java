package com.example.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;
import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.my_framework.StaticTextFW;
import com.example.spacecleaner.R;
import com.example.spacecleaner.classes.Manager;
import com.example.spacecleaner.utilities.Save;

public class GameScene extends SceneFW
{
    GameState gameState;
    Manager manager;
    private Save save;

    private final StaticTextFW Ready = new StaticTextFW(coreFW.getString(R.string.txtGameSceneReady), new Point(300,200), Color.WHITE, 100, null);

    enum GameState
    {
        READY, RUNNING, PAUSE, GAME_OVER
    }

    public GameScene(CoreFW coreFW, Save save)
    {
        super(coreFW);

        this.save = save;
        gameState = GameState.READY;
        manager = new Manager(coreFW, sceneSize);
    }

    @Override
    public void update()
    {
        switch(gameState)
        {
            case READY:       updateStateReady(); break;
            case PAUSE:       updateStatePause(); break;
            case RUNNING:     updateStateRunning(); break;
            case GAME_OVER:   coreFW.setScene(new GameOver(coreFW, manager, save));
        }
    }

    private void updateStateRunning()
    {
        gameState = GameState.RUNNING;
        manager.update();

        if (manager.gameOver)
        {
            save.addDistance(manager.player.getPassedDistance());
            gameState = GameState.GAME_OVER;
        }
    }

    private void updateStatePause() {
    }

    private void updateStateReady()
    {
        if (coreFW.getTouchListenerFW().getTouchUp(Ready.getTouchArea(graphicsFW)))
            gameState = GameState.RUNNING;
    }

    @Override
    public void drawing()
    {
        switch(gameState)
        {
            case READY:       drawingStateReady(); break;
            case PAUSE:       drawingStatePause(); break;
            case RUNNING:     drawingStateRunning(); break;
        }
    }

    private void drawingStateRunning()
    {
        graphicsFW.clearScene(Color.BLACK);
        manager.drawing(coreFW, graphicsFW);
    }

    private void drawingStateReady()
    {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(Ready);
    }

    private void drawingStatePause() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}
}
