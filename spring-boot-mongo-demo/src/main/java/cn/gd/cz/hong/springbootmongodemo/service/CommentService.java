package cn.gd.cz.hong.springbootmongodemo.service;

import cn.gd.cz.hong.springbootmongodemo.dao.CommentRepository;
import cn.gd.cz.hong.springbootmongodemo.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论服务
 */
@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> findByParentId(int parentId) {
        return commentRepository.findByParentId(parentId);
    }

}
