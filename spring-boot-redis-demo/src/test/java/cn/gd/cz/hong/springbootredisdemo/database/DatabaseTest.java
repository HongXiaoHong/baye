package cn.gd.cz.hong.springbootredisdemo.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 数据库相关测试
 */
@SpringBootTest
public class DatabaseTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void dbTest() {
        RedisConnection conn = redisTemplate.getConnectionFactory().getConnection();
        conn.flushDb();
    }

}
