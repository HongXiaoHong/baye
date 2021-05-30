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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
}
