package cn.gd.cz.hong.springbootmongodemo.service;

import cn.gd.cz.hong.springbootmongodemo.SpringBootMongoDemoApplication;
import cn.gd.cz.hong.springbootmongodemo.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = SpringBootMongoDemoApplication.class)
class CommentServiceTest {
    @Autowired
    CommentService service;

    @Test
    void findByParentId() {
       List<Comment> list = service.findByParentId(1);
        System.out.println("list: " + list);
    }
}