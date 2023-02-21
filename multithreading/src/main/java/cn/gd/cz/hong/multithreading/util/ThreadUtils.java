package cn.gd.cz.hong.multithreading.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程工具类
 */
public class ThreadUtils {

    public static ExecutorService getThreadPool() {
        return Executors.newFixedThreadPool(5);
    }
}
