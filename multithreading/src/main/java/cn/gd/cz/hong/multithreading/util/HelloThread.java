package cn.gd.cz.hong.multithreading.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 第一节 带你创建线程
 */
@Slf4j
public class HelloThread {


    public static void main(String[] args) {
        log.info("hello thread start");
        // 用匿名类 创建一个线程名为
        new Thread("线程名") {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    log.info("Thread.sleep(10) has something error", e);
                }
                log.info("run it");
            }
        }.start();
        log.info("hello thread end");
    }//:)
    /*
2021-09-07 22:00:26 [main][INFO][cn.gd.cz.hong.multithreading.util.HelloThread][main][12]-> hello thread start
2021-09-07 22:00:26 [main][INFO][cn.gd.cz.hong.multithreading.util.HelloThread][main][25]-> hello thread end
2021-09-07 22:00:26 [线程名][INFO][cn.gd.cz.hong.multithreading.util.HelloThread][run][22]-> run it
     */

    @Test
    public void create() {
        Runnable run = () -> System.out.println("running ");
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        threadPool.submit(run);
    }
}
