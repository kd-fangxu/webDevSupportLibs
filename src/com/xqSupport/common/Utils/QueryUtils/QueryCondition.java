package com.xqSupport.common.Utils.QueryUtils;


import java.util.List;

public class QueryCondition {
    public enum Type {
        equal,
        unequal,
        in,
        notBlank,
        like,
        likeHeader,
        likeEnder
    }

    public enum ConditionReleationType {
        AND,
        OR;
    }

    private String columnName;
    private String[] value;
    private String releationResult;
    private Type type;

    public ConditionReleationType getConditionReleationType() {
        return conditionReleationType;
    }

    private ConditionReleationType conditionReleationType = ConditionReleationType.AND;//默认关系为AND

    public QueryCondition(String columnName, String[] value, Type type, ConditionReleationType conditionReleationType) {
        this.columnName = columnName;
        this.value = value;
        this.releationResult = releationResult;
        this.type = type;
        this.conditionReleationType = conditionReleationType;
    }

    public QueryCondition(String columnName, List<String> valuelist, Type type, ConditionReleationType conditionReleationType) {
        this.columnName = columnName;
        this.value = (String[]) valuelist.toArray();
        this.releationResult = releationResult;
        this.type = type;
        this.conditionReleationType = conditionReleationType;
    }

    public QueryCondition(String columnName, String[] value, Type type) {
        this.columnName = columnName;
        this.value = value;
        this.type = type;
    }

    public QueryCondition(String columnName, List<String> valuelist, Type type) {
        this.columnName = columnName;
        this.value = (String[]) valuelist.toArray();
        this.type = type;
    }

    public String[] getValue() {
        return value;
    }

    public void setValue(String[] value) {
        this.value = value;
    }


    public String getColumnName() {

        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * 返回拼好的查询条件
     *
     * @return
     */
    public String getReleationResult() {
        if (type == null || columnName == null) {
            return "";
        }
        String valueStr = valueTranformer();
        switch (this.type) {
            case equal:
                valueStr = "\'" + valueStr + "\'";
                releationResult = columnName + " = " + valueStr;
                break;
            case unequal:
                valueStr = "\'" + valueStr + "\'";
                releationResult = columnName + " != " + valueStr;
                break;
            case like:
                releationResult = columnName + " like \'%" + valueStr + "%\'";
                break;
            case likeHeader:
                releationResult = columnName + " like \'" + valueStr + "%\'";
                break;
            case likeEnder:
                releationResult = columnName + " like \'%" + valueStr + "\'";
                break;
            case in:
                releationResult = columnName + " in" + valueStr;//value必须为（xx,xx,...)形式后期加正则
                break;
            case notBlank:
                releationResult = columnName + " !=null or " + columnName + " !=\"\"";
                break;
        }
        return releationResult + "\n";
    }


    private String valueTranformer() {
        if (value != null && value.length > 0) {
            if (type == Type.in) {
                StringBuffer sb = new StringBuffer("");
                sb.append(" in (");
                for (String itemStr : value) {
                    if (itemStr.trim().length() > 0) {
                        sb.append(itemStr + ",");
                    }
                }
                sb.replace(sb.toString().length() - 1, sb.toString().length(), "");
                sb.append(")");
                return sb.toString();
            }

            return value[0];
        }

        return "";
    }
}
