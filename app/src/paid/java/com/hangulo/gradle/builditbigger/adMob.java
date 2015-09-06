package com.hangulo.gradle.builditbigger;

import android.app.Activity;
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
 *    adMob.java (for paid flavor)
 *      --> just for one Main code, this routine is not running acutal codes.
 */
public class adMob implements  IadmobUtility {
    @Override
    public boolean interstitialLoadedthenShow() {
        return true;
    }
    public adMob(Activity activity) {
    }
    @Override
    public void showBannerAd() {
    }
    @Override
    public void setupInterstitialAd() {
    }

    @Override
    public void requestNewInterstitialAd() {
    }
}
