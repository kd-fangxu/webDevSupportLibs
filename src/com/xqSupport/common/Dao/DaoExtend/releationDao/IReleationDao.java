package com.xqSupport.common.Dao.DaoExtend.releationDao;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.xqSupport.Entity.BaseEntity;
import com.xqSupport.common.Dao.IBaseDao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 2018/1/18.
 */
public interface IReleationDao<T extends BaseEntity> extends IBaseDao<T> {
    List getList(Serializable releationEntityid, Class releatedClass, Class targetClass);
}
