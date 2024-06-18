package com.momotoff.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;
import android.view.View;

import com.momotoff.my_framework.CoreFW;
import com.momotoff.my_framework.SceneFW;
import com.momotoff.my_framework.StaticTextFW;
import com.momotoff.spacecleaner.R;
import com.momotoff.spacecleaner.utilities.Resource;
import com.momotoff.spacecleaner.utilities.Save;

public class MainMenu extends SceneFW
{
    private final StaticTextFW Title = new StaticTextFW(coreFW.getString(R.string.txtMainMenuNameGame), new Point(50, 150), Color.WHITE, 100);
    private final StaticTextFW MenuStart = new StaticTextFW(coreFW.getString(R.string.txtMainMenuStartGame), new Point(50, 270), Color.WHITE, 60);
    private final StaticTextFW MenuSettings = new StaticTextFW(coreFW.getString(R.string.txtMainMenuMenuSettings), new Point(50, 370), Color.WHITE, 60);
    private final StaticTextFW MenuResults = new StaticTextFW(coreFW.getString(R.string.txtMainMenuResult), new Point(50, 470), Color.WHITE, 60);
    private final StaticTextFW MenuExit = new StaticTextFW(coreFW.getString(R.string.txtMainMenuExitGame), new Point(50, 570), Color.WHITE, 60);
    private Save save;

    public MainMenu(CoreFW coreFW, Save save)
    {
        super(coreFW);
        this.save = save;

        coreFW.getBackgroundAudioFW().setTrack(com.momotoff.my_framework.R.raw.menu);
        coreFW.getBackgroundAudioFW().start();
    }

    @Override
    public void update()
    {
        if (coreFW.getTouchListenerFW().getTouchUp(MenuStart.getTouchArea(graphicsFW)))
        {
            coreFW.getBackgroundAudioFW().stop();
            coreFW.getSoundFW().start(R.raw.tap);
            coreFW.setScene(new GameScene(coreFW, save));
        }

        if (coreFW.getTouchListenerFW().getTouchUp(MenuResults.getTouchArea(graphicsFW)))
        {
            coreFW.getSoundFW().start(R.raw.tap);
            coreFW.setScene(new Results(coreFW, save));
        }

        if (coreFW.getTouchListenerFW().getTouchUp(MenuExit.getTouchArea(graphicsFW)))
        {
            coreFW.getSoundFW().start(R.raw.tap);
            coreFW.onBackPressed();
        }

        coreFW.setBannerVisibility(View.VISIBLE);
    }

    @Override
    public void drawing()
    {
        graphicsFW.drawTexture(Resource.menuImage, new Point(0, 0));
        graphicsFW.drawText(Title);
        graphicsFW.drawText(MenuStart);
        graphicsFW.drawText(MenuSettings);
        graphicsFW.drawText(MenuResults);
        graphicsFW.drawText(MenuExit);
    }
}
