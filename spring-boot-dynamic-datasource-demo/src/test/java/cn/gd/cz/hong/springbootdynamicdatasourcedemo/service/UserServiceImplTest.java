package cn.gd.cz.hong.springbootdynamicdatasourcedemo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    void selectAll() {
        List list = userService.selectAll();
        System.out.println(list);
    }

    @Test
    void selectByCondition() {
        List list = userService.selectByCondition();
        System.out.println(list);
    }
}