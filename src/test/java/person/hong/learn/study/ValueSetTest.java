package person.hong.learn.study;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ValueSetTest {

    @Autowired
    private ValueSet valueSet;

    @Test
    void testToString() {
        System.out.println(valueSet);
    }

    /*
    ----------------------------------------------------
    没有经过容器 直接创建 结果：
    ValueSetTest{hight=0, wight=null, species='null', osName='null', password='null'}
    经过容器 结果：
    ValueSetTest{hight=25, wight=65, species='human', osName='Windows 10', password='fuwit948tigoas'}
    ----------------------------------------------------
    * */
}