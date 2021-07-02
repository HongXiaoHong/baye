package cn.gd.cz.hong.springbootmongodemo.service;

import cn.gd.cz.hong.springbootmongodemo.entity.NotifyMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MongoTemplate 访问实现
 */
@Service
class NotifyMsgService {

    @Autowired
    MongoTemplate mongoTemplate;

    public NotifyMsg saveNotifyMsg(NotifyMsg msg) {
        //使用 save和insert都可以进行插入
        //区别：当存在"_id"时
        //insert 插入已经存在的id时 会异常
        //save 则会进行更新
        //简单来说 save 就是不存在插入 存在更新
        mongoTemplate.insert(msg);
        mongoTemplate.save(msg);

        return msg;
    }

    public NotifyMsg findNotifyMsgByNo(String notifyNo) {
        //根据Criteria 改造查询条件
        Query query = new Query(Criteria.where("notifyNo").is(notifyNo));
        return mongoTemplate.findOne(query, NotifyMsg.class);
    }

    public List<NotifyMsg> findNotifyMsgByDate(String notifyDate) {
        //查找 notifyDate 根据Criteria 改造查询条件
        Query query = new Query(Criteria.where("notifyDate").is(notifyDate));
        return mongoTemplate.find(query, NotifyMsg.class);
    }

    public NotifyMsg delNotifyMsgById(String id) {
        //查找 id 根据Criteria 改造查询条件
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findAndRemove(query, NotifyMsg.class);
    }
}
