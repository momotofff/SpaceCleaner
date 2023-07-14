package com.example.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;

import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.my_framework.StaticTextFW;
import com.example.spacecleaner.R;
import com.example.spacecleaner.classes.Saves;

import java.util.Locale;

public class Results extends SceneFW
{
    StaticTextFW[] numbers = new StaticTextFW[5];
    private int leading = 70;

    private final StaticTextFW BestResults = new StaticTextFW(coreFW.getString(R.string.txtBestResults), new Point(50, 100), Color.BLUE, 100, null);
    private final StaticTextFW Back = new StaticTextFW(coreFW.getString(R.string.txtBack), new Point(50, 650), Color.BLUE, 70, null);

    public Results(CoreFW coreFW)
    {
        super(coreFW);

        int lead = 200;

        for (int  i = 0; i < numbers.length; ++i)
        {
            this.numbers[i] = new StaticTextFW(String.format(Locale.getDefault(), "%d. %d", i + 1, Saves.distance[i]),new Point(BestResults.position.x, lead), Color.BLUE, 50, null);
            lead += leading;
        }
    }

    @Override
    public void update()
    {
        if (coreFW.getTouchListenerFW().getTouchUp(Back.getTouchArea(graphicsFW)))
            coreFW.setScene(new MainMenu(coreFW));
    }

    @Override
    public void drawing()
    {
        graphicsFW.clearScene(Color.GREEN);
        graphicsFW.drawText(BestResults);
        graphicsFW.drawText(Back);
        for (int i = 0; i < numbers.length; ++i)
            graphicsFW.drawText(numbers[i]);
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
