package com.xqSupport.common.Dao.DaoExtend.releationDao;

import com.xqSupport.Entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 2018/1/18.
 */
public abstract class ReleationDao<K extends BaseEntity,V extends BaseEntity> implements IReleationDao<K,V> {


    /**
     * 获取操作对象的类名
     *
     * @return
     */
    public abstract String getEntityClassName();

    public List<K> getListKBy(Serializable id, Class<V> vClass) {

        return null;
    }

    public List<V> getListVBy(Serializable id, Class<K> vClass) {
        return null;
    }
}
