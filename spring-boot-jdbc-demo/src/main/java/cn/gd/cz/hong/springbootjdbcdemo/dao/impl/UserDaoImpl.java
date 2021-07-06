package cn.gd.cz.hong.springbootjdbcdemo.dao.impl;

import cn.gd.cz.hong.springbootjdbcdemo.dao.UserDao;
import cn.gd.cz.hong.springbootjdbcdemo.dao.util.DbUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 持久层
 */
@Component
public class UserDaoImpl implements UserDao {

    @Resource
    DataSource dataSource;

    @Override
    public String getUserName(String id) {
        String userName = "";
        String sql = "SELECT t.* FROM user t where t.id = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement state = conn.prepareStatement(sql)) {
            state.setString(1, id);
            ResultSet resultSet = state.executeQuery();
            while (resultSet.next()) {
                userName = resultSet.getString("name");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userName;
    }

    @Override
    public String getBirthday(String id) {
        String sql = "SELECT t.birthday FROM user t where t.id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }
}
