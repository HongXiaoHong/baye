package cn.gd.cz.hong.springbootconfigurationprocessordemo.config;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ThirdPartyInterfacePropTest {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ThirdPartyInterfacePropTest.class);

    @Autowired
    ThirdPartyInterfaceProp partyInterfaceProp;

    @Test
    public void test() {
        LOGGER.info("partyInterfaceProp: {}", partyInterfaceProp);
    }


}