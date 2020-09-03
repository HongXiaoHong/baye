package person.hong.learn.api.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: 洪晓鸿
 * @time: 2020/9/3 23:24
 */

@Component
//@Configuration //这个我这里虽然存在时能成功,不过我注释了也是可以的,这个是看网上有人写就跟着写上的
@PropertySource(value = {"classpath:/replaceReg.properties"}, encoding = "utf-8") //有的人是写了这个注解能成功,但是我这边不能有这个注解,有的话,就连编译都会报错
//@ConfigurationProperties(prefix = "tes")
public class MapTest {

    @Value("#{${tes.maps}}")
    private HashMap<String, String> maps;

    public HashMap<String, String> getMaps() {
        return maps;
    }

    public void setMaps(HashMap<String, String> maps) {
        this.maps = maps;
    }
}
