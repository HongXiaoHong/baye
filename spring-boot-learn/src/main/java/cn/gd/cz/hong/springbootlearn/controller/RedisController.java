package cn.gd.cz.hong.springbootlearn.controller;

import cn.gd.cz.hong.springbootlearn.entity.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 *
 */
@Api
@RestController
@RequestMapping("/redis")
public class RedisController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisController.class);
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    @ApiOperation("add role to redis")
    @PutMapping("/addRole")
    public Map<String, Object> addRole(@RequestBody Role role) {
        Map<String, Object> result = new HashMap<String, Object>() {{
            put("code", "200");
            put("message", "success");
        }};
        Role roleFromCache = (Role) redisTemplate.opsForValue().get("role_" + role.getRoleId());
        LOGGER.info("role : {}", roleFromCache);
        if (Objects.isNull(roleFromCache)) {
            redisTemplate.opsForValue().set("role_" + role.getRoleId(), role);
            LOGGER.info("add role to redis");
        }
        return result;
    }

    @ApiOperation("test")
    @PutMapping("/test")
    public Map<String, Object> test() {
        Map<String, Object> result = new HashMap<String, Object>() {{
            put("code", "200");
            put("message", "success");
        }};
//        List<String> list1 = new ArrayList<String>();
//        list1.add("a1");
//        list1.add("a2");
//        list1.add("a3");
//
//        List<String> list2 = new ArrayList<String>();
//        list2.add("b1");
//        list2.add("b2");
//        list2.add("b3");
//        redisTemplate.opsForList().leftPush("listkey1", list2);
//        redisTemplate.opsForList().rightPush("listkey2", list1);
//        List<String> resultList1 = (List<String>) redisTemplate.opsForList().range("listkey1", 0, 2);
//        List<String> resultList2 = (List<String>) redisTemplate.opsForList().rightPop("listkey2");
//        System.out.println("resultList1:" + resultList1);
//        System.out.println("resultList2:" + resultList2);

        List<Role> roles = new LinkedList<Role>() {{
            add(new Role(2, "simple"));
            add(new Role(3, "special"));
        }};
        redisTemplate.opsForValue().set("roles", roles);
        return result;
    }
}
