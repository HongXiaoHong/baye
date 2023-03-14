package cn.gd.cz.hong.demo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 */
/*@Configuration
@EnableWebSecurity*/
public class SecurityConfiguration {

    //    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
/*        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/tologin")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());*/
        // 定制请求的授权规则
        // 首页所有人可以访问
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");
        // 开启自动配置的登录功能
// /login 请求来到登录页
// /login?error 重定向到这里表示登录失败
        http.formLogin();
        return http.build();
    }

    //    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("hong")
                        .password("123456")
                        .roles("vip1", "vip2", "vip3")
                        .build();

        return new
                InMemoryUserDetailsManager(user);
    }
}