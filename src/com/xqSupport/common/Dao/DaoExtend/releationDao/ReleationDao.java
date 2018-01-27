package com.xqSupport.common.Dao.DaoExtend.releationDao;

import com.xqSupport.Entity.BaseEntity;
import com.xqSupport.common.Dao.BaseDao;
import com.xqSupport.common.Utils.QueryUtils.ConditionWrapper;
import com.xqSupport.common.Utils.QueryUtils.QueryCondition;
import com.xqSupport.common.Utils.SqlUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2018/1/18.
 */
public abstract class ReleationDao<T> extends BaseDao<T> implements IReleationDao<T> {


    /**
     * 获取操作对象的类名
     *
     * @return
     */
    public abstract String getLeafEntityTableName();

    /**
     * 根据class 返回 关系表关联的两方表名
     *
     * @param classT
     * @return
     */
    public abstract String getRleatedTableNameByEntityClass(Class classT);

    /**
     * 根据class获取在关系表中的外键或唯一约束列名
     *
     * @param classT
     * @return
     */
    public abstract String getColumnNameByEntityClass(Class classT);


    /**
     * @param releationEntityid
     * @param targetClass
     * @param releatedClass
     * @return
     */
    public List getList(Serializable releationEntityid, Class releatedClass, Class targetClass) {
        String sql = "selecte distinct k.* from " + getLeafEntityTableName() + "r left join " +
                getRleatedTableNameByEntityClass(targetClass) + " k on k.id = r." + getColumnNameByEntityClass(targetClass) + " where " +
                getColumnNameByEntityClass(targetClass) + ".id = " + releationEntityid;
        List list = findSql(sql);

        return list;
    }

    public void addReleation(T releationEntity) {
//        String whereCondition = SqlUtils.ConvertToWhereCondition(new ConditionWrapper()
//                .addCondition(getRleatedTableNameByEntityClass(firstClass), firstEntityId + "", QueryCondition.Type.equal)
//                .addCondition(getRleatedTableNameByEntityClass(secondClass), secondEntityId + "", QueryCondition.Type.equal));
//        String deleteSql = "delete from " + getLeafEntityTableName() + whereCondition;

        delete(releationEntity);
        save(releationEntity);
    }
}
