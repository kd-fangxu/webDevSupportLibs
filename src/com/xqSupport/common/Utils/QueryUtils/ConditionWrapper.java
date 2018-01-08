package com.xqSupport.common.Utils.QueryUtils;

import java.util.ArrayList;
import java.util.List;

public class ConditionWrapper {
    public List<QueryCondition> getQueryConditionList() {
        return queryConditionList;
    }

    private List<QueryCondition> queryConditionList;

//    public List<QueryCondition> addCondition(QueryCondition condition) {
//        if (queryConditionList == null) {
//            queryConditionList = new ArrayList<QueryCondition>();
//        }
//        queryConditionList.add(condition);
//        return queryConditionList;
//    }

    public List<QueryCondition> addCondition(String columnName, String[] value, QueryCondition.Type type){
        if (queryConditionList == null) {
            queryConditionList = new ArrayList<QueryCondition>();
        }
        QueryCondition condition=new QueryCondition(columnName,value,type);
        queryConditionList.add(condition);
        return queryConditionList;
    }
}
