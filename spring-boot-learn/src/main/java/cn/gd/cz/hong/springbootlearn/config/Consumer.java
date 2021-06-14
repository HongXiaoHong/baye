package cn.gd.cz.hong.springbootlearn.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息队列处理
 */
@Component
@RabbitListener(queues = "hong")
public class Consumer {

    Logger log = LoggerFactory.getLogger(Consumer.class);

    /**
     * @param message
     * @RabbitHandler 指定消息的处理方法
     */
    @RabbitHandler
    public void process(String message) {
        log.info("接收的消息为: {}", message);
    }
}
