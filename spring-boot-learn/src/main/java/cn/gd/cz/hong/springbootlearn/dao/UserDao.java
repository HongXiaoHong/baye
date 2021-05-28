package cn.gd.cz.hong.springbootlearn.dao;

import cn.gd.cz.hong.springbootlearn.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 查询用户
 */
@Mapper
public interface UserDao {

    User selectById(String id);
    List<User> selectUsers(String id);
}
