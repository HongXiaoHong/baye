package cn.gd.cz.hong.springbootlearn.dao;

import cn.gd.cz.hong.springbootlearn.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 查询用户
 */
@Mapper
public interface UserDao {

    User selectById(String id);
}
