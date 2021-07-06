package cn.gd.cz.hong.springbootjdbcdemo.dao.impl;

import cn.gd.cz.hong.springbootjdbcdemo.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserDaoImplTest {
    @Resource
    UserDao userdao;

    @Test
    void getUserName() {
        String userName = userdao.getUserName("1");
        System.out.println("userName : " + userName);
    }

    @Test
    void getBirthday() {
        String birthday = userdao.getBirthday("1");
        System.out.println("birthday : " + birthday);
    }
}