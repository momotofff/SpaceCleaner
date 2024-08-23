package com.momotoff.spacecleaner.scene;

import android.graphics.Color;
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

public class WorldRating extends SceneFW
{
    private final StaticTextFW WorldRating = new StaticTextFW(coreFW.getString(R.string.txtWorldRating), new Point(50, 100), Color.WHITE, 100);
    private final StaticTextFW LocalRating = new StaticTextFW(coreFW.getString(R.string.txtLocalRating), new Point(400, 580), Color.WHITE, 70);
    private final StaticTextFW Back = new StaticTextFW(coreFW.getString(R.string.txtBack), new Point(50, 580), Color.WHITE, 70);

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Save save;
    private List<String> list = new ArrayList<>();
    private List<StaticTextFW> worldRating = new ArrayList<>();

    public WorldRating(CoreFW coreFW, Save save)
    {
        super(coreFW);

        this.save = save;

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");
        Query query = databaseReference.orderByChild("Result").limitToLast(5);
        query.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String email = dataSnapshot.child("Email").getValue().toString();
                    String result = dataSnapshot.child("Result").getValue().toString();

                    StringBuilder buffer = new StringBuilder();
                    buffer.append(email);
                    buffer.append(" - ");
                    buffer.append(result);
                    list.add(buffer.toString());
                }
                createWorldRating(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Log.d("myDebug", "onCancelled: ");
            }
        });
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

    private void createWorldRating(List<String> list)
    {
        final int RESULT_START_Y = 200;
        final int RESULT_STEP_Y = 70;
        Point position = new Point(WorldRating.position.x, RESULT_START_Y);

        for (int i = 0; i < list.size(); ++i)
        {
            StringBuffer buffer = new StringBuffer(i + 1 + ". ");
            buffer.append(list.get(list.size() - i - 1));
            StaticTextFW text = new StaticTextFW(buffer.toString(), new Point(position), Color.WHITE, 50);
            worldRating.add(text);
            position.y += RESULT_STEP_Y;
        }
    }
}
