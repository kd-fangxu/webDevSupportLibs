package com.xqSupport.common.Dao.DaoExtend.treeDao;

import com.xqSupport.Entity.BaseLinkedTreeEntity;
import com.xqSupport.common.BaseResponse;
import com.xqSupport.common.Dao.IBaseDao;

import java.util.List;

/**
 * Created by xu on 2017/11/30.
 */
public interface ITreeDao<T extends BaseLinkedTreeEntity> extends IBaseDao<T> {


    BaseResponse deleteNode(Integer nodeId);

    T getEntityByNodeId(Integer nodeId, boolean isChildLoad);

    List<T> getEntitiesByFatherId(Integer fatherId);

    List<BaseLinkedTreeEntity.ZtreeNode> getZTreeNodeListByNodeId(Integer nodeId);

    List<BaseLinkedTreeEntity.ZtreeNode> geZTreeNodeListByFatherId(Integer fatherId, boolean isChildLoad);

//    ---------------tree-------------

    BaseResponse addNodeByFatherId(Integer fatherId, T node);

    List<T> getLeafNodesByFatherId(Integer fatherId);

    BaseResponse reName(Integer nodeId, String nodeName);

    /**
     * 重分配子节点
     *
     * @param currentNodeId 待移动节点
     * @param targetNodeId  参照物节点（目标节点）
     * @param action        next(放在参照物节点后)  prev（放在参照物节点前） inner （放在参照物节点下作为第一个子节点）
     * @return
     */
    BaseResponse reDistribute(Integer currentNodeId, Integer targetNodeId, String action);

    List<BaseLinkedTreeEntity.ZtreeNode> ConvertToZTreeNodeList(List<T> linkedTreeEntities);
}
