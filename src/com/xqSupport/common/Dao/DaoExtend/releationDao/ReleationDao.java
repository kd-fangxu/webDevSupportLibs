package com.xqSupport.common.Dao.DaoExtend.releationDao;

import com.xqSupport.Entity.BaseEntity;
import com.xqSupport.common.Dao.BaseDao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 2018/1/18.
 */
public abstract class ReleationDao<T> extends BaseDao implements IReleationDao {


    /**
     * 获取操作对象的类名
     *
     * @return
     */
    public abstract String getLeafEntityTableName();

    public abstract String getRleatedTableNameByEntityClass(Class classT);

    public abstract String getColumnNameByEntityClass(Class classT);


    /**
     * @param releationEntityid
     * @param targetClass
     * @param releatedClass
     * @return
     */
    public List getList(Serializable releationEntityid, Class releatedClass, Class targetClass) {
        String sql = "selecte distinct k.* from " + getLeafEntityTableName() + "r left join " +
                getRleatedTableNameByEntityClass(targetClass) + " k on k.id = r.id where " +
                getColumnNameByEntityClass(targetClass) + ".id = " + releationEntityid;
        List list = findSql(sql);

        return list;
    }

}
