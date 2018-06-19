package com.think.util;

/**
 * 时钟工具类
 * 
 * @author Administrator
 *
 */
public final class StopWatch {
  private long startTime;
  private long endTime;

  public void start() {
    startTime = System.currentTimeMillis();
  }

  public void stop() {
    endTime = System.currentTimeMillis();
  }

  public long getMillTime() {
    long duration = endTime - startTime;
    return duration;
  }

  public void reset() {
    this.startTime = 0;
    this.endTime = 0;
  }
}
