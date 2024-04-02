package com.example.my_framework;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class CoreFW extends AppCompatActivity
{
    private final Point FRAME_BUFFER = new Point(1280, 720);

    private LoopFW loopFW;
    private GraphicsFW graphicsFW;
    private TouchListenerFW touchListenerFW;
    private Display display;
    private Point sizeDisplay;
    private Bitmap frameBuffer;
    private SceneFW sceneFW;
    private BackgroundAudioFW backgroundAudioFW;
    private SoundFW soundFW;

    private SharedPreferences sharedPreferences;
    private final String SETTINGS = "Settings";

    private final PointF scale = new PointF();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);

        sizeDisplay = new Point();
        display = getWindowManager().getDefaultDisplay();
        display.getSize(sizeDisplay);

        frameBuffer = Bitmap.createBitmap(FRAME_BUFFER.x, FRAME_BUFFER.y, Bitmap.Config.ARGB_8888);
        scale.x = (float) FRAME_BUFFER.x / sizeDisplay.x;
        scale.y = (float) FRAME_BUFFER.y / sizeDisplay.y;

        backgroundAudioFW = new BackgroundAudioFW(this);
        soundFW = new SoundFW(this);

        loopFW = new LoopFW(this, frameBuffer);
        graphicsFW = new GraphicsFW(getAssets(), frameBuffer);
        touchListenerFW = new TouchListenerFW(loopFW, scale);
        sceneFW = getStartScene();

        setContentView(loopFW);
    }

    public CoreFW() {}

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
        backgroundAudioFW.getMediaPlayer().release();

        if (isFinishing())
            sceneFW.dispose();
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

    public void onDestroy() {
        super.onDestroy();
    }

    public void onStop()
    {
        super.onStop();
        loopFW.stopGame();
        backgroundAudioFW.getMediaPlayer().release();
    }

    @Override
    public void onBackPressed() { finish(); }

    public GraphicsFW getGraphicsFW() { return graphicsFW; }

    public TouchListenerFW getTouchListenerFW() { return touchListenerFW; }

    public SharedPreferences getSharedPreferences() { return sharedPreferences; }

    public SceneFW getCurrentScene() { return sceneFW; }

    public SceneFW getStartScene() { return sceneFW; }

    public BackgroundAudioFW getBackgroundAudioFW() { return backgroundAudioFW; }

    public SoundFW getSoundFW() { return soundFW; }
}
