package cn.gd.cz.hong.springbootmongodemo.dao;

import cn.gd.cz.hong.springbootmongodemo.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    //方法名根据已有字段来设置，Mongo会提示，拼写错误则无法使用
    //第一个参数是查询条件，第二个是分页
    List<Comment> findByParentId(int parentId);
}