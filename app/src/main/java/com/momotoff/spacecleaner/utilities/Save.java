package com.momotoff.spacecleaner.utilities;

import android.content.SharedPreferences;
import android.util.Log;
import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Save implements Serializable
{
    private int[] distance = {0, 0, 0, 0, 0};

    //private Map<String, Integer> worldRating = new HashMap<>();
    //private Map<String, Integer> map = new HashMap<>();
    //private DatabaseReference databaseReference;

    public Save(int[] distance)
    {
        this.distance = distance;
    }

    public Save()
    {
        //loadBase();
        //worldRating = sortByValue(map);
    }

    public void addDistance(int value)
    {
        for (int i = 0; i < distance.length; ++i)
        {
            int number = value;

            if (number > distance[i])
            {
                value = distance[i];
                distance[i] = number;
            }
        }
    }

    public void save(SharedPreferences preferences)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();

        String result = "";

        try
        {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objStream = new ObjectOutputStream(byteStream);
            objStream.writeObject(this);
            objStream.close();
            result = Base64.getEncoder().encodeToString(byteStream.toByteArray());
        }
        catch (Exception e)
        {
            Log.w(this.getClass().getSimpleName(), "Error while storing save data. Exception says: " + e.getMessage());
        }

        editor.putString(this.getClass().getSimpleName(), result);
        editor.apply();
    }

    public void loadDistance(SharedPreferences preferences)
    {
        String serialized = preferences.getString(this.getClass().getSimpleName(), "");
        byte[] data = Base64.getDecoder().decode(serialized);

        try
        {
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            this.distance = ((Save) ois.readObject()).distance;
            ois.close();
        }
        catch (Exception e)
        {
            Log.w(this.getClass().getSimpleName(), "Error while deserializing save data. Exception says: " + e.getMessage());
        }
    }

    public int[] getDistance() { return distance; }

    /*private void loadBase()
    {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

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

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map )
    {
        Map<K,V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K,V>> st = map.entrySet().stream();

        st.sorted(Comparator.comparing(e -> e.getValue()))
                .forEach(e -> result.put(e.getKey(),e.getValue()));

        return result;
    }

    public Map<String, Integer> getWorldRating() {
        return worldRating;
    }*/
}
