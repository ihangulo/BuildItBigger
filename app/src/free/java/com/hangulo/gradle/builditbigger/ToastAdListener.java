package com.hangulo.gradle.builditbigger;
/*
 * Copyright (C) 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
 *    ToastAdListner.java
 *    Using Debug
 *
 *    *Original soruce
 *      https://github.com/udacity/google-play-services/blob/master/LessonAdMob_FINAL/AdViewer/app/src/main/java/com/example/adviewer/ToastAdListener.java
 */

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;

public class ToastAdListener extends AdListener {
    private Context mContext;
    private String mErrorReason;


    public ToastAdListener(Context context) {
        this.mContext = context;
    }

    // Toast Messages are used when debugging
    @Override
    public void onAdLoaded() {
//        Toast.makeText(mContext, "onAdLoaded()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAdOpened() {
//        Toast.makeText(mContext, "onAdOpened()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAdClosed() {
//        Toast.makeText(mContext, "onAdClosed()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAdLeftApplication() {
//        Toast.makeText(mContext, "onAdLeftApplication()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAdFailedToLoad(int errorCode) {
        mErrorReason = "";
        switch(errorCode) {
            case AdRequest.ERROR_CODE_INTERNAL_ERROR:
                mErrorReason = "Internal error";
                break;
            case AdRequest.ERROR_CODE_INVALID_REQUEST:
                mErrorReason = "Invalid request";
                break;
            case AdRequest.ERROR_CODE_NETWORK_ERROR:
                mErrorReason = "Network Error";
                break;
            case AdRequest.ERROR_CODE_NO_FILL:
                mErrorReason = "No fill";
                break;
        }

        Log.e ("onAdFailedToLoad", "Error Code : "+ errorCode+ " " + mErrorReason );
////        Toast.makeText(mContext,
//                String.format("onAdFailedToLoad(%s)", mErrorReason),
//                Toast.LENGTH_SHORT).show();
    }

    public String getErrorReason() {
        return mErrorReason == null ? "" : mErrorReason;
    }

}