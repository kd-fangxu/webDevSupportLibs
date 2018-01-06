package com.xqSupport.Entity;

import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * Created by xu on 2017/11/29.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
    //除去逻辑极为清楚的比如 文章对应图片的关系类 1-N  N-N尽量少用对象属性
    //1-1也需根据业务需要控制 是否设置相关属性

    @Column(name = "createTime", nullable = true)
//    @org.hibernate.annotations.Generated(GenerationTime.ALWAYS)
    @org.hibernate.annotations.CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(name = "updateTime", nullable = true)
//    @org.hibernate.annotations.Generated(GenerationTime.INSERT)
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.UpdateTimestamp
    private Date updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer entityId;

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    private Date getCreateTime() {
        return createTime;
    }

    private void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    private Date getUpdateTime() {
        return updateTime;
    }

    private void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


}
