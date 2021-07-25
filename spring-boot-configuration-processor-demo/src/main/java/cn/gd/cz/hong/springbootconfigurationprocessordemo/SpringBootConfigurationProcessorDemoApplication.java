package cn.gd.cz.hong.springbootconfigurationprocessordemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class SpringBootConfigurationProcessorDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfigurationProcessorDemoApplication.class, args);
    }

}
