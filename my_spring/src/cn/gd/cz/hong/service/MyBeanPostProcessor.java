package cn.gd.cz.hong.service;

import cn.gd.cz.hong.spring.BeanPostProcessor;
import cn.gd.cz.hong.spring.Component;

/**
 * bean 后置处理器
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(String beanName, Object bean) {
        if ("orderService".equals(beanName)) {
            System.out.println("Before: postProcessBeforeInitialization");
        }
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(String beanName, Object bean) {
        if ("orderService".equals(beanName)) {
            System.out.println("After: postProcessAfterInitialization");
        }
        return null;
    }
}/*
Before: postProcessBeforeInitialization
不可描述的初始化
After: postProcessAfterInitialization
*/
