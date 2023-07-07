package com.example.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;
import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.my_framework.StaticTextFW;
import com.example.spacecleaner.R;
import com.example.spacecleaner.classes.Manager;

public class GameScene extends SceneFW
{
    GameState gameState;
    Manager manager;

    private final StaticTextFW Ready = new StaticTextFW(coreFW.getString(R.string.txtGameSceneReady),new Point(300,200),Color.WHITE, 100, null);
    private final StaticTextFW GameOver = new StaticTextFW(coreFW.getString(R.string.txtGameOver),new Point(300,200),Color.WHITE, 100, null);
    private final StaticTextFW Restart = new StaticTextFW(coreFW.getString(R.string.txtRestart),new Point(300,350),Color.WHITE, 50, null);
    private final StaticTextFW ExitMenu = new StaticTextFW(coreFW.getString(R.string.txtExitMenu),new Point(300,450),Color.WHITE, 50, null);
    private final StaticTextFW txtResult = new StaticTextFW(coreFW.getString(R.string.txtResult),new Point(300,550),Color.WHITE, 50, null);
    private StaticTextFW Result;

    enum GameState
    {
        READY, RUNNING, PAUSE, GAMEOVER
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
        if (gameState == GameState.READY)
            updateStateReady();

        else if (gameState == GameState.PAUSE)
            updateStatePause();

        else if (gameState == GameState.RUNNING)
            updateStateRunning();

        else if (gameState == GameState.GAMEOVER)
            updateStateGameOver();

    }

    private void updateStateGameOver()
    {
        if (coreFW.getTouchListenerFW().getTouchUp(Restart.getTouchArea(graphicsFW)))
            coreFW.setScene(new GameScene(coreFW));

        if (coreFW.getTouchListenerFW().getTouchUp(ExitMenu.getTouchArea(graphicsFW)))
            coreFW.setScene(new MainMenuScene(coreFW));
    }

    private void updateStateRunning()
    {
        gameState = GameState.RUNNING;
        manager.update();

        if (Manager.gameOver)
            gameState = GameState.GAMEOVER;
    }

    private void updateStatePause() {
    }

    private void updateStateReady()
    {
        if (coreFW.getTouchListenerFW().getTouchUp(Ready.getTouchArea(graphicsFW)))
        {
            gameState = GameState.RUNNING;
        }
    }

    @Override
    public void drawing()
    {

        if (gameState == GameState.READY)
            drawingStateReady();

        else if (gameState == GameState.PAUSE)
            drawingStatePause();

        else if (gameState == GameState.RUNNING)
            drawingStateRunning();

        else if (gameState == GameState.GAMEOVER)
            drawingStateGameOver();

    }

    private void drawingStateGameOver()
    {
        Result = new StaticTextFW(manager.player.getPassedDistance() + "", new Point(600,550), Color.WHITE, 50, null);
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(GameOver);
        graphicsFW.drawText(ExitMenu);
        graphicsFW.drawText(Restart);
        graphicsFW.drawText(txtResult) ;
        graphicsFW.drawText(Result) ;

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
