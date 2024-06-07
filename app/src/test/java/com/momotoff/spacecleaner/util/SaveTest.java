package com.momotoff.spacecleaner.util;

import static org.junit.Assert.assertArrayEquals;

import com.momotoff.spacecleaner.utilities.Save;

import org.junit.Test;

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
