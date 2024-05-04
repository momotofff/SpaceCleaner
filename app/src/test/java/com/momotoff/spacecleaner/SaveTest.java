package com.momotoff.spacecleaner;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import com.momotoff.spacecleaner.utilities.Save;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SaveTest
{
    @Test
    public void testAddDistanceMethod()
    {
        Save save = new Save(new int[]{25, 20, 15, 10, 5});

        save.addDistance(13);
        assertArrayEquals(save.getDistance(), new int[] {25, 20, 15, 13, 10});

        save.addDistance(9);
        assertArrayEquals(save.getDistance(), new int[] {25, 20, 15, 13, 10});

        save.addDistance(100);
        assertArrayEquals(save.getDistance(), new int[] {100, 25, 20, 15, 13});
    }

    @Test
    public void testAddDistanceMethodWithZeroInitialize()
    {
        Save save = new Save();

        save.addDistance(42);
        assertArrayEquals(save.getDistance(), new int[] {42, 0, 0, 0, 0});
    }
}