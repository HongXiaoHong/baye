package cn.gd.cz.hong.springbootdynamicdatasourcedemo.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户服务实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List selectAll() {
        return jdbcTemplate.queryForList("select * from member");
    }

    @Override
    @DS("slave_1")
    public List selectByCondition() {
        return jdbcTemplate.queryForList("select * from member where id < 10");
    }
}
