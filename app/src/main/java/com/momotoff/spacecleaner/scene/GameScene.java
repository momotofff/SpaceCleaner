package com.momotoff.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;
import android.view.View;
import com.momotoff.my_framework.CoreFW;
import com.momotoff.my_framework.SceneFW;
import com.momotoff.my_framework.StaticTextFW;
import com.momotoff.my_framework.TimerDelay;
import com.momotoff.spacecleaner.R;
import com.momotoff.spacecleaner.classes.Manager;
import com.momotoff.spacecleaner.objects.Asteroid;
import com.momotoff.spacecleaner.objects.Star;
import com.momotoff.spacecleaner.utilities.Save;

public class GameScene extends SceneFW
{
    private GameState gameState;
    private Manager manager;
    private Save save;
    private final TimerDelay powerUpDelay = new TimerDelay();

    private final StaticTextFW Ready = new StaticTextFW(coreFW.getString(R.string.txtGameSceneReady), new Point(300,200), Color.WHITE, 100);

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

        coreFW.getBackgroundAudioFW().setTrack(com.momotoff.my_framework.R.raw.game1);
        coreFW.getBackgroundAudioFW().start();

        powerUpDelay.start();
    }

    @Override
    public void update()
    {
        MainMenu.getInstance().setBannerVisibility(View.GONE);
        MainMenu.getInstance().setRegistrationWindowVisibility(View.GONE);

        switch (gameState)
        {
            case READY:       updateStateReady(); break;
            case PAUSE:       updateStatePause(); break;
            case RUNNING:     updateStateRunning(); break;
            case GAME_OVER:   coreFW.setScene(new GameOver(coreFW, manager, save));
        }

        if (powerUpDelay.isElapsed(20))
        {
            coreFW.getSoundFW().start(R.raw.level_up);
            ++manager.player.level;
            ++manager.player.shields;
            ++manager.player.speed;
            ++manager.player.dexterity;


            powerUpDelay.start();

            Asteroid asteroid = new Asteroid(manager.maxScreen, manager.getHUD_HEIGHT());
            asteroid.restartFromInitialPosition();
            manager.getzOrder().add(asteroid);
            manager.asteroids.add(asteroid);

            for (Star star : manager.background.stars)
                ++star.speed;
            for (Star star : manager.background.bigStars1)
                ++star.speed;
            for (Star star : manager.background.bigStars2)
                ++star.speed;
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
            coreFW.getBackgroundAudioFW().stop();
        }
    }

    private void updateStatePause() {}

    private void updateStateReady()
    {
        if (coreFW.getTouchListenerFW().getTouchUp(Ready.getTouchArea(graphicsFW)))
        {
            coreFW.getSoundFW().start(R.raw.tap);
            gameState = GameState.RUNNING;
        }
    }

    @Override
    public void drawing()
    {
        switch (gameState)
        {
            case READY:       drawingStateReady(); break;
            case PAUSE:       drawingStatePause(); break;
            case RUNNING:     drawingStateRunning(); break;
        }
    }

    private void drawingStateRunning()
    {
        graphicsFW.clearScene(Color.BLACK);
        manager.drawing(graphicsFW);
    }

    private void drawingStateReady()
    {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(Ready);
    }

    private void drawingStatePause() {}
}
