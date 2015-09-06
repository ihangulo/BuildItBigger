package com.hangulo.gradle.builditbigger;

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
 *    IadmobUtility.java
 *      - interface for using admob
 */
public interface IadmobUtility {
    // show banner ad
    void showBannerAd();
    // show interstitial ad
    boolean interstitialLoadedthenShow();
    void setupInterstitialAd();
    void requestNewInterstitialAd();
}
