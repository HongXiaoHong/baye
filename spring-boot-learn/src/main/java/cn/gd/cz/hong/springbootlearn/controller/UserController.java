package cn.gd.cz.hong.springbootlearn.controller;

import cn.gd.cz.hong.springbootlearn.dao.UserDao;
import cn.gd.cz.hong.springbootlearn.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 *
 */
@RestController
public class UserController {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserDao userDao;

    @PostConstruct
    public void init(){
        User user = userDao.selectById("1");
        LOGGER.info("user : {}", user);
    }
}
