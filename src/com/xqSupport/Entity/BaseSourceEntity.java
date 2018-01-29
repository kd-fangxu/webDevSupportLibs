package com.xqSupport.Entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by xu on 2017/11/30.
 */
@MappedSuperclass
public class BaseSourceEntity extends BaseEntity {
    @Column(name = "souceUrl", nullable = true, columnDefinition = "TEXT")
    private String souceUrl;
    @Column(nullable = true, columnDefinition = "INT default 0")
    private Integer groupOrder;

    public String getSouceUrl() {
        return souceUrl;
    }

    public void setSouceUrl(String souceUrl) {
        this.souceUrl = souceUrl;
    }

    public Integer getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(Integer groupOrder) {
        this.groupOrder = groupOrder;
    }
}

