package cn.gd.cz.hong.multithreading.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusTransformTest {

    /**
     * 调用了 线程的 join方法后 会等join执行完再执行主线程的方法
     */
    @Test
    void joinThread() {
        try {
            StatusTransform.joinThread();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }/*
    2021-09-07 23:17:33 [main][INFO][cn.gd.cz.hong.multithreading.util.StatusTransform][joinThread][18]-> start
2021-09-07 23:17:33 [joinThread][INFO][cn.gd.cz.hong.multithreading.util.StatusTransform][run][28]-> run it
2021-09-07 23:17:33 [main][INFO][cn.gd.cz.hong.multithreading.util.StatusTransform][joinThread][33]-> end
    */
}