package com.example.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;

import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.my_framework.StaticTextFW;
import com.example.spacecleaner.R;
import com.example.spacecleaner.utilities.Resource;
import com.example.spacecleaner.utilities.Save;

import java.util.Locale;

public class Results extends SceneFW
{
    private final StaticTextFW[] Numbers = new StaticTextFW[5];
    private final StaticTextFW BestResults = new StaticTextFW(coreFW.getString(R.string.txtBestResults), new Point(50, 100), Color.WHITE, 100);
    private final StaticTextFW Back = new StaticTextFW(coreFW.getString(R.string.txtBack), new Point(50, 580), Color.WHITE, 70);

    private Save save;

    public Results(CoreFW coreFW, Save save)
    {
        super(coreFW);
        this.save = save;

        final int RESULT_START_Y = 200;
        final int RESULT_STEP_Y = 70;

        Point position = new Point(BestResults.position.x, RESULT_START_Y);

        for (int i = 0; i < Numbers.length; ++i)
        {
            String text = String.format(Locale.getDefault(), "%d. %d", i + 1, save.getDistance()[i]);
            this.Numbers[i] = new StaticTextFW(text, new Point(position), Color.WHITE, 50);
            position.y += RESULT_STEP_Y;
        }
    }

    @Override
    public void update()
    {
        if (coreFW.getTouchListenerFW().getTouchUp(Back.getTouchArea(graphicsFW)))
        {
            coreFW.getSoundFW().start(R.raw.tap);
            //coreFW.getBackgroundAudioFW().stop();
            coreFW.setScene(new MainMenu(coreFW, save));
        }
    }

    @Override
    public void drawing()
    {
        graphicsFW.drawTexture(Resource.menuImage, new Point(0, 0));
        graphicsFW.drawText(BestResults);
        graphicsFW.drawText(Back);

        for (StaticTextFW number: Numbers)
            graphicsFW.drawText(number);
    }
}
