package com.example.my_framework;

public class LoopFW implements Runnable
{
    private final int FPS = 60;
    private final int SECOND = 1000;

    private  boolean running = false;

    Thread gameThread = null;

    //temp
    float updates = 0;
    float drawing = 0;
    long timer = 0;

    @Override
    public void run()
    {
        timer =System.currentTimeMillis();
        long UPDATE_TIME = (long) SECOND / FPS;

        while (running)
        {
            try
            {
                Thread.sleep(UPDATE_TIME);
                updateGame();
                drawingGame();
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }

            if (System.currentTimeMillis() - timer > 1000)
            {
                System.out.println(updates);
                System.out.println(drawing);
                System.out.println();
                updates = 0;
                drawing = 0;
                timer += 1000;
            }
        }
    }

    public void startGame()
    {
        if (running)
            return;

        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stopGame()
    {
        if (!running)
            return;

        running = false;

        try { gameThread.join();}
        catch (InterruptedException e) { e.printStackTrace();}
    }

    private void updateGame() {++updates;}
    private void drawingGame() {++drawing;}
}
