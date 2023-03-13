package cn.gd.cz.hong.multithreading.demo.juc.producer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程就是一个单独的资源类 没有附属的操作
 * Lock 版本
 */
public class TicketLockPlus {
    private int num = 0;
    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    /**
     * synchronized 上锁三部曲 判断等待 业务 通知
     *
     * @throws InterruptedException
     */
    public void increase() throws InterruptedException {
        lock.lock();
        try {
            while (num != 0) { // 这里用 if 会有 虚假唤醒 的异常
                condition.await();
            }
            System.out.println("now the num is " + ++num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }


    public void decrease() throws InterruptedException {
        lock.lock();
        try {
            while (num == 0) {
                condition.await();
            }
            System.out.println("now the num is " + --num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
