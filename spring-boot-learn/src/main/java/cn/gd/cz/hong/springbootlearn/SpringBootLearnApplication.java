package cn.gd.cz.hong.springbootlearn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hong
 */
@MapperScan("cn.gd.cz.hong.springbootlearn.mapper")
@SpringBootApplication
public class SpringBootLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLearnApplication.class, args);
	}

}
