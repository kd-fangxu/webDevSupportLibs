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

    /**
     * 获取填充子类的节点对象
     *
     * @param nodeId      节点id
     * @param isChildLoad 是否填充子节点
     * @return 返回节点对象
     */
    T getEntityByNodeId(Integer nodeId, boolean isChildLoad);

    /**
     * 根据fatherid获取叶子节点并递归填充下层所有叶子节点
     * 默认加载叶子节点
     *
     * @param fatherId
     * @return 返回层级树结构节点列表
     */
    List<T> getEntitiesByFatherId(Integer fatherId);

    /**
     * 根据fatherid获取叶子节点并递归填充下层所有叶子节点
     *
     * @param fatherId    父类id
     * @param isChildLoad 是否填充子节点
     * @return 返回节点列表
     */
    List<T> getEntitiesByFatherId(Integer fatherId, boolean isChildLoad);

//    /**
//     * 以nodeId为锚点返回Ztree格式（平铺）节点列表
//     *
//     * @param nodeId 节点id
//     * @return 平铺节点列表
//     */
//    List<BaseLinkedTreeEntity.ZtreeNode> getZTreeNodeListByNodeId(Integer nodeId);

//    /**
//     * 已fatherid为锚点返回ztree格式的平铺节点列表
//     *
//     * @param fatherId
//     * @return
//     */
//    List<BaseLinkedTreeEntity.ZtreeNode> geZTreeNodeListByFatherId(Integer fatherId, boolean isChildLoad);

//    ---------------treeActicon-------------

    /**
     * 添加节点
     *
     * @param fatherId 父节点id
     * @param node     节点实体
     * @return
     */
    BaseResponse addNodeByFatherId(Integer fatherId, T node);

    /**
     * 预排序查询根据父节点获取所有子节点  无层级结构
     *
     * @param fatherId
     * @return
     */
    List<T> getLeafNodesByFatherId(Integer fatherId);

    /**
     * 节点重命名
     *
     * @param nodeId
     * @param nodeName
     * @return
     */
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

    /**
     * 将节点队列转化为平铺的ztree格式node列表
     *
     * @param linkedTreeEntities
     * @return
     */
    List<BaseLinkedTreeEntity.ZtreeNode> ConvertToZTreeNodeList(List<T> linkedTreeEntities);
}
