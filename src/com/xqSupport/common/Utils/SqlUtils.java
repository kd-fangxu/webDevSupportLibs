package com.xqSupport.common.Utils;

import com.xqSupport.common.Utils.QueryUtils.ConditionWrapper;
import com.xqSupport.common.Utils.QueryUtils.QueryCondition;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by xu on 2017/8/4.
 */
public class SqlUtils {


    public static String ConvertToWhereCondition(List<QueryCondition> conditionList) {
        ConditionWrapper wrapper = new ConditionWrapper();
        wrapper.setQueryConditionList(conditionList);
        return ConvertToWhereCondition(wrapper);
    }

    public static String ConvertToWhereCondition(ConditionWrapper wrapper) {

        List<QueryCondition> conditionList = wrapper.getQueryConditionList();
        if (conditionList != null && conditionList.size() > 0) {
            StringBuffer sb = new StringBuffer(" WHERE ");
            for (QueryCondition condition : conditionList) {
                sb.append(condition.getReleationResult());
                sb.append(" and ");
            }
            return sb.substring(0, sb.toString().length() - 4);
        }

        return "";
    }

    public static String ConvertToWhereCondition(Map<String, String[]> conditionMap) {
        if (conditionMap == null || conditionMap.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer(" WHERE ");
        for (Map.Entry<String, String[]> entry : conditionMap.entrySet()) {
            if (entry.getValue().length == 1 && entry.getValue()[0].trim().length() > 0) {
                sb.append(entry.getKey() + "=" + entry.getValue()[0] + " AND ");
            } else if (entry.getValue().length > 1) {
                sb.append(entry.getKey() + " in (");
                for (String itemStr : entry.getValue()) {
                    if (itemStr.trim().length() > 0) {
                        sb.append(itemStr + ",");
                    }
                }
                sb.replace(sb.toString().length() - 1, sb.toString().length(), "");
                sb.append(") AND ");
            }
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());


        }
        return sb.substring(0, sb.toString().length() - 4);
    }

    public static String ConvertSumSql(String sql) {
        return String.format("SELECT  COUNT(*) as count from (%s) as queryStr", sql);
    }

    public static Integer getCount(List<Map<String, Object>> list) {
        if (list != null && list.size() > 0) {
            BigInteger count = (BigInteger) list.get(0).get("count");
            return count.intValue();
        }
        return 0;
    }
}
