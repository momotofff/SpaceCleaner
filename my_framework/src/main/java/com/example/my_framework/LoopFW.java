package com.example.my_framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class LoopFW extends SurfaceView implements Runnable
{
    private final int FPS = 50;
    private final int SECOND = 1000;

    private boolean running = false;

    Thread gameThread = null;
    CoreFW coreFW;
    Bitmap frameBuffer;
    SurfaceHolder surfaceHolder;
    Canvas canvas;
    Rect rect;

    public LoopFW(CoreFW coreFW, Bitmap frameBuffer)
    {
        super(coreFW);
        this.frameBuffer = frameBuffer;
        this.coreFW = coreFW;
        this.surfaceHolder = getHolder();
        rect = new Rect();
        canvas = new Canvas();
    }

    //temp
    int updates = 0;
    int draws = 0;
    long timer = 0;

    @Override
    public void run()
    {
        timer = System.currentTimeMillis();
        final long UPDATE_TIME = (long) SECOND / FPS;

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
//                Log.d(this.getClass().getSimpleName(), String.format("Updates: %d / 1 sec", updates));
//                Log.d(this.getClass().getSimpleName(), String.format("Redraws: %d / 1 sec", draws));

                updates = 0;
                draws = 0;
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

    private void updateGame()
    {
        ++updates;
        coreFW.getCurrentScene().update();
    }

    private void drawingGame()
    {
        ++draws;
        if (surfaceHolder.getSurface().isValid())
        {
            canvas = surfaceHolder.lockCanvas();
            canvas.getClipBounds(rect);
            canvas.drawBitmap(frameBuffer, null, rect, null);
            coreFW.getCurrentScene().drawing();
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
}
