package com.xqSupport.common.RestFul.Service;

import com.xqSupport.common.BaseResponse;
import com.xqSupport.common.Dao.IBaseDao;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Created by developer on 2017/7/7.
 */
@Service
@Transactional
public class RestSerImpl implements IRestSer {

    @Resource
    IBaseDao<Object> baseDao;


    public BaseResponse doRestFulApi(HttpServletRequest request, @PathVariable String action, @PathVariable String moduleName, @PathVariable String targetEntityName) {

        try {
            Object newInstance = Class.forName("com.com.xq." + moduleName + ".Bean." + targetEntityName).newInstance();//采用@Component优化
            try {
                BeanUtils.populate(newInstance, request.getParameterMap());
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            if (newInstance != null) {
                String msg = action + "=====" + newInstance.getClass().getName() + "===" + request.getParameterMap().toString();
                if (action.equals("add")) {
                    return BaseResponse.create(1, msg, saveEntity(newInstance));
                } else if (action.equals("update")) {
                    updateEntity(newInstance);
                    return BaseResponse.create(1, msg, "");
                } else if (action.equals("delete")) {
                    delete(newInstance);
                    return BaseResponse.create(1, msg, "");
                } else if (action.equals("get")) {

                    if (targetEntityName != null) {
                        StringBuilder sb = new StringBuilder("from " + targetEntityName + " where ");
                        Map<String, String[]> parameterMap = request.getParameterMap();
                        for (String key : parameterMap.keySet()) {
                            System.out.println("key = " + key + "; value = " + parameterMap.get(key));
                            System.out.println(parameterMap.get(key).toString());
                            if (parameterMap.get(key).length > 0) {
                                sb.append(key + "=\'" + parameterMap.get(key)[0] + "\' and ");
                            }

                        }
                        try {
                            sb.delete(sb.length() - 4, sb.length());
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                        System.out.println(sb.toString());
                        return BaseResponse.create(1, msg, find(sb.toString()));

                    }

                }

            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object saveEntity(Object ob) {
        return baseDao.save(ob);
    }

    public void updateEntity(Object ob) {
        baseDao.update(ob);
    }

    public void delete(Object object) {
        baseDao.delete(object);
    }

    public List<Object> find(String queryStr) {
        return baseDao.findList(queryStr, new Object[]{});
    }
}
