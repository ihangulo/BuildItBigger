package com.hangulo.gradle.builditbigger;

import android.app.Activity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/*
 *   ================================================
 *        Android Devlelopment Nanodegree
 *        Project 4 - Build it Bigger
 *   ================================================
 *
 *        from : 21th JUL 2015
 *        to : 7th SEP 2015
 *
 *     Kwanghyun JUNG
 *     ihangulo@gmail.com
 *
 *    Android Devlelopment Nanodegree
 *    Udacity
 *
 *    adMob.java (free version)
 *
 *    Banner & Interstitial AD (only free flavor)
 *
 */

public class adMob implements IadmobUtility {

    private AdView mBannerAdView;
    private Activity mRoot;
    private static InterstitialAd mInterstitial;
    private static final String DEVICE_HASH="MY_DEVICE_BUILD_IT_BIGGER";


    public adMob(Activity activity) {
        mRoot=activity;
        mBannerAdView= (AdView) activity.findViewById(R.id.adView);
    }


    @Override
    public boolean interstitialLoadedthenShow()
    {
        if (mInterstitial.isLoaded()) {
            mInterstitial.show();
            return true;
        }
        requestNewInterstitialAd(); // if not loaded then fetch again
        return false;
    }

    // --- Google Admob -----
    // 1. Banner Ad
    @Override
    public void showBannerAd() {

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(DEVICE_HASH)
                .build();
        if (mBannerAdView!=null)
            mBannerAdView.loadAd(adRequest);
    }

    /*
    * Optional Tasks
    * Add Interstitial Ad
    * Follow these instructions to add an interstitial ad to the free version.
    * Display the ad after the user hits the button, and before the joke is shown.
    */
    // https://github.com/googleads/googleads-mobile-android-examples/tree/master/admob/InterstitialExample
    //https://github.com/udacity/google-play-services/blob/master/LessonAdMob_FINAL/AdViewer/app/src/main/java/com/example/adviewer/InterstitialActivity.java

    // 2. InterstitialAd
    @Override
    public void setupInterstitialAd() {
        mInterstitial = new InterstitialAd(mRoot);
        mInterstitial.setAdUnitId(mRoot.getString(R.string.interstitial_ad_unit_id));

        mInterstitial.setAdListener(new ToastAdListener(mRoot) {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                // error processing !
            }

            @Override
            public void onAdClosed() {
                requestNewInterstitialAd(); // change to new AD
                ((MainActivity)mRoot).getJoke();
            }
        });

        requestNewInterstitialAd();

    }
    @Override
    public void requestNewInterstitialAd() {

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitial.loadAd(adRequest);
    }

}
