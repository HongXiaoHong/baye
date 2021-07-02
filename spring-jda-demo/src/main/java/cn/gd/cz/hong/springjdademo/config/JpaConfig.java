package cn.gd.cz.hong.springjdademo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * jpa配置
 * 实现了类似公共字段自动填充功能
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
