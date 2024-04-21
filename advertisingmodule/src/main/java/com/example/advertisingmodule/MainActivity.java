package com.example.advertisingmodule;

import android.os.Bundle;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.yandex.mobile.ads.banner.BannerAdEventListener;
import com.yandex.mobile.ads.banner.BannerAdSize;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.common.AdRequest;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;

public class MainActivity extends AppCompatActivity
{
    private BannerAdView bannerAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        bannerAdView = findViewById(R.id.banner_ad_view);
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
    }
}