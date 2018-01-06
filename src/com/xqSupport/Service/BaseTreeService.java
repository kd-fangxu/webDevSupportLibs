package com.xqSupport.Service;


import com.xqSupport.Entity.BaseLinkedTreeEntity;
import com.xqSupport.common.BaseResponse;
import com.xqSupport.common.Dao.DaoExtend.treeDao.TreeDaoImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * Created by mac on 2018/1/4.
 */
@Transactional
public abstract class BaseTreeService extends BaseService implements IBaseTreeSer {
//    private Class currentTreeEntityClass;

    public abstract Class getCurrentTreeEntityClass();

    /**
     * 根据class返回封装树基本错做的treedao实例
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public <T extends BaseLinkedTreeEntity> TreeDaoImpl<T> getTreeDao(final Class<T> tClass) {
        if (daoMap.get(tClass.getName()) != null) {
            return (TreeDaoImpl<T>) daoMap.get(tClass.getName());
        } else {
            TreeDaoImpl<T> treeDao = new TreeDaoImpl<T>() {
                @Override
                public String getEntityClassName() {
                    return tClass.getName();
                }
            };

            treeDao.setSessionFactory0(sessionFactory.getObject());
            treeDao.entityClazz = tClass;//这个不设置entityClazz未null 但是spring加载时没问题  应该是运行时编译时的问题
            String key = "treeDao" + tClass.getName();
            daoMap.put(key, treeDao);
            return (TreeDaoImpl<T>) daoMap.get(key);
        }

    }


    public BaseResponse addNode(Integer fatherId, BaseLinkedTreeEntity node) {

        return getTreeDao(getCurrentTreeEntityClass())
                .addNodeByFatherId(fatherId, node);
    }

    public BaseResponse deleteNode(Integer nodeId) {
        return getTreeDao(getCurrentTreeEntityClass())
                .deleteNode(nodeId);
    }

    public BaseResponse reNameNode(Integer nodeId, String nodeName) {
        return getTreeDao(getCurrentTreeEntityClass())
                .reName(nodeId, nodeName);
    }

    public List<BaseLinkedTreeEntity.ZtreeNode> getZtreeNodeList(Integer fatherId) {
        return getTreeDao(getCurrentTreeEntityClass()).geZTreeNodeListByFatherId(fatherId, true);
//        return getTreeDao(getCurrentTreeEntityClass())
    }

    public BaseResponse reDistribute(Integer currentNodeId, Integer targetNodeId, String action) {
        return getTreeDao(getCurrentTreeEntityClass())
                .reDistribute(currentNodeId, targetNodeId, action);
    }
}
