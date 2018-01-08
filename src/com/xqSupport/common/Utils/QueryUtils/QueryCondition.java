package com.xqSupport.common.Utils.QueryUtils;

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

    private String columnName;
    private String[] value;
    private String releationResult;
    private Type type;

    public QueryCondition(String columnName, String[] value, Type type) {
        this.columnName = columnName;
        this.value = value;
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
                valueStr="\'"+valueStr+"\'";
                releationResult = columnName + " = " + valueStr;
                break;
            case unequal:
                valueStr="\'"+valueStr+"\'";
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
