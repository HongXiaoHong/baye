package cn.gd.cz.hong.springbootredisdemo.crud;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

import javax.annotation.PostConstruct;

/**
 * redis
 * set 类型操作
 */
@Slf4j
@SpringBootTest
public class SetTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private SetOperations<String, Object> operations;

    @PostConstruct
    public void init() {
        operations = redisTemplate.opsForSet();
    }

    /**
     * 增删改查 测试
     */
    @Test
    public void test() {
        // 增加元素
        operations.add("hopes", "rich");
        operations.add("hopes", "stronger");
        // 查看set集合
        log.error("hopes: {}", operations.members("hopes"));
        // 查看set集合元素个数
        log.error("hopes length: {}", operations.size("hopes"));
        // 查看元素是否在set集合中
        log.error("{} in hopes: {}", "rich", operations.isMember("hopes", "rich"));
        operations.add("hopes", "hello");
        log.error("add hello hopes: {}", operations.members("hopes"));
        ;
        // 删除
        operations.remove("hopes", "hello");
        log.error("after remove hopes: {}", operations.members("hopes"));
        ;
        // 随机删除
        operations.pop("hopes");
        log.error("after random delete hopes: {}", operations.members("hopes"));
        ;
        operations.add("achieved", "hansom");
        operations.add("achieved", "rich");
        log.error("hopes: {}", operations.members("hopes"));
        log.error("achieved: {}", operations.members("achieved"));
        // 差集
        log.error("diff: {}", operations.difference("hopes", "achieved"));
        // 交集
        log.error("inter: {}", operations.intersect("hopes", "achieved"));
        // 合集
        log.error("union: {}", operations.union("hopes", "achieved"));
    }
/*
结果:
hopes: [stronger, rich]
hopes length: 2
rich in hopes: true
add hello hopes: [hello, stronger, rich]
after remove hopes: [stronger, rich]
after random delete hopes: [rich]
hopes: [rich]
achieved: [hansom, rich]
diff: []
inter: [rich]
union: [hansom, rich]
 */
}
