package cn.gd.cz.hong.multithreading.demo.juc.producer;

/**
 * 线程就是一个单独的资源类 没有附属的操作
 */
public class Ticket {
    private int num = 0;

    /**
     * synchronized 上锁三部曲 判断等待 业务 通知
     *
     * @throws InterruptedException
     */
    public synchronized void increase() throws InterruptedException {
        while (num != 0) { // 这里用 if 会有 虚假唤醒 的异常
            this.wait(1000);
        }
        System.out.println("now the num is " + ++num);
        this.notifyAll();
    }


    public synchronized void decrease() throws InterruptedException {
        while (num == 0) {
            this.wait(1000);
        }
        System.out.println("now the num is " + --num);
        this.notifyAll();
    }
}
