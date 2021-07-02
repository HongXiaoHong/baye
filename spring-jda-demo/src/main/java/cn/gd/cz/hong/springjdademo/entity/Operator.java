package cn.gd.cz.hong.springjdademo.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 操作员实体
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Operator implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -3752294262021766827L;
    /**
     * 唯一标示
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;

    /**
     * 创建时间
     */
    @CreatedDate //自动创建
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @LastModifiedDate //有修改时 会自动时间
    private Date gmtModified;
}