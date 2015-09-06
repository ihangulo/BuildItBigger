package com.hangulo.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.hangulo.gce.backend.myApi.MyApi;


import java.io.IOException;


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
 *    GetJokesFromBackend.java
 *      AsyncTask which is fetching the
 *
 *    *Original soruce
 *      https://github.com/udacity/google-play-services/blob/master/LessonAdMob_FINAL/AdViewer/app/src/main/java/com/example/adviewer/ToastAdListener.java
 */

class GetJokesFromBackend extends AsyncTask<Void, Void, String> {
    private  MyApi myApiService = null;
    private static String LOG_TAG = "GetJokesBackend";

    ResultsListener listener; // callback method

    public void setOnResultsListener(ResultsListener listener) {
        this.listener = listener;
    }
    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            Log.e(LOG_TAG, "I/O ERROR on GetJokesFromBackend :"+e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (listener!=null)
            listener.onResultsSucceeded(result); // callback

    }
}

// if I use Pair<Context, String>... params then
//context = params[0].first;
//String name = params[0].second;

