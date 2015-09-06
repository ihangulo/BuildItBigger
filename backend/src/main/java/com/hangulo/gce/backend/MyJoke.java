package com.hangulo.gce.backend;

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
 *    Myjoke.java
 *    The object model for the data we are sending through endpoints
 */
public class MyJoke {

    private String joke;

    public String getData() {
        return joke;
    }

    public void setData(String data) {
        joke = data;
    }
}