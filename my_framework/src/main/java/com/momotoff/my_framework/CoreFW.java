package com.momotoff.my_framework;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.yandex.mobile.ads.banner.BannerAdSize;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.common.AdRequest;

public class CoreFW extends AppCompatActivity
{
    private final Point FRAME_BUFFER = new Point(1280, 720);

    private LoopFW loopFW;
    private GraphicsFW graphicsFW;
    private TouchListenerFW touchListenerFW;
    private SceneFW sceneFW;
    private BackgroundAudioFW backgroundAudioFW;
    private SoundFW soundFW;

    private SharedPreferences sharedPreferences;
    private final String SETTINGS = "Settings";

    private final PointF scale = new PointF();

    public BannerAdView banner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        System.out.println("запустился onCreate");
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);

        Point displaySize = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(displaySize);

        Bitmap frameBuffer = Bitmap.createBitmap(FRAME_BUFFER.x, FRAME_BUFFER.y, Bitmap.Config.ARGB_8888);
        scale.x = (float) FRAME_BUFFER.x / displaySize.x;
        scale.y = (float) FRAME_BUFFER.y / displaySize.y;

        backgroundAudioFW = new BackgroundAudioFW(this);
        soundFW = new SoundFW(this);

        loopFW = new LoopFW(this, frameBuffer);
        graphicsFW = new GraphicsFW(getAssets(), frameBuffer);
        touchListenerFW = new TouchListenerFW(loopFW, scale);
        sceneFW = getStartScene();

        banner = new BannerAdView(this);
        banner.setAdSize(BannerAdSize.stickySize(this, displaySize.x));
        banner.setAdUnitId("R-M-7427752-1");

        ConstraintLayout layout = new ConstraintLayout(this);
        layout.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT));
        layout.addView(loopFW);
        layout.addView(banner, displaySize.x, displaySize.y * 2 - 160);
        setContentView(layout);
    }

    public CoreFW() {}

    @Override
    public void onResume()
    {
        System.out.println("запустился onResume");
        super.onResume();
        backgroundAudioFW.start();
    }

    @Override
    public void onStart()
    {
        System.out.println("запустился onStart");
        super.onStart();
        loopFW.startGame();
        backgroundAudioFW.start();
    }

    @Override
    public void onPause()
    {
        System.out.println("запустился onPause");
        super.onPause();
        loopFW.pausedGame();
        backgroundAudioFW.pause();
    }

    public void setScene(SceneFW sceneFW)
    {
        if (sceneFW == null)
            throw new IllegalArgumentException("scene = null");

        sceneFW.update();
        this.sceneFW = sceneFW;
    }

    @Override
    public void onDestroy()
    {
        System.out.println("запустился onDestroy");
        super.onDestroy();
    }

    @Override
    public void onStop()
    {
        System.out.println("запустился onStop");
        super.onStop();
        loopFW.stopGame();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        System.out.println("запустился onBackPressed");
        finish();
    }

    public GraphicsFW getGraphicsFW() { return graphicsFW; }

    public TouchListenerFW getTouchListenerFW() { return touchListenerFW; }

    public SharedPreferences getSharedPreferences() { return sharedPreferences; }

    public SceneFW getCurrentScene() { return sceneFW; }

    public SceneFW getStartScene() { return sceneFW; }

    public BackgroundAudioFW getBackgroundAudioFW() { return backgroundAudioFW; }

    public SoundFW getSoundFW() { return soundFW; }

    public void setBannerVisibility(int visibility)
    {
        if (banner.getVisibility() == visibility)
            return;

        if (visibility == View.VISIBLE)
            banner.loadAd(new AdRequest.Builder().build());

        this.runOnUiThread(() -> banner.setVisibility(visibility));
    }
}
