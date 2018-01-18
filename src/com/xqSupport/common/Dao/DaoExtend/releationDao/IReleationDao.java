package com.xqSupport.common.Dao.DaoExtend.releationDao;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.xqSupport.Entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 2018/1/18.
 */
public interface IReleationDao<K extends BaseEntity, V extends BaseEntity> {
    List<K> getListKBy(Serializable id, Class<V> vClass);

    List<V> getListVBy(Serializable id, Class<K> vClass);
}
