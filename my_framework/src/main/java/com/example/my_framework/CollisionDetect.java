package com.example.my_framework;

public class CollisionDetect
{
    public static boolean collisionDetect (ObjectFW object1, ObjectFW object2)
    {
        int x = object1.getHitBox().centerX() - object2.getHitBox().centerX();;
        int y = object1.getHitBox().centerY() - object2.getHitBox().centerY();

        int distanceObjects = (int) Math.sqrt(x * x + y * y);

        return distanceObjects < object1.getRadius() + object2.getRadius();
    }


}
