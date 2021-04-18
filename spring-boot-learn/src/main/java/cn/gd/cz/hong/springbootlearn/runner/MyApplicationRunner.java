package cn.gd.cz.hong.springbootlearn.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author hong
 */

@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments var1) {
        System.out.println("MyApplicationRunner class will be execute when the project was started!");
    }
}