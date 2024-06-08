package com.momotoff.my_framework;

import static org.junit.Assert.*;

import android.graphics.Rect;

import org.junit.Assert;
import org.junit.Test;

class TestObject extends ObjectFW
{
    TestObject(Rect hitBox, int radius)
    {
        this.hitBox = hitBox;
        this.radius = radius;
    }
}

public class CollisionDetectorTest
{
    @Test
    public void detect_sameObject_expectTrue()
    {
        ObjectFW obj1 = new TestObject(new Rect(0, 0, 10, 10), 10);
        Assert.assertTrue(CollisionDetector.detect(obj1, obj1));
    }

    @Test
    public void detect_objectsAreFar_expectFalse()
    {
        ObjectFW obj1 = new TestObject(new Rect(0, 0, 10, 10), 10);
        ObjectFW obj2 = new TestObject(new Rect(100, 100, 110, 110), 10);
        Assert.assertFalse(CollisionDetector.detect(obj1, obj2));
    }

    @Test
    public void detect_objectsAreClose_expectTrue()
    {
        ObjectFW obj1 = new TestObject(new Rect(0, 0, 10, 10), 10);
        ObjectFW obj2 = new TestObject(new Rect(11, 11, 21, 21), 10);
        Assert.assertTrue(CollisionDetector.detect(obj1, obj2));
    }
}