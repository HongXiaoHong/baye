package cn.gd.cz.hong.springbootdruiddemo.dao.impl;

import cn.gd.cz.hong.springbootdruiddemo.dao.UserDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 持久层
 */
@Component
public class UserDaoImpl implements UserDao {

    @Resource
    DataSource dataSource;


    @Override
    public String getBirthday(String id) {
        String sql = "SELECT t.birthday FROM user t where t.id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }
}
