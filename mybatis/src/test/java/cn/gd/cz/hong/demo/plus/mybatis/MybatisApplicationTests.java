package cn.gd.cz.hong.demo.plus.mybatis;

import cn.gd.cz.hong.demo.plus.mybatis.dao.UserMapper;
import cn.gd.cz.hong.demo.plus.mybatis.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class MybatisApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        User user = userMapper.selectById(1);
        log.error("user: {}", user);
    }

}
