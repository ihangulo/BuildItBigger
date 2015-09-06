package com.hangulo.gradle.builditbigger;

import android.test.InstrumentationTestCase;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
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
 *    JokeTest.java
  * step 4 : add Functional Testes
 *
 * Code to test that your Async task succesffuly retrieves a non-empty string.
  * http://stackoverflow.com/questions/2321829/android-asynctask-testing-with-android-test-framework
 */

public class JokeTest extends InstrumentationTestCase {

    private static boolean isNull=false;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

    }

    public void testSomeAsynTask () throws Throwable {
        // create  a signal to let us know when our task is done.
        final CountDownLatch signal = new CountDownLatch(1);


        // test if there is internet connection
        assertTrue("There is no internet connection. Please check first.",Utility.isNetworkAvailable(getInstrumentation().getContext()));

    /* Just create an in line implementation of an asynctask. Note this
     * would normally not be done, and is just here for completeness.
     * You would just use the task you want to unit test in your project.
     */
        final GetJokesFromBackend myTask = new GetJokesFromBackend() {

            @Override
            protected void onPostExecute(String result) {
                isNull =  (result==null);
                signal.countDown(); // set latch to 0 --> end
                //super.onPostExecute(result); // only test this session
            }
        };

        // Execute the async task on the UI thread! THIS IS KEY!
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                // myTask.execute(new Pair<Context, String>( getInstrumentation().getContext(), ""));
                myTask.execute();
            }
        });
    /* The testing thread will wait here until the UI thread releases it
     * above with the countDown() or 30 seconds passes and it times out.
     */
             signal.await(30, TimeUnit.SECONDS);
             assertFalse("Jokes are NULL. Please check Dev App server", isNull); // Testing result Not null
            // The task is done, and now you can assert some things!
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
