package person.hong.learn.api.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


//@RunWith(SpringRunner.class)
@SpringBootTest
class MapTestServiceTest {

    @Autowired
    MapTestService service;

    @Test
    void sout() {
        service.sout();
    }
}