package com.xqSupport.common.Utils.QueryUtils;

import java.util.ArrayList;
import java.util.List;

public class ConditionWrapper {
    public List<QueryCondition> getQueryConditionList() {
        return queryConditionList;
    }

    public void setQueryConditionList(List<QueryCondition> queryConditionList) {
        this.queryConditionList = queryConditionList;
    }

    private List<QueryCondition> queryConditionList;

//    public List<QueryCondition> addCondition(QueryCondition condition) {
//        if (queryConditionList == null) {
//            queryConditionList = new ArrayList<QueryCondition>();
//        }
//        queryConditionList.add(condition);
//        return queryConditionList;
//    }

    public ConditionWrapper addCondition(String columnName, String[] value, QueryCondition.Type type) {
        if (queryConditionList == null) {
            queryConditionList = new ArrayList<QueryCondition>();
        }
        QueryCondition condition = new QueryCondition(columnName, value, type);
        queryConditionList.add(condition);
        return this;
    }

    public ConditionWrapper addCondition(String columnName, List<String> valueList, QueryCondition.Type type) {
        if (queryConditionList == null) {
            queryConditionList = new ArrayList<QueryCondition>();
        }
        QueryCondition condition = new QueryCondition(columnName, valueList, type);
        queryConditionList.add(condition);
        return this;
    }

    public ConditionWrapper addCondition(String columnName, String valueStr, QueryCondition.Type type) {
        if (queryConditionList == null) {
            queryConditionList = new ArrayList<QueryCondition>();
        }
        List<String> paramsList = new ArrayList<String>();
        paramsList.add(valueStr);
        QueryCondition condition = new QueryCondition(columnName, paramsList, type);
        queryConditionList.add(condition);
        return this;
    }

}
