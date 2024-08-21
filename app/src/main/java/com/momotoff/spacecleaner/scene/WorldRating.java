package com.momotoff.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.FirebaseDatabaseKtxRegistrar;

import com.google.firebase.database.ValueEventListener;
import com.momotoff.my_framework.CoreFW;
import com.momotoff.my_framework.SceneFW;
import com.momotoff.my_framework.StaticTextFW;
import com.momotoff.spacecleaner.R;
import com.momotoff.spacecleaner.utilities.Resource;
import com.momotoff.spacecleaner.utilities.Save;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

public class WorldRating extends SceneFW
{
    private final StaticTextFW[] Users = new StaticTextFW[5];
    private final StaticTextFW WorldRating = new StaticTextFW(coreFW.getString(R.string.txtWorldRating), new Point(50, 100), Color.WHITE, 100);
    private final StaticTextFW LocalRating = new StaticTextFW(coreFW.getString(R.string.txtLocalRating), new Point(400, 580), Color.WHITE, 70);
    private final StaticTextFW Back = new StaticTextFW(coreFW.getString(R.string.txtBack), new Point(50, 580), Color.WHITE, 70);

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Save save;
    Map<String, Integer> map = new HashMap<>();
    LinkedList<String> keys = new LinkedList<>();

    public WorldRating(CoreFW coreFW, Save save)
    {
        super(coreFW);

        this.save = save;

        final int RESULT_START_Y = 200;
        final int RESULT_STEP_Y = 70;

        Point position = new Point(WorldRating.position.x, RESULT_START_Y);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String str = dataSnapshot.child("Email").getValue().toString();
                    String email = str.replace("@", "");
                    int result = Integer.parseInt(dataSnapshot.child("Result").getValue().toString());
                    keys.add(email);

                    map.put(email, result);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Log.d("myDebug", "onCancelled: ");
            }
        });


        //loadBase();


        for (int i = 0; i < Users.length; ++i)
        {
            String text = String.format(Locale.getDefault(), "%d. %d", i + 1, 0);
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

        for (String number: keys)
            graphicsFW.drawText(new StaticTextFW(number, new Point(100, 150), Color.WHITE, 100));
    }

    private void loadBase()
    {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    String str = dataSnapshot.child("Email").getValue().toString();
                    String email = str.replace("@", "");
                    int result = Integer.parseInt(dataSnapshot.child("Result").getValue().toString());

                    map.put(email, result);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("myDebug", "onCancelled: ");
            }
        });
    }

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map )
    {
        Map<K,V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K,V>> st = map.entrySet().stream();

        st.sorted(Comparator.comparing(e -> e.getValue()))
                .forEach(e -> result.put(e.getKey(),e.getValue()));

        return result;
    }
}
