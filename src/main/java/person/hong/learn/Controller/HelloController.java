package person.hong.learn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import person.hong.learn.study.ValueSet;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 洪晓鸿
 * @time: 2020/8/30 13:59
 */

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    ValueSet valueSet;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/first")
    public String hello() {
        List<Map<String, Object>> test = test();
        System.out.println(test);
        System.out.println(valueSet);
        return "hello spring";
    }

    public List<Map<String, Object>> test() {
        String sql = "select * from t_mime";
        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
        return list;
    }
}
