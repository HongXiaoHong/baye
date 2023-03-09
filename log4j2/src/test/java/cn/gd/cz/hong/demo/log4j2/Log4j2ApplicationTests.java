package cn.gd.cz.hong.demo.log4j2;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Log4j2ApplicationTests {

    private final Logger LOGGER = LoggerFactory.getLogger(Log4j2ApplicationTests.class);

    @Test
    void contextLoads() {
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");
    }

}
