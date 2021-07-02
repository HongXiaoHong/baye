package cn.gd.cz.hong.springbootmongodemo.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * 通知消息对象
 */
@Document(collection = "notify_msg")//集合名
public class NotifyMsg implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -8985545025018238754L;

    @Id
    String id;

    /**
     * 消息类型
     */
    @Indexed
    String notifyType;

    /**
     * 消息单号
     */
    @Indexed
    String notifyNo;

    /**
     * 消息通知日期
     */
    String notifyDate;

    /**
     * 消息体
     */
    @Field("notifyMsg")//可指定存储时的字段名
            String notifyMsg;

    /**
     * 创建时间
     */
    @CreatedDate
    Date gmtCreate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getNotifyNo() {
        return notifyNo;
    }

    public void setNotifyNo(String notifyNo) {
        this.notifyNo = notifyNo;
    }

    public String getNotifyDate() {
        return notifyDate;
    }

    public void setNotifyDate(String notifyDate) {
        this.notifyDate = notifyDate;
    }

    public String getNotifyMsg() {
        return notifyMsg;
    }

    public void setNotifyMsg(String notifyMsg) {
        this.notifyMsg = notifyMsg;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public String toString() {
        return "NotifyMsg{" +
                "id='" + id + '\'' +
                ", notifyType='" + notifyType + '\'' +
                ", notifyNo='" + notifyNo + '\'' +
                ", notifyDate='" + notifyDate + '\'' +
                ", notifyMsg='" + notifyMsg + '\'' +
                ", gmtCreate=" + gmtCreate +
                '}';
    }
}
