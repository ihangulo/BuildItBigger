package com.hangulo.gradle.jokesource;

import java.util.Random;
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
 *    JokeSource.java
 *    Very simple joke library
 */
public class JokeSource {

    String myJokes[] = new String[7];

    private void setupJokes() {
        myJokes[0] = "Q: Why do programmers always mix up Halloween and Christmas?\n\n" +
                "A: Because Oct 31 == Dec 25! \n\n"+
                ">Source : http://stackoverflow.com/q/234075";
        myJokes[1] = "Q: how many programmers does it take to change a light bulb?\n" +
                "\n" +
                "A: none, that's a hardware problem\n\n" +
                ">>Source : http://stackoverflow.com/a/234100";

        myJokes[2] = "Richard Stallman, Linus Torvalds, and Donald Knuth engage in a discussion on whose impact on computer science was the greatest.\n" +
                "\n" +
                "Stallman: \"God told me I have programmed the best editor in the world!\"\n" +
                "\n" +
                "Torvalds: \"Well, God told me that I have programmed the best operating system in the world!\"\n" +
                "\n" +
                "Knuth: \"Wait, wait, I never said that.\"\n\n" +
                ">>Source : http://stackoverflow.com/a/798476";

        myJokes[3] = "Keyboard not found ... press F1 to continue \n\n" +
                ">>Source : http://stackoverflow.com/a/234134";

        myJokes[4] = "Q: What is the difference between a programmer and a non-programmer ?\n" +
                "\n" +
                 "A: The non-programmer thinks a kilobyte is 1000 bytes while a programmer is convinced that a kilometer is 1024 meters\n\n" +
                ">>Source : http://stackoverflow.com/a/237814";

        myJokes[5] = "Why do java programmers have to wear glasses?\n" +
                "\n" +
                "Because they don't see sharp(C#).\n\n" +
                ">>Source : http://stackoverflow.com/a/1009780";

        myJokes[6] = "There's no place like 127.0.0.1\n\n" +
                ">>Source : http://stackoverflow.com/a/260633";


    }
    public String getJoke() {

        setupJokes();

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(myJokes.length);

        return myJokes[randomInt];
    }


}
