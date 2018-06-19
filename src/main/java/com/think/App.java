package com.think;


import com.think.hotswap.WatchDog;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        WatchDog watchDog = new WatchDog(null, null);
        watchDog.start();
    }
}
