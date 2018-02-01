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

    @Deprecated
    public ConditionWrapper addCondition(String columnName, String[] value, QueryCondition.Type type) {
        addCondition(columnName, value, type, QueryCondition.ConditionReleationType.AND);
        return this;
    }

    @Deprecated

    public ConditionWrapper addCondition(String columnName, List<String> valueList, QueryCondition.Type type) {
        addCondition(columnName, (String[]) valueList.toArray(), type);
        return this;
    }

    @Deprecated

    public ConditionWrapper addCondition(String columnName, String valueStr, QueryCondition.Type type) {
        List<String> paramsList = new ArrayList<String>();
        paramsList.add(valueStr);
        addCondition(columnName, paramsList, type);
        return this;
    }

    @Deprecated

    public ConditionWrapper addCondition(String columnName, String[] value, QueryCondition.Type type, QueryCondition.ConditionReleationType conditionReleationType) {
        if (queryConditionList == null) {
            queryConditionList = new ArrayList<QueryCondition>();
        }
        QueryCondition condition = new QueryCondition(columnName, value, type, conditionReleationType);
        queryConditionList.add(condition);
        return this;
    }

    /**
     * and 关系 添加一个查询条件
     *
     * @param columnName
     * @param valueList
     * @param type
     * @return
     */
    public ConditionWrapper andCondition(String columnName, List<String> valueList, QueryCondition.Type type) {
        addCondition(columnName, (String[]) valueList.toArray(), type, QueryCondition.ConditionReleationType.AND);
        return this;
    }

    public ConditionWrapper andCondition(String columnName, String valueStr, QueryCondition.Type type) {
        addCondition(columnName, valueStr, type);
        return this;
    }

    /**
     * or 关系 添加有个查询条件
     *
     * @param columnName
     * @param valueList
     * @param type
     * @return
     */
    public ConditionWrapper orCondition(String columnName, List<String> valueList, QueryCondition.Type type) {
        addCondition(columnName, (String[]) valueList.toArray(new String[0]), type, QueryCondition.ConditionReleationType.OR);
        return this;
    }

    public ConditionWrapper orCondition(String columnName, String valueStr, QueryCondition.Type type) {
        List<String> paramsList = new ArrayList<String>();
        paramsList.add(valueStr);
        addCondition(columnName, (String[]) paramsList.toArray(new String[0]), type, QueryCondition.ConditionReleationType.OR);
        return this;
    }
}
