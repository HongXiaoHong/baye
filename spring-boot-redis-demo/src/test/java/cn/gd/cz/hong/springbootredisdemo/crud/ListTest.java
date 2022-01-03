package cn.gd.cz.hong.springbootredisdemo.crud;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;

/**
 * 数据类型 list
 */
@Slf4j
@SpringBootTest
public class ListTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private ListOperations<String, Object> listOpes;

    @PostConstruct
    public void init() {
        listOpes = redisTemplate.opsForList();
    }

    /**
     * 增删改查 测试
     */
    @Test
    public void test() {
        // 相当于队列 栈 阻塞队列
        // 可以从左边插入 也可以从右边插入
        listOpes.leftPush("favorite_singer", "eason");
        listOpes.leftPush("favorite_singer", "jack");
        listOpes.rightPush("favorite_singer", "ed");
        // 获取列表
        log.error("lrange: favorite_singer: {}", listOpes.range("favorite_singer", 0, -1));
        // 移除元素
        listOpes.leftPop("favorite_singer");
        log.error("lpop: favorite_singer: {}", listOpes.range("favorite_singer", 0, -1));
        // 根据下表获取元素
        log.error("lindex: favorite_singer: {}", listOpes.index("favorite_singer", 0));
        // 获取列表的长度
        log.error("llen: favorite_singer: {}", listOpes.size("favorite_singer"));
        // 移除固定的值
        listOpes.remove("favorite_singer", 1, "jack");
        log.error("lrem: favorite_singer: {}", listOpes.range("favorite_singer", 0, -1));
        // 截断 会改变原来的值
        listOpes.leftPush("favorite_singer", "hello");
        log.error("before ltrim: favorite_singer: {}", listOpes.range("favorite_singer", 0, -1));
        listOpes.trim("favorite_singer", 1, 2);
        log.error("after ltrim: favorite_singer: {}", listOpes.range("favorite_singer", 0, -1));
        // rpoplpush # 移除列表的最后一个元素，将他移动到新的列表中！
        listOpes.rightPush("favorite_singer", "mai");
        listOpes.rightPopAndLeftPush("favorite_singer", "not_favorite_singer");
        log.error("rpoplpush not_favorite_singer: {}", listOpes.range("not_favorite_singer", 0, -1));
        //lset 将列表中指定下标的值替换为另外一个值，更新操作
        listOpes.set("favorite_singer", 0, "jackson");
        log.error("rpoplpush favorite_singer: {}", listOpes.range("favorite_singer", 0, -1));
        // linsert # 将某个具体的value插入到列把你中某个元素的前面或者后面！
        listOpes.leftPush("favorite_singer", "jackson", "bob");
        log.error("linsert favorite_singer: {}", listOpes.range("favorite_singer", 0, -1));
    }
    /*
    lrange: favorite_singer: [jack, eason, ed]
    lpop: favorite_singer: [eason, ed]
    lindex: favorite_singer: eason
    llen: favorite_singer: 2
    lrem: favorite_singer: [eason, ed]
    before ltrim: favorite_singer: [hello, eason, ed]
    after ltrim: favorite_singer: [eason, ed]
    rpoplpush not_favorite_singer: [mai]
    rpoplpush favorite_singer: [jackson, ed]
    linsert favorite_singer: [bob, jackson, ed]
    * */
}
