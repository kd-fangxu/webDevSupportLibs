package com.xqSupport.supportmodule.oss.entity;

import com.xqSupport.Entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by mac on 2018/1/21.
 */
@Entity
@Table(name = "Service_Oss_Source", schema = "xq", catalog = "")
public class OssSourceEntity extends BaseEntity {
    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

    private String sourceKey;

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    @Column(name = "sourceUrl", nullable = true, columnDefinition = "TEXT")
    private String sourceUrl;


}
