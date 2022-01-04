package cn.gd.cz.hong.springbootredisdemo.crud;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * hash 类型测试
 */
@Slf4j
@SpringBootTest
public class HashTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, String, Object> hashOpes;

    @PostConstruct
    public void init() {
        hashOpes = redisTemplate.opsForHash();
    }

    /**
     * 增删改查 测试
     */
    @Test
    public void test() {
        // 设置值 hset
        hashOpes.put("user:1", "name", "hong");
        hashOpes.put("user:1", "sex", "male");
        // 获取值 hget
        log.error("hget user:1:name:{}", hashOpes.get("user:1", "name"));
        // 批量设置值 hmset
        Map<String, String> otherUserProps = ImmutableMap.<String, String>builder().put("age", "27").put("tall", "177").build();
        hashOpes.putAll("user:1", otherUserProps);
        // 获取所有的值 hgetall
        log.error("hgetall user:1:{}", hashOpes.entries("user:1"));
        // 删除对应的值 hdel
        hashOpes.delete("user:1", "tall");
        log.error("after hdel user:1:{}", hashOpes.entries("user:1"));
        // 获取长度 hlen
        log.error("hlen user:1:{}", hashOpes.size("user:1"));
        // 判断字段是否存在 HEXISTS
        log.error("HEXISTS user:1:name:{}", hashOpes.hasKey("user:1", "name"));
        // # 只获得所有field
        // # 只获得所有value
        log.error("hkeys user:1:keys:{}", hashOpes.keys("user:1"));
        log.error("hvals user:1:vals:{}", hashOpes.values("user:1"));
        // 这里操作value也可以像操作String类型一样 使用整型自增自减 使用setex setnx
        hashOpes.put("user:1", "weight", 75);
        hashOpes.increment("user:1", "weight", 1);
        log.error("after incrby user:1:{}", hashOpes.entries("user:1"));
    }
    /*
     hget user:1:name:hong
     hgetall user:1:{name=hong, sex=male, age=27, tall=177}
     after hdel user:1:{name=hong, sex=male, age=27}
     hlen user:1:3
     HEXISTS user:1:name:true
     hkeys user:1:keys:[name, sex, age]
     hvals user:1:vals:[hong, male, 27]
     after incrby user:1:{name=hong, sex=male, age=27, weight=76}
     */
}
