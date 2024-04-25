package com.example.my_framework;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yandex.mobile.ads.banner.BannerAdEventListener;
import com.yandex.mobile.ads.banner.BannerAdSize;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.common.AdRequest;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;
import com.yandex.mobile.ads.common.MobileAds;


public class CoreFW extends AppCompatActivity
{
    private final Point FRAME_BUFFER = new Point(1280, 720);

    private LoopFW loopFW;
    private GraphicsFW graphicsFW;
    private TouchListenerFW touchListenerFW;
    private Display display;
    private Point displaySize;
    private Bitmap frameBuffer;
    private SceneFW sceneFW;
    private BackgroundAudioFW backgroundAudioFW;
    private SoundFW soundFW;

    private SharedPreferences sharedPreferences;
    private final String SETTINGS = "Settings";

    private final PointF scale = new PointF();

    public BannerAdView bannerAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        System.out.println("запустился onCreate");
        super.onCreate(savedInstanceState);

        MobileAds.initialize(this, () -> {
            // now you can use ads
        });

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);

        displaySize = new Point();
        display = getWindowManager().getDefaultDisplay();
        display.getSize(displaySize);

        frameBuffer = Bitmap.createBitmap(FRAME_BUFFER.x, FRAME_BUFFER.y, Bitmap.Config.ARGB_8888);
        scale.x = (float) FRAME_BUFFER.x / displaySize.x;
        scale.y = (float) FRAME_BUFFER.y / displaySize.y;

        backgroundAudioFW = new BackgroundAudioFW(this);
        soundFW = new SoundFW(this);

        loopFW = new LoopFW(this, frameBuffer);
        graphicsFW = new GraphicsFW(getAssets(), frameBuffer);
        touchListenerFW = new TouchListenerFW(loopFW, scale);
        sceneFW = getStartScene();



        bannerAdView = new BannerAdView(this);
        bannerAdView.setAdUnitId("R-M-7427752-1");
        bannerAdView.setAdSize(BannerAdSize.stickySize(this, 1000 ));

        final AdRequest adRequest = new AdRequest.Builder().build();

        bannerAdView.setBannerAdEventListener(new BannerAdEventListener()
        {
            @Override
            public void onAdLoaded() {}

            @Override
            public void onAdFailedToLoad(@NonNull AdRequestError adRequestError) {}

            @Override
            public void onAdClicked() {}

            @Override
            public void onLeftApplication() {}

            @Override
            public void onReturnedToApplication() {}

            @Override
            public void onImpression(@Nullable ImpressionData impressionData) {}
        });

        bannerAdView.loadAd(adRequest);
        loopFW.setBanner(bannerAdView);

        setContentView(loopFW);

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

    public Point getDisplaySize() { return displaySize; }
}
