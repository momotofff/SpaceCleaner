package com.momotoff.spacecleaner.scene;

import android.graphics.Point;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.momotoff.my_framework.CoreFW;
import com.momotoff.my_framework.SceneFW;
import com.momotoff.my_framework.StaticTextFW;
import com.momotoff.spacecleaner.R;
import com.momotoff.spacecleaner.utilities.Resource;
import com.momotoff.spacecleaner.utilities.Save;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WorldRating extends SceneFW implements ValueEventListener
{
    private final StaticTextFW WorldRating = new StaticTextFW(coreFW.getString(R.string.txtWorldRating), new Point(50, 100), 100);
    private final StaticTextFW LocalRating = new StaticTextFW(coreFW.getString(R.string.txtLocalRating), new Point(400, 580), 70);
    private final StaticTextFW Back = new StaticTextFW(coreFW.getString(R.string.txtBack), new Point(50, 580), 70);

    private final Save save;
    private final List<StaticTextFW> worldRating = new ArrayList<>();

    public WorldRating(CoreFW coreFW, Save save)
    {
        super(coreFW);
        this.save = save;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Users");
        Query query = databaseReference.orderByChild("Result").limitToLast(5);
        query.addListenerForSingleValueEvent(this);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot)
    {
        List<String> list = new ArrayList<>();

        for (DataSnapshot dataSnapshot : snapshot.getChildren())
        {
            String email = dataSnapshot.child("Email").getValue(String.class);
            Long result = dataSnapshot.child("Result").getValue(Long.class);
            list.add(String.format(Locale.getDefault(), "%s - %d", email, result));
        }

        final int RESULT_START_Y = 200;
        final int RESULT_STEP_Y = 70;
        Point position = new Point(WorldRating.position.x, RESULT_START_Y);

        for (int i = 1; i <= list.size(); ++i)
        {
            String text = String.format(Locale.getDefault(), "%d. %s", i, list.get(list.size() - i));
            worldRating.add(new StaticTextFW(text, new Point(position), 50));
            position.y += RESULT_STEP_Y;
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error)
    {
        Log.d("myDebug", "onCancelled: ");
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

        for (StaticTextFW text: worldRating)
            graphicsFW.drawText(text);
    }
}
