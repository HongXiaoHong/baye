package cn.gd.cz.hong.springbootjdbcdemo.dao;

/**
 * 持久层接口
 */
public interface UserDao {
    String getUserName(String id);

    String getBirthday(String id);
}
