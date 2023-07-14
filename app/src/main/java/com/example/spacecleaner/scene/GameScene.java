package com.example.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;
import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.my_framework.StaticTextFW;
import com.example.spacecleaner.R;
import com.example.spacecleaner.classes.Manager;
import com.example.spacecleaner.classes.Saves;

public class GameScene extends SceneFW
{
    GameState gameState;
    Manager manager;

    private final StaticTextFW Ready = new StaticTextFW(coreFW.getString(R.string.txtGameSceneReady), new Point(300,200), Color.WHITE, 100, null);
    private final StaticTextFW GameOver = new StaticTextFW(coreFW.getString(R.string.txtGameOver), new Point(300,200), Color.WHITE, 100, null);
    private final StaticTextFW Restart = new StaticTextFW(coreFW.getString(R.string.txtRestart), new Point(300,350), Color.WHITE, 50, null);
    private final StaticTextFW ExitMenu = new StaticTextFW(coreFW.getString(R.string.txtExitMenu), new Point(300,450), Color.WHITE, 50, null);

    enum GameState
    {
        READY, RUNNING, PAUSE, GAME_OVER
    }

    public GameScene(CoreFW coreFW)
    {
        super(coreFW);
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
            case GAME_OVER:   updateStateGameOver(); break;
        }
    }

    private void updateStateGameOver()
    {
        Saves.addDistance(manager.player.getPassedDistance());
        if (coreFW.getTouchListenerFW().getTouchUp(Restart.getTouchArea(graphicsFW)))
            coreFW.setScene(new GameScene(coreFW));

        if (coreFW.getTouchListenerFW().getTouchUp(ExitMenu.getTouchArea(graphicsFW)))
            coreFW.setScene(new MainMenu(coreFW));
    }

    private void updateStateRunning()
    {
        gameState = GameState.RUNNING;
        manager.update();

        if (manager.gameOver)
            gameState = GameState.GAME_OVER;
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
            case GAME_OVER:   drawingStateGameOver(); break;
        }
    }

    private void drawingStateGameOver()
    {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(GameOver);
        graphicsFW.drawText(ExitMenu);
        graphicsFW.drawText(Restart);

        StaticTextFW result = new StaticTextFW(manager.player.getTxtPassedDistance(), new Point(300,550), Color.WHITE, 50, null);
        graphicsFW.drawText(result);
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

    private void drawingStatePause() {
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
