package cn.gd.cz.hong;

import cn.gd.cz.hong.service.UserService;
import cn.gd.cz.hong.spring.ApplicationContext;
import cn.gd.cz.hong.spring.Config;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Spring World!");
        ApplicationContext context = new ApplicationContext(Config.class);
        UserService userService = (UserService) context.getBean("userService");
        System.out.println(userService.getUserById());
    }
}