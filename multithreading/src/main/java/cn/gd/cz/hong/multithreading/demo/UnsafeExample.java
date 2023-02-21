package cn.gd.cz.hong.multithreading.demo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 不安全示例
 * 可以使用 Thread sleep方法放大问题的发生性
 * <p>
 * 1. 多个线程同时操作一个变量
 * 1.1. 多个人抢同一张票
 * 1.2. 多个人同时操作一个账户
 * 2. 使用不安全的集合类
 * 2.1 例如 ArrayList等
 */
@Slf4j
public class UnsafeExample {

    public static void main(String[] args) {
//        grabTrainTicket();
        System.out.println("-----------------------------");
//        withdrawMoney();
        assemblyTeam();

    }

    /**
     * 抢火车票
     */
    public static void grabTrainTicket() {
        Runnable grab = new Runnable() {
            int ticketNum = 10;

            @Override
            public void run() {
                while (ticketNum > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "抢到了第 " + ticketNum-- + "张票");
                }
            }
        };
        Thread you = new Thread(grab, "you");
        Thread me = new Thread(grab, "me");
        Thread him = new Thread(grab, "him");
        you.start();
        me.start();
        him.start();
    }/*
    husband看到的现在账户中存款: 100万
    网路延迟......
    wife看到的现在账户中存款: 100万
    网路延迟......
    husband取款前 现在账户中存款: 100万
    取款之后 现在账户中存款: 50万
    wife取款前 现在账户中存款: 50万
    取款之后 现在账户中存款: -50万
    *///:~

    /**
     * 取钱
     */
    public static void withdrawMoney() {
        @Data
        class Account {
            int money;

            public Account(int money) {
                this.money = money;
            }
        }
        Account account = new Account(100);

        class Withdraw implements Runnable {

            final Account account;
            final int subtraction;

            public Withdraw(Account account, int subtraction) {
                this.account = account;
                this.subtraction = subtraction;
            }

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "看到的现在账户中存款: " + account.getMoney() + "万");
                if (account.getMoney() > 0) {
                    try {
                        System.out.println("网路延迟......");
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "取款前 现在账户中存款: " + account.getMoney() + "万");
                    int resultMoney = account.money - subtraction;
                    account.setMoney(resultMoney);
                    System.out.println("取款之后 现在账户中存款: " + account.getMoney() + "万");
                }
            }
        }

        Runnable husbandWithdraw = new Withdraw(account, 50);
        Thread husband = new Thread(husbandWithdraw, "husband");
        Runnable wifeWithdraw = new Withdraw(account, 100);
        Thread wife = new Thread(wifeWithdraw, "wife");
        husband.start();
        wife.start();
    }/*
    him抢到了第 10张票
    me抢到了第 8张票
    you抢到了第 9张票
    you抢到了第 7张票
    him抢到了第 6张票
    me抢到了第 7张票
    you抢到了第 5张票
    him抢到了第 4张票
    me抢到了第 5张票
    me抢到了第 3张票
    you抢到了第 1张票
    him抢到了第 2张票
    *///:~

    /**
     * 集合队员
     * 摇 100 人 却只来了 96 人
     */
    public static void assemblyTeam() {
        List<String> team = new ArrayList<>();
        IntStream.range(0, 100).forEach(i -> {
            new Thread(() -> team.add(Math.random() + "")).start();
        });
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error("sleep", e);
        }
        System.out.println("现在队伍中有 " + team.size() + " 人");
    }/*
    现在队伍中有 96 人
    */

}
