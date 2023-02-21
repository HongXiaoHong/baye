package cn.gd.cz.hong.multithreading.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 生产者 与 消费者
 */
public class ProducerAndConsumer {
    public static void main(String[] args) {
        Basket basket = new Basket();

        new Farmer("农场主", basket).start();
        new Customer("张小丽", basket).start();
        new Customer("胡英俊", basket).start();
        new Customer("大耳朵图图", basket).start();
        new Customer("牛爷爷", basket).start();
        new Customer("小美", basket).start();
    }
}

class Farmer extends Thread {

    private Basket basket;

    public Farmer(String name, Basket basket) {
        super(name);
        this.basket = basket;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Apple apple = new Apple(i);
            System.out.println(Thread.currentThread().getName() + "生产了" + i + "号苹果");
            basket.push(apple);
        }
    }
}

class Customer extends Thread {
    private Basket basket;

    public Customer(String name, Basket basket) {
        super(name);
        this.basket = basket;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            Apple apple = basket.pop();
            System.out.println(Thread.currentThread().getName() + "买到" + apple.getId() + "号苹果");
        }
    }
}

/**
 * 牛顿的苹果
 */
@Data
@AllArgsConstructor
class Apple {
    int id;
}

/**
 * 篮子
 */
@Data
class Basket {
    Apple[] apples = new Apple[20];
    int index = 0;

    public synchronized void push(Apple apple) {
        if (index == apples.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        apples[index++] = apple;
        this.notifyAll();
    }

    public synchronized Apple pop() {
        while (index == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Apple soldApple = apples[--index];
        this.notifyAll();
        return soldApple;
    }
}