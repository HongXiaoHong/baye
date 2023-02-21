package cn.gd.cz.hong.multithreading.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * join 插队
 * 相当于 vip 先做事 其他人才可以继续做
 */
@Slf4j
public class JoinThread {

    /**
     * 用log记录可能出现意想不到的结果
     * starta
     * startb
     * endb
     * enda
     */
    @SneakyThrows
    @Test
    public void test() {
        Runnable run = () -> {
            System.out.println(Thread.currentThread().getName() + " is coming, serve first");
            for (int i = 0; i < 1000; i++) {
                System.out.println(" =--^^()_^^%%%&()^^%$%^^^ " + i + " =--^^()_^^%%%&()^^%$%^^^ ");
            }
        };
        Thread vip = new Thread(run, "vip");
        vip.start();

        for (int i = 0; i < 600; i++) {
            if (i == 300) {
                vip.join();
            }
            System.out.println("银行业务通道 main" + i);
        }
    }
}
