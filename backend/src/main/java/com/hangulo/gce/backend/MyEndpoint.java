/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.hangulo.gce.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import javax.inject.Named;
import com.hangulo.gradle.jokesource.JokeSource;

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
 *    MyEndpoint.java
 *     An endpoint class we are exposing
 *     https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
 */

@Api(name = "myApi", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend.gce.hangulo.com", ownerName = "backend.gce.hangulo.com", packagePath = ""))
public class MyEndpoint {


    // getJoke Api : take from jokesource (library)
    @ApiMethod(name = "getJoke")
    public MyJoke getJoke() {
        MyJoke response = new MyJoke();

        // takes from joke source --> from app engine
        JokeSource newJoke = new JokeSource();
        response.setData(newJoke.getJoke());

        return response;
    }
    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyJoke sayHi(@Named("name") String name) {
        MyJoke response = new MyJoke();
        response.setData("Hi, " + name);
        return response;
    }




////takes from joke source --> write app engine
//
//    @ApiMethod(name="setupJokes")
//    public void setupJokes() {
//        JokeSource myJoke = new JokeSource();
//        // here
//    }




}
