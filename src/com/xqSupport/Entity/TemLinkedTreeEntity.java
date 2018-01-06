package com.xqSupport.Entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by xu on 2017/11/30.
 */
@MappedSuperclass
//改注解下 无法设置object属性
public class TemLinkedTreeEntity extends BaseEntity {
    /**
     * 树级
     */
    @Column(name = "treeLevel", nullable = true)
    private Integer treeLevel;
    /**
     * 父id
     */
    @Column(name = "fatherId", nullable = true, columnDefinition = "INT default -1")
    private Integer fatherId;
    /**
     * 组内排序依据
     */
    @Column(name = "groupOrder", nullable = true)
    private Integer groupOrder;

    @Column(name = "lft", nullable = true)
    private Integer lft;

    public Integer getLft() {
        return lft;
    }

    public void setLft(Integer lft) {
        this.lft = lft;
    }

    public Integer getRgt() {
        return rgt;
    }

    public void setRgt(Integer rgt) {
        this.rgt = rgt;
    }


    @Column(name = "nodeName", nullable = true)
    private String nodeName;

    public Integer getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(Integer groupOrder) {
        this.groupOrder = groupOrder;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    @Column(name = "rgt", nullable = true)
    private Integer rgt;

    public Integer getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }
}
