package cn.gd.cz.hong.multithreading.demo.juc.producer;

public class CosummerAndProducerTest {
    public static void main(String[] args) {
//        Ticket ticket = new Ticket(); // synchronized 实现版
        TicketLockPlus ticket = new TicketLockPlus();

        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                try {
                    ticket.increase();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                try {
                    ticket.decrease();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "B").start();
    }
}