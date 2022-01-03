package cn.gd.cz.hong.springbootredisdemo.crud;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * String 类型 测试
 */
@Slf4j
@SpringBootTest
public class StringTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private ValueOperations<String, Object> valueOpes;

    @PostConstruct
    public void init() {
        valueOpes = redisTemplate.opsForValue();
    }

    /**
     * 增删改查 测试
     */
    @Test
    public void test() {
        /*-------字符串基本操作---------*/
        // 设置
        valueOpes.set("hobby", "listen songs");
        // 获值
        log.error("get value -> hobby: {}", valueOpes.get("hobby"));
        // 获取长度
        log.error("STRLEN -> hobby: {}", valueOpes.size("hobby"));
        valueOpes.append("hobby", " and sing");
        // 追加
        log.error("append value -> hobby: {}", valueOpes.get("hobby"));
        /*-------整形自增自减---------*/
        valueOpes.set("likes", 0);
        // 自增 以及 步长
        log.error("incr -> likes: {}", valueOpes.increment("likes"));
        log.error("INCRBY -> likes: {}", valueOpes.increment("likes", 2));
        // 自减 以及 步长
        log.error("decr -> likes: {}", valueOpes.decrement("likes"));
        log.error("DECRBY -> likes: {}", valueOpes.decrement("likes", 2));
        /*-------字符串范围操作---------*/
        valueOpes.set("now", "2022-01-03");
        // 字符串截取
        log.error("GETRANGE -> now: {}", valueOpes.get("now", 0, 3));
        // 字符串替换
        valueOpes.set("now", "2099", 0);
        log.error("SETRANGE -> now: {}", valueOpes.get("now"));
        /*-------特殊的两个命令字---------*/
        // # setex (set with expire) # 设置过期时间
        valueOpes.set("apple-expired", "1", 8, TimeUnit.DAYS);
        // # setnx (set if not exist) # 不存在在设置 （在分布式锁中会常常使用！）
        valueOpes.setIfAbsent("apple-expired", "0");
        log.error("setnx -> apple-expired: {}", valueOpes.get("apple-expired"));
        /*-------批量操作---------*/
        HashMap<String, Object> singerAndSong = new HashMap<String, Object>() {
            private static final long serialVersionUID = 8896311068704058472L;

            {
                put("eason", "fuKua");
                put("likeqin", "yuebanxiaoyequ");
            }
        };
        valueOpes.multiSet(singerAndSong);
        /*-------getset---------*/
        // 百年孤寂
        log.error("getset -> favorite-song: {}", valueOpes.getAndSet("favorite-song", "bai-nian-gu-ji"));
        // 约定
        log.error("getset -> favorite-song: {}", valueOpes.getAndSet("favorite-song", "yue-ding"));
    }
/*
get value -> hobby: listen songs
STRLEN -> hobby: 14
append value -> hobby: listen songs
incr -> likes: 1
INCRBY -> likes: 3
decr -> likes: 2
DECRBY -> likes: 0
GETRANGE -> now: "202
SETRANGE -> now: 2099
setnx -> apple-expired: 1
getset -> favorite-song: null
getset -> favorite-song: bai-nian-gu-ji
* */
}
