package com.example.my_framework;

public class TimerDelay
{
    long start;
    long end;
    long difference;
    final int SECOND = 1000000000;

    public void startTimer()
    {
        start = System.nanoTime() / SECOND;
    }

    public boolean delay(int second)
    {
        end = System.nanoTime() / SECOND;
        difference = end - start;

        return difference > second;
    }
}
