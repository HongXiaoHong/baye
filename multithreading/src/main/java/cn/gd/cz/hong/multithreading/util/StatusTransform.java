package cn.gd.cz.hong.multithreading.util;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程状态转换
 * New：新创建的线程，尚未执行；
 * Runnable：运行中的线程，正在执行run()方法的Java代码；
 * Blocked：运行中的线程，因为某些操作被阻塞而挂起；
 * Waiting：运行中的线程，因为某些操作在等待中；
 * Timed Waiting：运行中的线程，因为执行sleep()方法正在计时等待；
 * Terminated：线程已终止，因为run()方法执行完毕。
 * 当线程启动后，它可以在Runnable、Blocked、Waiting和Timed Waiting这几个状态之间切换，直到最后变成Terminated状态，线程终止。
 */
@Slf4j
public class StatusTransform {
    public static void joinThread() throws InterruptedException {
        log.info("start");
        // 用匿名类 创建一个线程名为 joinThread
        Thread thread = new Thread("joinThread") {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    log.info("Thread.sleep(10) has something error", e);
                }
                log.info("run it");
            }
        };
        thread.start();
        thread.join();
        log.info("end");
    }
}
