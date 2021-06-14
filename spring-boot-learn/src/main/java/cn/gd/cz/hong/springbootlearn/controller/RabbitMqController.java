package cn.gd.cz.hong.springbootlearn.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * rabbitmq 离子
 */
@RequestMapping("/rabbit")
@RestController
public class RabbitMqController {

    @Resource
    AmqpTemplate rabbitmqTemplate;

    @RequestMapping("/hello")
    public Map<String, String> send(String msg) {
        rabbitmqTemplate.convertAndSend("hong", msg);
        return new HashMap<String, String>() {{
            put("code", "200");
            put("msg", msg + "已发送");
        }};
    }
}
