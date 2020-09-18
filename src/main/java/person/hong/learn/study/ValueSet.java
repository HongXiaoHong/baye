package person.hong.learn.study;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description:
 * @author: 洪晓鸿
 * @time: 2020/9/18 22:37
 */

@Component
public class ValueSet {
    /* 直接设置值 */
    @Value("25")
    private int hight;
    @Value("65")
    private Integer wight;
    @Value("human")
    private String species;
    // 设置系统文本内容
    @Value("classpath:mock/resume.txt")
    private Resource resume;
    // 设置url 调用后返回的值
    @Value("http://www.baidu.com")
    private Resource baidu;

    /* 使用#设置值 */
    // 1. 使用systemProperties[‘xxx’]获取系统参数
    @Value("#{systemProperties['os.name']}")
    private String osName;
    // 2. 调用系统方法
    @Value("#{T(Math).random() * 100.0}")
    private double waterContent;



    /* 使用$设置值 */
    @Value("${user.password}")
    private String password;

    @Override
    public String toString() {
        try {
            return "ValueSet{" +
                    "hight=" + hight +
                    ", wight=" + wight +
                    ", species='" + species + '\'' +
                    ", resume=" + IOUtils.toString(resume.getInputStream()) +
                    ", baidu=" + IOUtils.toString(baidu.getInputStream()) +
                    ", osName='" + osName + '\'' +
                    ", waterContent=" + waterContent +
                    ", password='" + password + '\'' +
                    '}';
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
