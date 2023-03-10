package cn.gd.cz.hong.demo.plus.mybatis;

import cn.gd.cz.hong.demo.plus.mybatis.dao.UserMapper;
import cn.gd.cz.hong.demo.plus.mybatis.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
class MybatisApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        User user = userMapper.selectById(1);
        log.error("user: {}", user);
    }


    @Test
    void gen() {
        FastAutoGenerator.create("jdbc:sqlite:D:\\learn\\experiment\\Java\\learn\\mybatis\\sql\\db.sqlite", "", "")
                .globalConfig(builder -> {
                    builder.author("hong") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E:\\mybatis\\plus\\gen"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("cn.gd.cz.hong.demo.plus") // 设置父包名
                            .moduleName("mybatis") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E:\\mybatis\\plus\\gen")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("teacher,user"); // 设置需要生成的表名
                    //.addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }


}
