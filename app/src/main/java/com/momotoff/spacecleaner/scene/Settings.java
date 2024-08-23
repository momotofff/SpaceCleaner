package com.momotoff.spacecleaner.scene;

import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.momotoff.my_framework.CoreFW;
import com.momotoff.my_framework.SceneFW;
import com.momotoff.my_framework.StaticTextFW;
import com.momotoff.spacecleaner.R;
import com.momotoff.spacecleaner.utilities.Resource;

public class Settings extends SceneFW
{
    private final StaticTextFW settings = new StaticTextFW(coreFW.getString(R.string.txtMainMenuMenuSettings), new Point(50, 150), Color.WHITE, 100);
    private final StaticTextFW singOut = new StaticTextFW(coreFW.getString(R.string.txtSingOut), new Point(50, 250), Color.WHITE, 60);
    private final StaticTextFW deleteAccount = new StaticTextFW(coreFW.getString(R.string.txtDeleteAccount), new Point(50, 350), Color.WHITE, 60);
    private final StaticTextFW back = new StaticTextFW(coreFW.getString(R.string.txtBack), new Point(50, 580), Color.WHITE, 70);

    public Settings(CoreFW coreFW)
    {
        super(coreFW);
    }


    @Override
    public void update()
    {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null)
        {
            if (coreFW.getTouchListenerFW().getTouchUp(singOut.getTouchArea(graphicsFW)))
            {
                coreFW.getSoundFW().start(R.raw.tap);
                coreFW.getBackgroundAudioFW().stop();

                FirebaseAuth.getInstance().signOut();
            }

            if (coreFW.getTouchListenerFW().getTouchUp(deleteAccount.getTouchArea(graphicsFW)))
            {
                coreFW.getSoundFW().start(R.raw.tap);
                coreFW.getBackgroundAudioFW().stop();

                currentUser.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    Log.d(TAG, "User account deleted.");
                                }
                            }
                        });


            }
        }

        if (coreFW.getTouchListenerFW().getTouchUp(back.getTouchArea(graphicsFW)))
        {
            coreFW.getSoundFW().start(R.raw.tap);
            coreFW.getBackgroundAudioFW().stop();
            coreFW.setScene(MainMenu.getInstance());
        }
    }

    @Override
    public void drawing()
    {
        graphicsFW.drawTexture(Resource.menuImage, new Point(0, 0));
        graphicsFW.drawText(settings);
        graphicsFW.drawText(singOut);
        graphicsFW.drawText(deleteAccount);
        graphicsFW.drawText(back);
    }
}
