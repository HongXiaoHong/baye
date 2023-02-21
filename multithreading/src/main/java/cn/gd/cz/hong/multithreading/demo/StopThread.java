package cn.gd.cz.hong.multithreading.demo;

import cn.gd.cz.hong.multithreading.util.ThreadUtils;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;

/**
 * 停止线程示例
 * 使用标志位 不用stop方法停止
 */
@Slf4j
public class StopThread {

    private boolean isRun = true;

    public void stop() {
        this.isRun = false;
    }

    /**
     * 启动一个带有标志位的线程
     */
    public void createThread() {
        ExecutorService threadPool = ThreadUtils.getThreadPool();
        Runnable run = () -> {
            int distance = 0;
            while (isRun) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    log.error("sleep", e);
                }
                Message message = StringFormatterMessageFactory.INSTANCE.newMessage("跑步中, 已经跑了%s米", distance++);
                System.out.println(message.getFormattedMessage());
            }
        };
        threadPool.submit(run);
    }

    @Test
    public void test() {
        createThread();
        for (int i = 0; i < 10; i++) {
//            System.out.println("----------" + i + "--------");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("sleep", e);
            }
            if (i == 1) {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++end");
                stop();
            }
        }
    }
}
