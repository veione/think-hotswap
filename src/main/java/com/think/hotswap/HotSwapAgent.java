package com.think.hotswap;

import java.lang.instrument.Instrumentation;

/**
 * 热替换代理类
 *
 * @author veione
 * @since 2018年6月15日11:03:16
 */
public final class HotSwapAgent {

    public static void premain(String args, Instrumentation inst) {
        WatchDog watchDog = new WatchDog(args, inst);
        watchDog.setUncaughtExceptionHandler(new HotSwapExceptionHandler());
        watchDog.start();
    }
}
