package cn.gd.cz.hong.service;

import cn.gd.cz.hong.entity.User;
import cn.gd.cz.hong.spring.Component;
import cn.gd.cz.hong.spring.Scope;

/**
 *
 */
@Component("userService")
@Scope("singleton")
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById() {
        return new User("1", "hong");
    }
}
