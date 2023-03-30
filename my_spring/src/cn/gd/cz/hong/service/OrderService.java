package cn.gd.cz.hong.service;

import cn.gd.cz.hong.spring.BeanNameAware;
import cn.gd.cz.hong.spring.Component;
import cn.gd.cz.hong.spring.InitializingBean;

/**
 * 测试 Component
 */
@Component()
public class OrderService implements BeanNameAware, InitializingBean {
    private String beanName;
    private String x;

    public String getBeanName() {
        return beanName;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void afterPropertiesSet() {
        // 做一些不可描述的初始化动作
        x = "不可描述的初始化";
    }
}
