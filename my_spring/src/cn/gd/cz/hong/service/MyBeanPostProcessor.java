package cn.gd.cz.hong.service;

import cn.gd.cz.hong.spring.BeanPostProcessor;
import cn.gd.cz.hong.spring.Component;

import java.lang.reflect.Proxy;

/**
 * bean 后置处理器
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(String beanName, Object bean) {
        if ("orderService".equals(beanName)) {
            System.out.println("Before orderService: postProcessBeforeInitialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(String beanName, Object bean) {
        if ("orderService".equals(beanName)) {
            System.out.println("After orderService: postProcessAfterInitialization");
        }
        if ("userService".equals(beanName)) {
            System.out.println("After userService: postProcessAfterInitialization");
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), (proxy, method, args) -> {
                System.out.println("不好意思, 拦截到你的方法了呢");
                return method.invoke(bean, args);
            });
        }
        return bean;
    }
}/*
Before: postProcessBeforeInitialization
不可描述的初始化
After: postProcessAfterInitialization
*/
