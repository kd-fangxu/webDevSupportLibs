package com.xqSupport.Service;


import com.xqSupport.Entity.BaseEntity;
import com.xqSupport.common.Dao.BaseDao;
import com.xqSupport.common.Dao.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xu on 2017/12/4.
 */

public class BaseService {
    @Autowired
    LocalSessionFactoryBean sessionFactory;

    protected Map<String, IBaseDao<? extends BaseEntity>> daoMap = new HashMap<String, IBaseDao<? extends BaseEntity>>();

    /**
     * 提供获取bean  dao的容器  用来处理一般数据库操作
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public <T extends BaseEntity> IBaseDao<T> getBaseDao(Class<T> tClass) {
        if (daoMap.containsKey(tClass.getName())) {
            return (BaseDao<T>) daoMap.get(tClass.getName());
        } else {
            BaseDao<T> baseDao = new BaseDao<T>();
            baseDao.setSessionFactory0(sessionFactory.getObject());
            baseDao.entityClazz = tClass;//这个不设置entityClazz未null 但是spring加载时没问题  应该是运行时编译时的问题
            daoMap.put(tClass.getName(), baseDao);
        }

        return (IBaseDao<T>) daoMap.get(tClass.getName());
    }

//    public <T extends BaseLinkedTreeEntity, E extends IBaseDao<T>> E getBaseDao(Class<T> treeEntityClass, final Class<E> daoImplClass) {
//        if (daoMap.containsKey(treeEntityClass.getName())) {
//            return (E) daoMap.get(treeEntityClass.getName());
//        } else {
////            BaseDao<>
//            if (daoImplClass.getName().equals(BaseDao.class.getName())) {
//                BaseDao<T> baseDao = new BaseDao<T>();
//                baseDao.setSessionFactory0(sessionFactory.getObject());
//                baseDao.entityClazz = treeEntityClass;//这个不设置entityClazz未null 但是spring加载时没问题  应该是运行时编译时的问题
//                daoMap.put(treeEntityClass.getName(), baseDao);
//            } else if (daoImplClass.getName().equals(TreeDaoImpl.class.getName())) {
//                TreeDaoImpl<T> treeDao = new TreeDaoImpl<T>() {
//                    @Override
//                    public String getEntityClassName() {
//                        return daoImplClass.getName();
//                    }
//                };
//                treeDao.setSessionFactory0(sessionFactory.getObject());
//                treeDao.entityClazz = treeEntityClass;//这个不设置entityClazz未null 但是spring加载时没问题  应该是运行时编译时的问题
//                String key = treeEntityClass.getName();
//                daoMap.put(key, treeDao);
//            }
//
//        }
//
//        return (E) daoMap.get(treeEntityClass.getName());
//    }
}
