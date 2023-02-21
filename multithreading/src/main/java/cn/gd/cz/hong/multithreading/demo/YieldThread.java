package cn.gd.cz.hong.multithreading.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * yield 礼让线程
 * 让进程进入就绪状态
 * 调不调用 看CPU心情
 */
@Slf4j
public class YieldThread {

    /**
     * 用log记录可能出现意想不到的结果
     * starta
     * startb
     * endb
     * enda
     */
    @Test
    public void test() {
        Runnable run = () -> {
//           log.error("thread: {}, start", Thread.currentThread().getName());
            System.out.println("start" + Thread.currentThread().getName());
            Thread.yield();
            System.out.println("end" + Thread.currentThread().getName());
//           log.error("thread: {}, end", Thread.currentThread().getName());
        };

        new Thread(run, "a").start();
        new Thread(run, "b").start();
    }
}
