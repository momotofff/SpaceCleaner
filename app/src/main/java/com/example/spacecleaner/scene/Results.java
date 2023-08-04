package com.example.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;

import com.example.my_framework.AudioFW;
import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.my_framework.StaticTextFW;
import com.example.spacecleaner.R;
import com.example.spacecleaner.utilities.Save;

import java.util.Locale;

public class Results extends SceneFW
{
    private final StaticTextFW[] Numbers = new StaticTextFW[5];
    private final StaticTextFW BestResults = new StaticTextFW(coreFW.getString(R.string.txtBestResults), new Point(50, 100), Color.BLUE, 100, null);
    private final StaticTextFW Back = new StaticTextFW(coreFW.getString(R.string.txtBack), new Point(50, 650), Color.BLUE, 70, null);

    private Save save;
    AudioFW audioFW;

    public Results(CoreFW coreFW, Save save, AudioFW audioFW)
    {
        super(coreFW);
        this.save = save;
        this.audioFW = audioFW;
        final int RESULT_START_Y = 200;
        final int RESULT_STEP_Y = 70;

        Point position = new Point(BestResults.position.x, RESULT_START_Y);

        for (int i = 0; i < Numbers.length; ++i)
        {
            String text = String.format(Locale.getDefault(), "%d. %d", i + 1, save.getDistance()[i]);
            this.Numbers[i] = new StaticTextFW(text, new Point(position), Color.BLUE, 50, null);
            position.y += RESULT_STEP_Y;
        }
    }

    @Override
    public void update()
    {
        if (coreFW.getTouchListenerFW().getTouchUp(Back.getTouchArea(graphicsFW)))
        {
            coreFW.setScene(new MainMenu(coreFW, save));
            audioFW.mediaPlayer.release();
        }
    }

    @Override
    public void drawing()
    {
        graphicsFW.clearScene(Color.GREEN);
        graphicsFW.drawText(BestResults);
        graphicsFW.drawText(Back);

        for (StaticTextFW number: Numbers)
            graphicsFW.drawText(number);
    }

    @Override
    public void pause() {audioFW.mediaPlayer.pause(); }

    @Override
    public void resume() {}

    @Override
    public void dispose() {}
}
