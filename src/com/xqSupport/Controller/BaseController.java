package com.xqSupport.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by xu on 2017/12/4.
 */
public class BaseController {
    /**
     * 转化请求对象参数为javaBean  key值对应属性名规则
     *
     * @param tClass
     * @param request
     * @param <T>
     * @return
     */
    public <T> T getRequestCovertBean(Class<T> tClass, HttpServletRequest request) {
        try {
            T t = tClass.newInstance();
            BeanUtils.populate(t, request.getParameterMap());
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String transToJsonStr(Object ob) {
//        ReflectiveAspectJAdvisorFactorycom.microsoft.sqlserver.jdbc.SQLServerDriver

        try {
            String resultStr = new ObjectMapper().writeValueAsString(ob);
//            System.out.println(resultStr);
            return resultStr;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        return "!!!!!======>null";
    }
}
