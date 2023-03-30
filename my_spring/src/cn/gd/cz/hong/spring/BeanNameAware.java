package cn.gd.cz.hong.spring;

/**
 * 回调设置 容器中 bean 名字
 */
public interface BeanNameAware {
    void setBeanName(String beanName);
}
