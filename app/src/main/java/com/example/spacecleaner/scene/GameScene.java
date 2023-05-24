package com.example.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;
import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.my_framework.StaticTextFW;
import com.example.spacecleaner.R;
import com.example.spacecleaner.generation.Background;

public class GameScene extends SceneFW
{
    GameState gameState;
    Background background;

    private final StaticTextFW SceneReady = new StaticTextFW(coreFW.getString(R.string.txtGameSceneReady),new Point(250,300),Color.WHITE, 60, null);
    private final StaticTextFW SceneGame = new StaticTextFW(coreFW.getString(R.string.txtGameScene),new Point(250,300),Color.WHITE, 60, null);

    enum GameState
    {
        READY, RUNNING, PAUSE, GAMEOVER
    }

    public GameScene(CoreFW coreFW)
    {
        super(coreFW);
        gameState = GameState.READY;
        background = new Background(sceneSize);
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

    private void updateStateGameOver() {
    }

    private void updateStateRunning()
    {
        gameState = GameState.RUNNING;
        background.update();
    }

    private void updateStatePause() {
    }

    private void updateStateReady()
    {

        if (coreFW.getTouchListenerFW().getTouchUp(SceneReady.getTouchArea(graphicsFW)))
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

    private void drawingStateGameOver() {
    }

    private void drawingStateRunning()
    {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(SceneGame);
        background.drawing(graphicsFW);
    }

    private void drawingStateReady()
    {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(SceneReady);
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
