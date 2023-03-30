package cn.gd.cz.hong.service;

import cn.gd.cz.hong.entity.User;
import cn.gd.cz.hong.spring.BeanNameAware;
import cn.gd.cz.hong.spring.Component;
import cn.gd.cz.hong.spring.Scope;

/**
 * 测试 Component
 */
@Component()
public class OrderService implements BeanNameAware {
    private String beanName;

    public String getBeanName() {
        return beanName;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
