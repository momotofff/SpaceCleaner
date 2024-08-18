package com.momotoff.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.momotoff.my_framework.CoreFW;
import com.momotoff.my_framework.SceneFW;
import com.momotoff.my_framework.StaticTextFW;
import com.momotoff.spacecleaner.R;
import com.momotoff.spacecleaner.utilities.Resource;
import com.momotoff.spacecleaner.utilities.Save;

import java.util.LinkedList;
import java.util.Locale;

public class WorldRating extends SceneFW
{
    private final StaticTextFW[] Numbers = new StaticTextFW[5];
    private final StaticTextFW[] Users = new StaticTextFW[5];
    private final StaticTextFW WorldRating = new StaticTextFW(coreFW.getString(R.string.txtWorldRating), new Point(50, 100), Color.WHITE, 100);
    private final StaticTextFW LocalRating = new StaticTextFW(coreFW.getString(R.string.txtLocalRating), new Point(400, 580), Color.WHITE, 70);
    private final StaticTextFW Back = new StaticTextFW(coreFW.getString(R.string.txtBack), new Point(50, 580), Color.WHITE, 70);

    private DatabaseReference databaseReference;

    private Save save;

    public WorldRating(CoreFW coreFW, Save save)
    {
        super(coreFW);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        this.save = save;

        final int RESULT_START_Y = 200;
        final int RESULT_STEP_Y = 70;

        Point position = new Point(WorldRating.position.x, RESULT_START_Y);

        LinkedList<String> list = new LinkedList<>(save.getWorldRating().keySet());

        for (int i = 0; i < Users.length; ++i)
        {
            String text;
            if (i < save.getWorldRating().size())
               text = String.format(Locale.getDefault(), "%d. %d   -   %s", i + 1, save.getWorldRating().get(list.get(i)), list.get(i));
            else
                text = String.format(Locale.getDefault(), "%d. 0", i + 1);

            this.Users[i] = new StaticTextFW(text, new Point(position), Color.WHITE, 50);
            position.y += RESULT_STEP_Y;
        }


    }

    @Override
    public void update()
    {
        if (coreFW.getTouchListenerFW().getTouchUp(Back.getTouchArea(graphicsFW)))
        {
            coreFW.getSoundFW().start(R.raw.tap);
            coreFW.getBackgroundAudioFW().stop();
            coreFW.setScene(MainMenu.getInstance());
        }

        if (coreFW.getTouchListenerFW().getTouchUp(LocalRating.getTouchArea(graphicsFW)))
        {
            coreFW.getSoundFW().start(R.raw.tap);
            coreFW.getBackgroundAudioFW().stop();
            coreFW.setScene(new LocalRating(coreFW, save));
        }
    }

    @Override
    public void drawing()
    {
        graphicsFW.drawTexture(Resource.menuImage, new Point(0, 0));
        graphicsFW.drawText(WorldRating);
        graphicsFW.drawText(Back);
        graphicsFW.drawText(LocalRating);

        for (StaticTextFW number: Users)
            graphicsFW.drawText(number);
    }
}
