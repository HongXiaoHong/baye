package cn.gd.cz.hong;

import cn.gd.cz.hong.spring.ApplicationContext;
import cn.gd.cz.hong.spring.Config;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Spring World!");
        ApplicationContext context = new ApplicationContext(Config.class);
        System.out.println(context.getBean("userService"));
        ;
        System.out.println(context.getBean("userService"));
        ;
        System.out.println(context.getBean("userService"));
        ;
        System.out.println(context.getBean("userService"));
        ;
    }
}