package cn.gd.cz.hong.springbootlearn.param;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * spring-boot-configuration-processor 测试
 *
 * 不插入setter方法会报错
 *
 * Failed to bind properties under 'food' to cn.gd.cz.hong.springbootlearn.param.Food$$EnhancerBySpringCGLIB$$d46d9d23:
 *
 *     Property: food.drink
 *     Value: milk
 *     Origin: class path resource [application.yml] - 12:10
 *     Reason: No setter found for property: drink
 *
 * spring-boot-configuration-processor  没有配置之前
 * idea 会出现
 * spring boot configuration annotation processor not configured
 * 但是程序没有报错依旧可以正常使用
 *
 * 配置了 spring-boot-configuration-processor 之后错误消失
 * re-run spring boot configuration annotation processor to update generated metadata
 * 重新运行Spring Boot配置注释处理器以更新生成的元数据
 * 也就是说 spring-boot-configuration-processor 这个组件是为了帮助我们自己配置生成元数据
 * 可参考
 * <a href="https://blog.csdn.net/weixin_43328357/article/details/106993172">spring-boot-configuration-processor的真实作用</a>
 */
@Configuration
@ConfigurationProperties(prefix = "food")
public class Food {
    private String meet;
    private String fruit;
    private String vegetable;
    private String drink;

    public String getMeet() {
        return meet;
    }

    public void setMeet(String meet) {
        this.meet = meet;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public String getVegetable() {
        return vegetable;
    }

    public void setVegetable(String vegetable) {
        this.vegetable = vegetable;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    @Override
    public String toString() {
        return "Food{" +
                "meet='" + meet + '\'' +
                ", fruit='" + fruit + '\'' +
                ", vegetable='" + vegetable + '\'' +
                ", drink='" + drink + '\'' +
                '}';
    }
}
