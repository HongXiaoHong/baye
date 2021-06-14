package cn.gd.cz.hong.springbootlearn.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//SpringBootTest 是springboot 用于测试的注解，可指定启动类或者测试环境等，这里直接默认。
//因为是mock测试，在实际开发过程中，可指定其测试启动时为随机端口，避免了不必要的端口冲突。
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//测试单一接口时 ，也可利用注解@WebMvcTest 进行单一测试
//@WebMvcTest(DemoController.class)
class TestParamControllerTest {

    //使用 WebMvcTest 时使用
    //@autowired mockMvc 是可自动注入的。
    //当直接使用SpringBootTest 会提示 注入失败  这里直接示例利用 MockMvcBuilders工具创建
    //@Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext wc;

    @BeforeEach
    public void beforeSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
    }

    @Test
    void simple() throws Exception {
        String id = "344698773532";
        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.get("/test/param/post/simple").param("id", id))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //断言 是否和预期相等
        System.out.println(result.getResponse().getContentAsString());

    }
}