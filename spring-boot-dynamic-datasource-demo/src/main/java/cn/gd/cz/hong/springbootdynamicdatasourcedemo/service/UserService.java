package cn.gd.cz.hong.springbootdynamicdatasourcedemo.service;

import com.baomidou.dynamic.datasource.annotation.DS;

import java.util.List;

/**
 * 用户服务层
 */
public interface UserService {
    List selectAll();

    @DS("slave_1")
    List selectByCondition();
}
