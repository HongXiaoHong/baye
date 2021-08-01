package cn.gd.cz.hong.jasyptspringbootstarterdemo.util;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 *
 */
@SpringBootTest
public class ConnectionUtilTest {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ConnectionUtilTest.class);
    @Autowired
    DataSource dataSource;

    @Test
    public void test() {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        String sql = "select name from member limit 0, 1";
        String result = template.queryForObject(sql, String.class);
        LOGGER.info("name: {};", result);
    }
}
