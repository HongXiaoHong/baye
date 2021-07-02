package cn.gd.cz.hong.springbootmongodemo.service;

import cn.gd.cz.hong.springbootmongodemo.SpringBootMongoDemoApplication;
import cn.gd.cz.hong.springbootmongodemo.entity.NotifyMsg;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = SpringBootMongoDemoApplication.class)
class NotifyMsgServiceTest {

    @Autowired
    NotifyMsgService notifyMsgService;

    @Test
    void saveNotifyMsg() {
        NotifyMsg msg = new NotifyMsg();
        msg.setId("1");
        msg.setNotifyType("song");
        msg.setNotifyMsg("囚鸟");
        msg.setNotifyNo("10000");
        msg.setNotifyDate("2021-07-02");
        notifyMsgService.saveNotifyMsg(msg);
    }

    @Test
    void findNotifyMsgByNo() {
        NotifyMsg notifyMsgByNo = notifyMsgService.findNotifyMsgByNo("10000");
        System.out.println(notifyMsgByNo);
    }

    @Test
    void findNotifyMsgByDate() {
        List<NotifyMsg> notifyMsgByDate = notifyMsgService.findNotifyMsgByDate("2021-07-02");
        System.out.println(Arrays.toString(notifyMsgByDate.toArray()));
    }

    @Test
    void delNotifyMsgById() {
        NotifyMsg notifyMsg = notifyMsgService.delNotifyMsgById("1");
        System.out.println(notifyMsg);
    }
}