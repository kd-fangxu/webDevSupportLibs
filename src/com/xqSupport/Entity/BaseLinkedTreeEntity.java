package com.xqSupport.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xu on 2017/11/30.
 */
public abstract class BaseLinkedTreeEntity extends TemLinkedTreeEntity {
    private List<BaseLinkedTreeEntity> childEntities;

    public List<BaseLinkedTreeEntity> getChildEntities() {
        return childEntities;
    }

    public void setChildEntities(List<BaseLinkedTreeEntity> childEntities) {
        this.childEntities = childEntities;
    }


//    public abstract String ConvertZtreeNodeName();


    /**
     * 将树对象转为zTree控件可识别的json结构
     *
     * @return
     */
    public List<BaseLinkedTreeEntity.ZtreeNode> ConvertToZTreeNodeList() {
        List<ZtreeNode> modeList = new ArrayList<ZtreeNode>();
        ZtreeNode node = new ZtreeNode(this);
        modeList.add(node);
        if (this.childEntities != null && this.childEntities.size() > 0) {
            loadChildNode(modeList, this.childEntities);
        }
        return modeList;
    }

    private void loadChildNode(List<ZtreeNode> modeList, List<BaseLinkedTreeEntity> mChildEntities) {
        for (BaseLinkedTreeEntity entity : mChildEntities) {
            modeList.add(new ZtreeNode(entity));
            if (entity.childEntities != null && entity.childEntities.size() > 0) {
                loadChildNode(modeList, entity.childEntities);
            }
        }
    }

    public class ZtreeNode {
        public ZtreeNode(BaseLinkedTreeEntity entity) {
            this.id = entity.getEntityId();
            this.pId = entity.getFatherId();
            this.name = entity.getNodeName();
        }

        //非数据库字段  用于显示zTree节点
//    {id: 1, pId: 0, name: "父节点1 - 展开", open: true},
        private Integer id;
        private Integer pId;
        private String name;
        private boolean open;
        private boolean isParent;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getpId() {
            return pId;
        }

        public void setpId(Integer pId) {
            this.pId = pId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isOpen() {
            return open;
        }

        public void setOpen(boolean open) {
            this.open = open;
        }

        public boolean isParent() {
            return isParent;
        }

        public void setParent(boolean parent) {
            isParent = parent;
        }
    }
}
