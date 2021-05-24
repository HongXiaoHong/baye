package cn.gd.cz.hong.springbootlearn.runner;

import cn.gd.cz.hong.springbootlearn.param.Food;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 继承 ApplicationRunner 实现 容器启动做某些动作
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(MyApplicationRunner.class);

    @Autowired
    private Food food;

    @PostConstruct
    public void init() {
        logger.info(" food : {}", food);
    }

    @Override
    public void run(ApplicationArguments var1) {
        logger.info("MyApplicationRunner class will be execute when the project was started!");
    }
}