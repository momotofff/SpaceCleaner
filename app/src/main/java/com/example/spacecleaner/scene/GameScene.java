package com.example.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;

import com.example.my_framework.AudioFW;
import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.my_framework.StaticTextFW;
import com.example.my_framework.TimerDelay;
import com.example.spacecleaner.R;
import com.example.spacecleaner.classes.Manager;
import com.example.spacecleaner.objects.Asteroid;
import com.example.spacecleaner.utilities.Save;

import java.util.stream.IntStream;

public class GameScene extends SceneFW
{
    GameState gameState;
    Manager manager;
    private Save save;
    TimerDelay powerUpDelay = new TimerDelay();

    private final StaticTextFW Ready = new StaticTextFW(coreFW.getString(R.string.txtGameSceneReady), new Point(300,200), Color.WHITE, 100, null);
    AudioFW audioFW;

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
        audioFW = new AudioFW(coreFW, com.example.my_framework.R.raw.game1);
        powerUpDelay.start();
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

        if (powerUpDelay.isElapsed(15))
        {
            ++manager.player.speed;
            ++manager.player.gravity;
            ++manager.player.shields;
            powerUpDelay.start();
            Asteroid asteroid = new Asteroid(manager.maxScreen, manager.HUD_HEIGHT);
            manager.zOrder.add(asteroid);
            manager.asteroids.add(asteroid);

            IntStream.range(0, manager.asteroids.size()).forEach(i -> ++manager.asteroids.get(i).speed);
        }
    }

    private void updateStateRunning()
    {
        gameState = GameState.RUNNING;
        manager.update();

        if (manager.gameOver)
        {
            save.addDistance(manager.player.getPassedDistance());
            save.save(coreFW.getSharedPreferences());
            gameState = GameState.GAME_OVER;
            audioFW.mediaPlayer.release();
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
    public void dispose() { audioFW.mediaPlayer.release(); }


}
