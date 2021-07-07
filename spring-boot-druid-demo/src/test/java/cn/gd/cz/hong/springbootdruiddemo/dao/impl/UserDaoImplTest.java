package cn.gd.cz.hong.springbootdruiddemo.dao.impl;

import cn.gd.cz.hong.springbootdruiddemo.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDaoImplTest {
    @Resource
    UserDao userDao;

    @Test
    void getBirthday() {
        String birthday = userDao.getBirthday("1");
        System.out.println("birthday : " + birthday);
    }
}