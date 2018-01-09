package com.xqSupport.common.Dao.DaoExtend.treeDao;

import com.xqSupport.Entity.BaseLinkedTreeEntity;
import com.xqSupport.common.BaseResponse;
import com.xqSupport.common.Dao.BaseDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xu on 2017/11/30.
 */
public abstract class TreeDaoImpl<T extends BaseLinkedTreeEntity> extends BaseDao<T> implements ITreeDao<T> {

    /**
     * 获取操作对象的类名
     *
     * @return
     */
    public abstract String getEntityClassName();

    /**
     * 预排序 为父节点添加子节点
     *
     * @param fatherId
     * @param node
     * @return
     */
    public BaseResponse addNodeByFatherId(Integer fatherId, T node) {
        String NodeName = getEntityClassName();
//        NodeName="NmSysMenu";
        if (fatherId == null || node == null) {
            return BaseResponse.create(0, "fatherid或node=null", "");
        }
//        1.获取父id
        T fatherNode = getById(fatherId);
        if (fatherNode == null) {
            //如果父节点不存在默认创建一个根节点
//         所有节点l r值统一+2
            executeUpdate("update " + NodeName + " node set node.lft=node.lft+2,node.rgt=node.rgt+2  where node.lft >= " + 0);
//         添加节点l值为0 r值为父1;
            T rootFatherNode = null;
            try {
                rootFatherNode = (T) node.getClass().newInstance();
                rootFatherNode.setFatherId(-1);
                rootFatherNode.setLft(0);
                rootFatherNode.setRgt(1);
                saveEntity(rootFatherNode);
                fatherId = rootFatherNode.getEntityId();
                fatherNode = rootFatherNode;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (fatherNode != null) {
            Integer fatherNodeLft = fatherNode.getLft();
//            2.获取父节点r值
            Integer fatherNodeRgt = fatherNode.getRgt();
            if (fatherNodeLft != null && fatherNodeRgt != null) {
//             3.更新所有l值大于当前节点l值得节点 l=l+2 ;
                executeUpdate("update " + NodeName + " node set node.lft=node.lft+2  where node.lft > " + fatherNodeLft);
//           4. 更新所有r值大于当前节点r值得节点 r = r + 2;
                executeUpdate("update " + NodeName + " node set node.rgt=node.rgt+2  where node.rgt > " + fatherNodeLft);
//           5. 添加节点l值为父r + 1 r值为父r + 2;
                node.setFatherId(fatherId);
                node.setLft(fatherNodeLft + 1);
                node.setRgt(fatherNodeLft + 2);
                saveEntity(node);
            }

        } else {
            return BaseResponse.create(0, "未找到fatherId=" + fatherId + "的节点", "");
        }
        return BaseResponse.create(1, "成功", "");
    }


    public void addNode(List<T> entities) {

    }

    public BaseResponse deleteNode(Integer nodeId) {


//        1.节点左侧不处理；
//        2.节点子孙节点 l=l-1 r=r-1 子孙fatherid=当前节点fatherId;
//        3.左节点数>当前左节点的所有节点 l=l-2;
//        4.右节点数>当前右节点数 r=r-2;


        T node = getById(nodeId);
        if (node != null) {
            String NodeName = getEntityClassName();
            Integer nodeLft = node.getLft();
            Integer nodeRgt = node.getRgt();

            if (nodeLft != null && nodeRgt != null) {

                //判断是否存在子节点  如果存在子节点 子节点父id继承现有节点父节点
                if (nodeRgt - nodeLft > 1) {

                    executeUpdate("update " + NodeName + " node set node.fatherId = " +
                            node.getFatherId() + " where node.fatherId = " + nodeId);

                }
                //step2

                executeUpdate("update " + NodeName + " node set node.lft=node.lft-1,node.rgt=node.rgt-1"
                        + " ,node.fatherId=" + node.getFatherId()
                        + " where node.lft > " + nodeLft
                        + " and node.rgt<" + nodeRgt);

                executeUpdate("update " + NodeName + " node set node.lft=node.lft-2  where node.lft > " + nodeRgt);
                executeUpdate("update " + NodeName + " node set node.rgt=node.rgt-2  where node.rgt > " + nodeRgt);
                delete(nodeId);
            }


        } else {
            return BaseResponse.create(0, "未找到nodeId=" + nodeId + "的节点", "");

        }
        return BaseResponse.create(1, "成功", "");
    }

    /**
     * 预排序根据父节点获取所有子节点  无层级结构
     *
     * @param fatherId
     * @return
     */
    public List<T> getLeafNodesByFatherId(Integer fatherId) {
        T fatherNode = getById(fatherId);
        List<T> objects = new ArrayList<T>();
        if (fatherNode != null) {
            String nodeName = getEntityClassName();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            List<T> list = findList("from " + nodeName + " node where node.lft  >" + fatherNode.getLft() + " and node.rgt<" + fatherNode.getRgt()
                    + "order by node.lft asc", paramMap);
            objects.addAll(list);
            return objects;
        } else if (fatherId < 0) {//父节点未找到  返回整数
            List<T> list = findList("from " + getEntityClassName() + " node where node.lft>=0" + "order by node.lft asc", new HashMap<String, Object>());
            objects.addAll(list);
//            return objects;
        }
        return objects;
    }

    public BaseResponse reName(Integer nodeId, String nodeName) {
        T entity = getById(nodeId);
        entity.setNodeName(nodeName);

        return BaseResponse.create(1, "修改完成", "");
    }

    public BaseResponse reDistribute(Integer currentNodeId, Integer targetNodeId, String action) {
        T currentNode = getById(currentNodeId);
        T targetNode;
        if (currentNode != null) {
            String NodeName = getEntityClassName();
            int cl = currentNode.getLft();
            int cr = currentNode.getRgt();

            //空出当前节点位置
            int c_readCount = cr - cl + 1;//访问当前节点及一下子节点的跳数

            executeUpdate("update " + NodeName + " node " +
                    "set node.lft=node.lft-" + c_readCount +
                    "  where node.lft >" + cl + "and node.fatherId!=" + currentNodeId);
            executeUpdate("update " + NodeName + " node " +
                    "set node.rgt=node.rgt-" + c_readCount +
                    "  where node.rgt >" + cr);
            //根据不同的策略和参考点  计算出位置点的左右值

            int finalL = 0;
            int finalR = 0;
            int deletaValue = 0;
            String aCase = action.toLowerCase();

            targetNode = getById(targetNodeId);//此处的targetnode已经和之前的targetNode rl值不同了
            int tl = targetNode.getLft();
            int tr = targetNode.getRgt();
            int preNodeR = 0;//要放置节点的前一跳数
            if (aCase.equals("next")) {
                finalL = tr + 1;
                finalR = finalL + c_readCount - 1;
                currentNode.setFatherId(targetNode.getFatherId());
                preNodeR = tr;
            } else if (aCase.equals("prev")) {
                finalR = tl - 1 + c_readCount;
                finalL = finalR - c_readCount + 1;
                currentNode.setFatherId(targetNode.getFatherId());
                preNodeR = tl - 1;
            } else {//inner
                finalL = tl + 1;
                finalR = finalL + c_readCount - 1;
                currentNode.setFatherId(targetNode.getEntityId());
                preNodeR = tl;
            }

            //这里得加上第三种情况
            //重分配之后的节点的funode改变了 xu

            deletaValue = finalL - cl;
            System.out.println("tN:" + targetNode.getNodeName() +
                    "action:" + action + "\n" +
                    "tl:" + tl + "  tr:" + tr +
                    "readCount=" + c_readCount + "\n" +
                    "FinalL=" + finalL + "\n" +
                    "FinalR=" + finalR + "");
            //为待节点 空出位置  节点的子节点不做改变
            executeUpdate("update " + NodeName + " node " +
                    "set node.lft=node.lft+" + c_readCount +
                    "  where node.lft>" + preNodeR +
                    " and node.fatherId!=" + currentNodeId +
                    " and node.id!=" + currentNodeId);
            executeUpdate("update " + NodeName + " node " +
                    "set node.rgt=node.rgt+" + c_readCount +
                    "  where node.rgt>" + preNodeR +
                    " and node.fatherId!=" + currentNodeId +
                    " and node.id!=" + currentNodeId);

//                获取到顺序变更值，所以该节点的子节点  l r 值加deletaValue
            currentNode.setLft(finalL);
            currentNode.setRgt(finalR);
            executeUpdate("update " + NodeName + " node " +
                    "set node.lft=node.lft+" + deletaValue +
                    ",node.rgt=node.rgt+" + deletaValue +
                    " where node.fatherId = " + currentNodeId);
            return BaseResponse.create(1, "结构重组完成", "");
        } else {
            return BaseResponse.create(0, "结构重组失败", "");
        }

    }

    /**
     * 获取填充子类的节点对象
     *
     * @param nodeId
     * @return
     */
    public T getEntityByNodeId(Integer nodeId, boolean isChildLoad) {
        T entity = getById(nodeId);
        if (isChildLoad && entity != null) {
            loadChildNode(entity);
        }
        return entity;
    }

    /**
     * 根据fatherid获取叶子节点并递归填充下层所有叶子节点
     * 默认加载叶子节点
     *
     * @param fatherId
     * @return
     */
    public List<T> getEntitiesByFatherId(Integer fatherId) {
        List<T> linkedTreeEntities = (List<T>) findByProperty("fatherId", fatherId);
        for (T entity : linkedTreeEntities) {
            if (entity != null) {
                loadChildNode(entity);
            }

        }
        return linkedTreeEntities;
    }

    public List<T> getEntitiesByFatherId(Integer fatherId, boolean isChildLoad) {
        List<T> linkedTreeEntities = (List<T>) findByProperty("fatherId", fatherId);
        if (isChildLoad) {
            for (T entity : linkedTreeEntities) {
                if (entity != null) {
                    loadChildNode(entity);
                }

            }
        }
        return linkedTreeEntities;
    }

    /**
     * 已nodeId为锚点返回Ztree格式节点列表
     *
     * @param nodeId
     * @return
     */
    public List<BaseLinkedTreeEntity.ZtreeNode> getZTreeNodeListByNodeId(Integer nodeId) {
        T entityByNodeId = getEntityByNodeId(nodeId, true);
        return entityByNodeId.ConvertToZTreeNodeList();
    }

    /**
     * 已fatherid为锚点返回ztree格式的节点列表
     *
     * @param fatherId
     * @param isChildLoad
     * @return
     */
    public List<BaseLinkedTreeEntity.ZtreeNode> geZTreeNodeListByFatherId(Integer fatherId, boolean isChildLoad) {
        List<T> linkedTreeEntities = getLeafNodesByFatherId(fatherId);
        return ConvertToZTreeNodeList(linkedTreeEntities);
    }


    public List<BaseLinkedTreeEntity.ZtreeNode> ConvertToZTreeNodeList(List<T> linkedTreeEntities) {
        List<BaseLinkedTreeEntity.ZtreeNode> treeList = new ArrayList<BaseLinkedTreeEntity.ZtreeNode>();
        for (T entity : linkedTreeEntities) {
            treeList.addAll(entity.ConvertToZTreeNodeList());
        }
        return treeList;
    }

    private void loadChildNode(T rootEntity) {
        List<T> linkedTreeEntities = (List<T>) findByProperty("fatherId", rootEntity.getEntityId());
        rootEntity.setChildEntities((List<BaseLinkedTreeEntity>) linkedTreeEntities);
        for (T treeEntity : linkedTreeEntities) {
            loadChildNode(treeEntity);
        }
    }


    private void loadChildNode(List<T> linkedTreeEntities) {
        for (T treeEntity : linkedTreeEntities) {
            loadChildNode(treeEntity);
        }
    }

    public void save(List<T> list) {

    }

    public void insert(List<T> list) {

    }

    public void delete(List<T> list) {

    }

    public void update(List<T> list) {

    }
}
