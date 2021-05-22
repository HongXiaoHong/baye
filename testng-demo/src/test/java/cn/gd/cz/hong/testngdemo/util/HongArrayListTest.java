package cn.gd.cz.hong.testngdemo.util;


import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * 首先要引入jar包 版本自定
 *
 *
 <dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-test</artifactId>
 <scope>test</scope>
 </dependency>
 <dependency>
 <groupId>org.testng</groupId>
 <artifactId>testng</artifactId>
 <version>7.4.0</version>
 </dependency>
 * testng 的类需要时public 不然没有办法测试
 */
public class HongArrayListTest {

    @Test
    void add() {
        HongArrayList list = new HongArrayList();
        list.add("man");
        Assert.assertEquals(list.get(0), "man");
    }
}