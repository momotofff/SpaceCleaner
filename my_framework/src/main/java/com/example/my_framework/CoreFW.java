package com.example.my_framework;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import org.jetbrains.annotations.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CoreFW extends AppCompatActivity
{
    private final Point FRAME_BUFFER= new Point(1280, 720);

    private LoopFW loopFW;
    private GraphicsFW graphicsFW;
    private TouchListenerFW touchListenerFW;
    private Display display;
    private Point sizeDisplay;
    private Bitmap frameBuffer;
    private SceneFW sceneFW;

    private SharedPreferences sharedPreferences;
    private final String SETTINGS = "Settings";

    private final PointF scale = new PointF();

    private boolean stateOnPause;
    private boolean stateOnResume;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);

        sizeDisplay = new Point();
        display = getWindowManager().getDefaultDisplay();
        display.getSize(sizeDisplay);

        frameBuffer = Bitmap.createBitmap(FRAME_BUFFER.x, FRAME_BUFFER.y, Bitmap.Config.ARGB_8888);
        scale.x = (float) FRAME_BUFFER.x / sizeDisplay.x;
        scale.y = (float) FRAME_BUFFER.y / sizeDisplay.y;

        loopFW = new LoopFW(this, frameBuffer);
        graphicsFW = new GraphicsFW(getAssets(), frameBuffer);
        touchListenerFW = new TouchListenerFW(loopFW, scale);
        sceneFW = getStartScene();

        stateOnPause = false;
        stateOnResume = false;

        setContentView(loopFW);
    }

    public  CoreFW() {}

    public void onResume()
    {
        super.onResume();
        sceneFW.resume();
        loopFW.startGame();
    }

    public void onPause()
    {
        super.onPause();
        sceneFW.pause();
        loopFW.stopGame();
        stateOnPause = true;

        if (isFinishing())
        {
            sceneFW.dispose();
        }
    }

    public void setScene(SceneFW sceneFW)
    {
        if (sceneFW == null)
            throw new IllegalArgumentException("scene = null");

        this.sceneFW.pause();
        this.sceneFW.dispose();
        sceneFW.resume();
        sceneFW.update();
        this.sceneFW = sceneFW;
    }

    public void close()
    {
        //метод закрытия приложения
    }
    public GraphicsFW getGraphicsFW()
    {
        return graphicsFW;
    }

    public TouchListenerFW getTouchListenerFW()
    {
        return touchListenerFW;
    }

    public SharedPreferences getSharedPreferences() { return sharedPreferences; }

    public SceneFW getCurrentScene()
    {
        return sceneFW;
    }

    public SceneFW getStartScene() { return sceneFW;}
}
