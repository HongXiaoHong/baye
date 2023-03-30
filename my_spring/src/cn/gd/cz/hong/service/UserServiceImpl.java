package cn.gd.cz.hong.service;

import cn.gd.cz.hong.entity.User;
import cn.gd.cz.hong.spring.Autowired;
import cn.gd.cz.hong.spring.Component;
import cn.gd.cz.hong.spring.Scope;

/**
 *
 */
@Component("userService")
@Scope("singleton")
public class UserServiceImpl implements UserService {

    @Autowired
    OrderService orderService;


    @Override
    public User getUserById() {
        /*
         * orderService: cn.gd.cz.hong.service.OrderService@6f496d9f
         * orderService.beanName: orderService
         */
        System.out.println("orderService: " + getOrderService());
        System.out.println("orderService.beanName: " + getOrderService().getBeanName());
        return new User("1", "hong");
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
