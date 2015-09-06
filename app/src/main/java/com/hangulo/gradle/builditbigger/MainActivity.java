package com.hangulo.gradle.builditbigger;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hangulo.gradle.jokedisplay.JokeActivity;

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
 *    MainActivity.java
 *
 *
 */
public class MainActivity extends AppCompatActivity implements ResultsListener {

    public static final String LOG_TAG = "MainActivity.class";

    public boolean mFree;
    private ProgressBar mProgressCircle;

    Button mBtnJoke;
    static adMob myAdmob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mFree = getResources().getBoolean(R.bool.free_version); // is it free version?
        mProgressCircle = (ProgressBar) findViewById(R.id.progress_circle);

        if (mFree)
            myAdmob= new adMob(this);
        else
            myAdmob=null;

        mBtnJoke = (Button) findViewById(R.id.btn_getjoke);
        if(mBtnJoke!=null) {
            mBtnJoke.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // show Interstitial ad (only free version)
                    if (mFree && myAdmob!=null) {
                        if (!myAdmob.interstitialLoadedthenShow())
                            getJoke();
                    } else
                        getJoke();
                }
            });
        } else {
            Toast.makeText(this, R.string.ERROR_INTENT, Toast.LENGTH_SHORT).show(); // error message
            return;
        }


        // only free version, show ads --> Step 5: Add a Paid Flavor
        if(mFree && myAdmob != null) {
            myAdmob.showBannerAd();
            myAdmob.setupInterstitialAd();
        }

    }


    public void getJoke() {

        // check network status first
        if(Utility.isNetworkAvailable(this)) {

            if (mProgressCircle!=null) {
                mProgressCircle.setVisibility(View.VISIBLE); // show lading indicator
                mProgressCircle.bringToFront();


                mBtnJoke.setClickable(false);
            }

            GetJokesFromBackend myTask = new GetJokesFromBackend(); // fetch joke
            myTask.setOnResultsListener(this); // set callback method
            myTask.execute(); // previous Pair<this,string>

        }
        else
            Toast.makeText(this, getString(R.string.ERROR_NO_NETWORK), Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.hangulo.gradle.builditbigger.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // call back from GetJokesFromBackend class

    @Override
    public void onResultsSucceeded(String result) {

        if (result==null || result.length()==0) {
            Toast.makeText(this, R.string.ERROR_BACKEND_SERVER, Toast.LENGTH_SHORT).show();
            Log.e(LOG_TAG, "Something wrong returned from GetJokesFromBackend.java");
        } else
            launchJokeActivity(result);

        // remove loading circle & activate button
        mProgressCircle.setVisibility(View.INVISIBLE); // remove loading indicator
        mBtnJoke.setClickable(true);

    }

    // using jokedisplay library
    private void launchJokeActivity(String result){

        if (result == null) return; // invalid call

        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, result);

        // Check if there's a handler for the intent (from https://youtu.be/jQWB_-o1kz4?t=2m59s)
        if (intent.resolveActivity(this.getPackageManager()) != null)
            startActivity(intent);

        else {  // show error message
            Toast.makeText(this, getString(R.string.ERROR_INTENT), Toast.LENGTH_LONG).show();
            Log.e(LOG_TAG, "JokeActivity.class Intent error");
        }

    }

}
