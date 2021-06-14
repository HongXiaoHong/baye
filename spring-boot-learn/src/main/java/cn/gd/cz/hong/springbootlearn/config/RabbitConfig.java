package cn.gd.cz.hong.springbootlearn.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq 配置队列
 */
@Configuration
public class RabbitConfig {

    /**
     * 定义一个名为：hong 的队列
     *
     * @return org.springframework.amqp.core.Queue
     */
    @Bean
    public Queue hongQueue() {
        return new Queue("hong");
    }
}
