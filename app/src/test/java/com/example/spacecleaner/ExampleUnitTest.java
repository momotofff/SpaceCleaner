package com.example.spacecleaner;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.spacecleaner.classes.Saves;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest
{
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testAddDistanceMethod()
    {
        Saves.distance = new int[]{25, 20, 15, 10, 5};

        Saves.addDistance(13);
        int[] test1 = {25,20,15,13,10};
        for (int i = 0; i < Saves.distance.length; ++i)
        {
            assertEquals(test1[i], Saves.distance[i]);
        }

        Saves.addDistance(9);
        int[] test2 = {25,20,15,13,10};
        for (int i = 0; i < Saves.distance.length; ++i)
        {
            assertEquals(test2[i], Saves.distance[i]);
        }

        Saves.addDistance(100);
        int[] test3 = {100,25,20,15,13};
        for (int i = 0; i < Saves.distance.length; ++i)
        {
            assertEquals(test3[i], Saves.distance[i]);
        }
    }
}