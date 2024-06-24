package com.momotoff.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ContentView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.momotoff.my_framework.CoreFW;
import com.momotoff.my_framework.SceneFW;
import com.momotoff.my_framework.StaticTextFW;
import com.momotoff.spacecleaner.R;
import com.momotoff.spacecleaner.utilities.Resource;
import com.momotoff.spacecleaner.utilities.Save;

import java.lang.annotation.Annotation;

public class MainMenu extends SceneFW implements ContentView
{
    private final StaticTextFW Title = new StaticTextFW(coreFW.getString(R.string.txtMainMenuNameGame), new Point(50, 150), Color.WHITE, 100);
    private final StaticTextFW MenuStart = new StaticTextFW(coreFW.getString(R.string.txtMainMenuStartGame), new Point(50, 270), Color.WHITE, 60);
    private final StaticTextFW MenuSettings = new StaticTextFW(coreFW.getString(R.string.txtMainMenuMenuSettings), new Point(50, 370), Color.WHITE, 60);
    private final StaticTextFW MenuResults = new StaticTextFW(coreFW.getString(R.string.txtMainMenuResult), new Point(50, 470), Color.WHITE, 60);
    private final StaticTextFW MenuExit = new StaticTextFW(coreFW.getString(R.string.txtMainMenuExitGame), new Point(50, 570), Color.WHITE, 60);
    private Save save;
    public LinearLayout linearLayout;

    public MainMenu(CoreFW coreFW, Save save)
    {
        super(coreFW);
        this.save = save;

        coreFW.getBackgroundAudioFW().setTrack(com.momotoff.my_framework.R.raw.menu);
        coreFW.getBackgroundAudioFW().start();

        ConstraintLayout layout = new ConstraintLayout(this.coreFW.getApplicationContext());
        layout.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT));
        linearLayout = initializeWindowRegistration();
        layout.addView(coreFW.getLoopFW());
        coreFW.setContentView(layout);
        layout.addView(linearLayout);

        coreFW.setContentView(layout);
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

        if (coreFW.getTouchListenerFW().getTouchUp(MenuSettings.getTouchArea(graphicsFW)))
        {
            coreFW.getSoundFW().start(R.raw.tap);

        }

        // TODO: Lets create banner here, not in framework
        //coreFW.setBannerVisibility(View.VISIBLE);
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
    private LinearLayout initializeWindowRegistration()
    {
        // TODO: Move it out, framework isn't a garbage can to put anything inside
        LinearLayout.LayoutParams windowParam = new LinearLayout.LayoutParams(coreFW.getDisplaySize().x / 2, WindowManager.LayoutParams.WRAP_CONTENT);
        windowParam.setMargins(20,20,20,20);

        LinearLayout window = new LinearLayout(this.coreFW.getApplicationContext());
        window.setBackgroundColor(Color.WHITE);
        window.setOrientation(LinearLayout.VERTICAL);

        TextView name = new TextView(this.coreFW.getApplicationContext());
        name.setText("Name");
        name.setLayoutParams(windowParam);
        window.addView(name);

        EditText fieldName = new EditText(this.coreFW.getApplicationContext());
        fieldName.setLayoutParams(windowParam);
        window.addView(fieldName);

        TextView password = new TextView(this.coreFW.getApplicationContext());
        password.setText("Password");
        password.setLayoutParams(windowParam);
        window.addView(password);

        EditText fieldPassword = new EditText(this.coreFW.getApplicationContext());
        fieldPassword.setLayoutParams(windowParam);
        window.addView(fieldPassword);

        Button buttonNext = new Button(this.coreFW.getApplicationContext());
        buttonNext.setText("далее");
        buttonNext.setLayoutParams(windowParam);
        window.addView(buttonNext);

        LinearLayout screen = new LinearLayout(this.coreFW.getApplicationContext());
        screen.setLayoutParams(new LinearLayout.LayoutParams(coreFW.getDisplaySize().x, coreFW.getDisplaySize().y));
        screen.setGravity(Gravity.CENTER);
        screen.addView(window);

        return screen;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
