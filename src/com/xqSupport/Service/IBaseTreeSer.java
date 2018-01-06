package com.xqSupport.Service;


import com.xqSupport.Entity.BaseLinkedTreeEntity;
import com.xqSupport.common.BaseResponse;

import java.util.List;

/**
 * Created by mac on 2018/1/4.
 */
public interface IBaseTreeSer {
    public BaseResponse addNode(Integer fatherId, BaseLinkedTreeEntity node);

    public BaseResponse deleteNode(Integer nodeId);

    public BaseResponse reNameNode(Integer nodeId, String nodeName);

    public List<BaseLinkedTreeEntity.ZtreeNode> getZtreeNodeList(Integer fatherId);

    public BaseResponse reDistribute(Integer currentNodeId, Integer targetNodeId, String action);
}
