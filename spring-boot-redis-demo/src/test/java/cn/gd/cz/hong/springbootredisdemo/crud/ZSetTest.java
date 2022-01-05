package cn.gd.cz.hong.springbootredisdemo.crud;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import javax.annotation.PostConstruct;

/**
 * Zset（有序集合）
 */
@Slf4j
@SpringBootTest
public class ZSetTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private ZSetOperations<String, Object> zSetOpes;

    @PostConstruct
    public void init() {
        zSetOpes = redisTemplate.opsForZSet();
    }

    /**
     * 增删改查 测试
     */
    @Test
    public void test() {
        /* 添加的时候顺带加上权重 便于排序
        zadd myset 1 one # 添加一个值
        zadd myset 2 two 3 three # 添加多个值
        ZRANGE myset 0 -1
        * */
        zSetOpes.add("number", "one", 1);
        zSetOpes.add("number", "two", 2);
        zSetOpes.add("number", "three", 3);
        log.error("ZRANGE number: {}", zSetOpes.range("number", 0, -1));
        // 排序 从小到大！
        log.error("ZRANGEBYSCORE: number: {}", zSetOpes.rangeByScore("number", Double.MIN_VALUE, Double.MAX_VALUE));
        // 排序 从大到小！
        log.error("ZRANGEBYSCORE number: {}", zSetOpes.reverseRangeByScore("number", Double.MIN_VALUE, Double.MAX_VALUE));
        // 移除rem中的元素
        zSetOpes.remove("number", "three");
        // zcard 获取有序集合中的个数
        log.error("after zrem -> zcard number: {}", zSetOpes.size("number"));
        // 获取指定区间的成员数量！
        log.error("zcount number:1-1: {}", zSetOpes.count("number", 1, 1));
    }
    /*
     * ZRANGE number: [one, two, three]
     * ZRANGEBYSCORE: number: [one, two, three]
     * ZRANGEBYSCORE number: [three, two, one]
     * after zrem -> zcard number: 2
     * zcount number:1-1: 1
     */
}
